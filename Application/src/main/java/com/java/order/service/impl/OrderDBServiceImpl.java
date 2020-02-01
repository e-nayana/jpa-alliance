package com.java.order.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huston.rest.exception.AbnoramalBehaviorException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.rest.exception.SystemBussinessLogicException;
import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.APP;
import com.java.address.service.AddressService;
import com.java.area.model.Area;
import com.java.area.service.AreaService;
import com.java.contact.service.ContactService;
import com.java.customer.service.CustomerService;
import com.java.inventory.model.Inventory;
import com.java.inventory.model.InventoryType;
import com.java.inventory.model.StockCountView;
import com.java.inventory.service.InventoryService;
import com.java.inventory.service.StockCountViewService;
import com.java.order.CONST;
import com.java.order.model.CustomerOrder;
import com.java.order.model.CustomerOrderItem;
import com.java.order.repository.OrderRepository;
import com.java.order.service.OrderDBService;
import com.java.order.service.OrderItemService;
import com.java.product.model.Product;
import com.java.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderDBServiceImpl extends GenericCrudServiceImpl<OrderRepository, CustomerOrder>  implements OrderDBService {

    private final static String state = "pending";

    OrderItemService orderItemService;
    AddressService addressService;
    ContactService contactService;
    CustomerService customerService;
    ProductService productService;
    AreaService areaService;
    InventoryService inventoryService;
    StockCountViewService stockCountViewService;


    @Autowired
    public OrderDBServiceImpl(OrderItemService orderItemService,
                              AddressService addressService,
                              ContactService contactService,
                              CustomerService customerService,
                              ProductService productService,
                              AreaService areaService,
                              InventoryService inventoryService,
                              StockCountViewService stockCountViewService) {

        this.orderItemService = orderItemService;
        this.addressService = addressService;
        this.contactService = contactService;
        this.customerService = customerService;
        this.productService = productService;
        this.areaService = areaService;
        this.inventoryService = inventoryService;
        this.stockCountViewService = stockCountViewService;
    }

    /**
     * IF TRANSACTIONAL DIDNT WORK
     * save state as empty string firstly
     * do other sub tasks
     * if all things went ok
     * save order with current state
     * @param order
     * @return
     * @throws CrudGenericException
     * @throws ResourceNotFoundException
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public CustomerOrder saveOrder(CustomerOrder order) throws CrudGenericException, ResourceNotFoundException, AllianceException, AbnoramalBehaviorException
    {


        Area deliveryArea = areaService.findArea(order.getShippingArea().getId());

        if(deliveryArea == null){
            throw new ResourceNotFoundException("Area was not found id - " + order.getShippingArea().getId());
        }

        order.setShippingAddressId(addressService.save(order.getShippingAddress()).getId());
        order.setStatus(state);
        order.setShippingAreaId(deliveryArea.getId());
        order.setDeliverCompletePeriod(deliveryArea.getServiceOfArea().getHour());
        order.setShippingCost(deliveryArea.getServiceOfArea().getPrice());
        CustomerOrder savedOrder = repository.save(order);

        Double orderTotal = saveCustomerOrderItems(savedOrder.getId(), savedOrder.getCustomerOrderItems(),0,0);

        savedOrder.setAmount(orderTotal);
        savedOrder.setTotal(orderTotal + deliveryArea.getServiceOfArea().getPrice());
        return repository.save(savedOrder);
    }

    /**
     * Update existing order
     * @param newOrder
     * @return
     * @throws CrudGenericException
     */
    @Transactional
    @Override
    public CustomerOrder update(CustomerOrder newOrder) throws CrudGenericException
    {

        CustomerOrder currentOrder = show(newOrder.getId());
        orderItemService.deleteBatch(currentOrder.getCustomerOrderItems());

        for (CustomerOrderItem item: newOrder.getCustomerOrderItems()) {
            item.setCustomerOrderId(currentOrder.getId());
            orderItemService.save(item);
        }

        return currentOrder;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deductProductsFromInventory(CustomerOrder order) throws SystemBussinessLogicException
    {

        inventoryUpdate(order.getCustomerOrderItems(), "deduct");
        throw new SystemBussinessLogicException("Products you requested just got outed of stock!");
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void restoreProductsInInventory(CustomerOrder order) throws SystemBussinessLogicException
    {
        inventoryUpdate(order.getCustomerOrderItems(), "restore");
        throw new SystemBussinessLogicException("Cannot restore");
    }

    /**
     * Save customer order items OR customer order child items
     *
     * NOTE -------- This function recurse itself and this function has been limited going too deep by deep level controller (deepLevel)
     *      -------- While saving child order items, calculated price has been ignored.
     * @param orderId
     * @param customerOrderItems
     * @return
     * @throws ResourceNotFoundException
     * @throws CrudGenericException
     */
    private Double saveCustomerOrderItems(Integer orderId, List<CustomerOrderItem> customerOrderItems, Integer selfParentId,Integer deepLevel) throws
            ResourceNotFoundException,
            CrudGenericException,
            AbnoramalBehaviorException
    {

        if(deepLevel > CONST.MAX_DEEP_LEVEL){
            throw new AbnoramalBehaviorException(CONST.GOING_TOO_DEEP_IN_RECURSION_FUNC);
        }
        deepLevel++;

        ObjectMapper Obj = new ObjectMapper();
        Double orderTotal = 0D;

        for (CustomerOrderItem item : customerOrderItems)
        {
            Product currentProduct = productService.find(item.getProduct().getId());
            Double currentTotal = 0D;
            CustomerOrderItem savedCustomerOrderItem;

            if(currentProduct == null)
            {
                throw new ResourceNotFoundException("Product was not found id - " + item.getProduct().getId());
            }
            currentTotal = item.getQuantity()*currentProduct.getPrice();

            try
            {
                item.setCurrentProduct(Obj.writeValueAsString(currentProduct));
            }catch (JsonProcessingException e)
            {
                e.printStackTrace();
            }
            item.setType(currentProduct.getType());
            item.setSelfParentId(selfParentId);
            item.setCustomerOrderId(orderId);
            item.setProductId(currentProduct.getId());
            item.setTotal(currentTotal);

            orderTotal += currentTotal;
            savedCustomerOrderItem = orderItemService.save(item);

            if(APP.PRODUCT_TYPE.COMBO.getType().equals(currentProduct.getType()))
            {
                saveCustomerOrderItems(orderId, item.getChildCustomerOrderItems(), savedCustomerOrderItem.getId(), deepLevel);
            }
        }
        return orderTotal;
    }

    /**
     *
     * @param customerOrderItems
     * @param action
     */
    private void inventoryUpdate(List<CustomerOrderItem> customerOrderItems, String action) throws SystemBussinessLogicException {
        for (CustomerOrderItem customerOrderItem : customerOrderItems){

            Inventory inventory = new Inventory();

            if (action.equals("deduct")) {

                StockCountView stockCountView = new StockCountView();
                stockCountView.setProductId(customerOrderItem.getProductId());
                stockCountView.setProductVariationId(customerOrderItem.getProductVariationId());

                stockCountView = stockCountViewService.getStockCountViewOfProduct(stockCountView);

                if (stockCountView == null) {
                    throw new SystemBussinessLogicException("Products you requested just got outed of stock!");
                }

                if (stockCountView.getQuantity() < customerOrderItem.getQuantity()) {
                    throw new SystemBussinessLogicException("Products you requested just got outed of stock!");
                }

                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(date);

                inventory.setDate(strDate);
                inventory.setCustomerOrderId(customerOrderItem.getCustomerOrderId());
                inventory.setCustomerOrderItemId(customerOrderItem.getId());
                inventory.setProductVariationId(customerOrderItem.getProductVariationId());
                inventory.setProductId(customerOrderItem.getProductId());
                inventory.setQuantity((-1)*customerOrderItem.getQuantity());
                inventory.setUnitPrice(customerOrderItem.getUnitPrice());
                inventory.setUom("Unit");
                inventory.setType(InventoryType.SALE);

                inventoryService.inventoryManagement(inventory);
            } else if (action.equals("restore")) {
                inventory = inventoryService.showInventoryByCustomerOrderItem(customerOrderItem.getId());
                if (inventory != null) {
                    inventoryService.deactivateInventory(inventory.getId());
                }
            }
        }
    }
}

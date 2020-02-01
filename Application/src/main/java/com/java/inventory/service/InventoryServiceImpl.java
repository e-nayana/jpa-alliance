package com.java.inventory.service;

import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.java.inventory.model.Inventory;
import com.java.inventory.repository.InventoryRepository;
import com.java.service.LoggerFile;
import com.java.service.Pagenator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class InventoryServiceImpl extends GenericCrudServiceImpl<InventoryRepository, Inventory> implements InventoryService{

    private LoggerFile loggerFile = new LoggerFile(this.getClass());
    private InventoryRepository inventoryRepository;

    @Autowired
    InventoryServiceImpl(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Pagenator.PagenatedObject list(int page, int perPage, HashMap<String,Object> filters){
        return list(page,perPage,searchCriteria(filters),"product", "warehouse", "costCenter");
    }

    private Specification<Inventory> searchCriteria(HashMap<String,Object> filters) {
        return new Specification<Inventory>() {
            @Override
            public Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> pd = new ArrayList<>();

                String itemSerial = (String) filters.putIfAbsent("itemSerial", null);
                Integer productId = (Integer) filters.putIfAbsent("productId", 0);

                if (productId != null && productId != 0) {
                    pd.add(criteriaBuilder.equal(root.get("productId"), productId));
                }

                if (itemSerial != null && !"".equals(itemSerial)) {
                    pd.add(criteriaBuilder.equal(root.get("serialNo"), itemSerial));
                }

                pd.add(criteriaBuilder.equal(root.get("isActive"), true));
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
                return criteriaBuilder.and(pd.toArray(new Predicate[0]));
            }
        };
    }

    public List getInventorySummary() {
        List<HashMap<String,Object>> inventorySummary = new ArrayList<>();
        List<Object[]> list = inventoryRepository.inventorySummary();
        list.forEach((resultItem)->{
            HashMap<String,Object> summary = new HashMap<>();
            summary.put("productId",resultItem[0]);
            summary.put("itemCode",resultItem[1]);
            summary.put("itemName",resultItem[2]);
            summary.put("quantity",resultItem[3]);
            summary.put("uom",resultItem[4]);
            summary.put("onlineItemId",resultItem[5]);
            inventorySummary.add(summary);
        });
        loggerFile.createLog("getInventorySummary", LoggerFile.LogType.INFO, "getInventorySummary");
        return inventorySummary;
    }

    public List inventorySummaryForEbayStockUpdate() {
        List<HashMap<String,Object>> inventorySummary = new ArrayList<>();
        List<Object[]> list = inventoryRepository.inventorySummaryForEbayStockUpdate();
        list.forEach((resultItem)->{
            HashMap<String,Object> summary = new HashMap<>();
            summary.put("productId",resultItem[0]);
            summary.put("itemCode",resultItem[1]);
            summary.put("itemName",resultItem[2]);
            summary.put("quantity",resultItem[3]);
            summary.put("uom",resultItem[4]);
            summary.put("onlineItemId",resultItem[5]);
            summary.put("apiType",resultItem[6]);
            summary.put("productOnlineItemTableId",resultItem[7]);
            inventorySummary.add(summary);
        });
        loggerFile.createLog("getInventorySummary", LoggerFile.LogType.INFO,"getInventorySummary");
        return inventorySummary;
    }

    @Transactional(rollbackOn = Exception.class)
    public Inventory inventoryManagement(Inventory inventory) {
        new LoggerFile().createLog(this.getClass(),"inventoryManagement","info","inventory data update started ",inventory);
        if(inventory.getDate() == null){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            inventory.setDate(dateTimeFormatter.format(now));
        }
        inventory = inventoryRepository.save(inventory);
        new LoggerFile().createLog(this.getClass(),"inventoryManagement","info","inventory data update ended ",inventory);
        return inventory;
    }

    public boolean deactivateInventory(int id) {
        Inventory inventory = inventoryRepository.findById(id);
        inventory.setIsActive(false);
        inventoryRepository.save(inventory);
        return true;
    }

    public Inventory showInventoryBySalesInvoiceItem(int salesInvoiceItemId, String serialNo) {
        return inventoryRepository.findFirstBySalesInvoiceItemIdAndSerialNoAndIsActive(salesInvoiceItemId, serialNo, true);
    }

    @Override
    public Inventory showInventoryByGoodReceiveNoteItem(int goodReceiveNoteItemId) {
        return inventoryRepository.findFirstByGoodReceivedNoteItemIdAndIsActive(goodReceiveNoteItemId, true);
    }

    @Override
    public Inventory showInventoryByCustomerOrderItem(int customerOrderItemId) {
        return inventoryRepository.findFirstByCustomerOrderItemIdAndIsActive(customerOrderItemId, true);
    }
}

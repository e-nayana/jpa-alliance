package com.java.order.state.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huston.rest.exception.AbnoramalBehaviorException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.rest.exception.SystemBussinessLogicException;
import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.huston.springboot.crudgeneric.pagination.ResultPage;
import com.java.order.ControllerRequestCriteria;
import com.java.order.model.CustomerOrder;
import com.java.order.service.CustomerOrderViewService;
import com.java.order.service.OrderDBService;
import com.java.order.state.exception.StateTransitException;

public class BaseOrderState implements OrderState {

    protected OrderDBService orderDBService = BeanUtil.getBean(OrderDBService.class);
    protected CustomerOrderViewService customerOrderViewDBService = BeanUtil.getBean(CustomerOrderViewService.class);
    protected CustomerOrder order;

    public BaseOrderState(CustomerOrder order) {
        this.order = order;
    }

    @Override
    public CustomerOrder transitNext() throws CrudGenericException, ResourceNotFoundException, StateTransitException{
        throw new StateTransitException("Current state does not have next state.");
    }

    @Override
    public CustomerOrder transitPrev() throws StateTransitException {
        throw new StateTransitException("Current state does not have previous state.");
    }

    @Override
    public CustomerOrder save() throws StateTransitException, CrudGenericException, ResourceNotFoundException, AllianceException, AbnoramalBehaviorException {
        throw new StateTransitException("Current state does not have save state.");
    }

    @Override
    public CustomerOrder update(CustomerOrder order) throws StateTransitException, CrudGenericException {
        throw new StateTransitException("Current state does not have update state.");
    }

    @Override
    public CustomerOrder confirm() throws CrudGenericException, StateTransitException, ResourceNotFoundException, SystemBussinessLogicException {
        throw new StateTransitException("Current state does not have update state.");
    }

    @Override
    public CustomerOrder cancel() throws CrudGenericException, ResourceNotFoundException,StateTransitException, SystemBussinessLogicException {
        throw new StateTransitException("Current state does not have update state.");
    }

    @Override
    public ResultPage getActiveList(Integer page, Integer perPage){
        return orderDBService.page(page, perPage,"customerOrderItems", "customer", "shippingAddress", "shippingContact");
    }

    @Override
    public ResultPage getActiveListForAuthenticatedCustomer(Integer page, Integer perPage) throws ResourceNotFoundException {
        return customerOrderViewDBService.getActiveListForAuthenticatedCustomer(page, perPage, "shippingAddress", "shippingArea");
    }

    @Override
    public CustomerOrder get(Integer orderId) throws AllianceException, CrudGenericException {
        return orderDBService.show(orderId,"customerOrderItems","customer", "shippingAddress", "shippingArea");
    }

    @Override
    public ResultPage searchOrders(ControllerRequestCriteria controllerRequestCriteria) throws ResourceNotFoundException , JsonProcessingException {
        return customerOrderViewDBService.search(controllerRequestCriteria, "shippingAddress","customer");
    }

    protected CustomerOrder changeState(String state) throws CrudGenericException, ResourceNotFoundException{
        this.order = orderDBService.show(this.order.getId());

        if(this.order == null){
            throw new ResourceNotFoundException("Order was not found");
        }

        this.order.setStatus(state);
        return orderDBService.save(this.order);
    }

}

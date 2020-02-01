package com.java.order.state;

import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.rest.exception.SystemBussinessLogicException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.order.model.CustomerOrder;
import com.java.order.state.engine.BaseOrderState;
import com.java.order.state.engine.STATES;
import com.java.order.state.exception.StateTransitException;

public class PendingState extends BaseOrderState {

    private final String NEXTSTATE = STATES.CONFIRMED;

    public PendingState(CustomerOrder order) {
        super(order);
    }

    public CustomerOrder transitNext() throws CrudGenericException, ResourceNotFoundException, StateTransitException{
        return changeState(NEXTSTATE);
    }

    @Override
    public CustomerOrder confirm() throws CrudGenericException, ResourceNotFoundException, SystemBussinessLogicException {
        orderDBService.deductProductsFromInventory(this.order);
        return changeState(NEXTSTATE);
    }

    @Override
    public CustomerOrder cancel() throws CrudGenericException, ResourceNotFoundException, SystemBussinessLogicException {
        orderDBService.restoreProductsInInventory(this.order);
        return changeState(STATES.CANCEL);
    }

    @Override
    public CustomerOrder update(CustomerOrder order) throws CrudGenericException {
        return orderDBService.save(order);
    }
}


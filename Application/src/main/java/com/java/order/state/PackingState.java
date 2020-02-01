package com.java.order.state;

import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.order.model.CustomerOrder;
import com.java.order.state.engine.BaseOrderState;
import com.java.order.state.engine.STATES;

public class PackingState extends BaseOrderState {

    private final String nextState = STATES.DELIVERYINPROGRESS;

    public PackingState(CustomerOrder order) {
        super(order);
    }

    @Override
    public CustomerOrder transitNext() throws CrudGenericException, ResourceNotFoundException {
        return changeState(nextState);
    }

}

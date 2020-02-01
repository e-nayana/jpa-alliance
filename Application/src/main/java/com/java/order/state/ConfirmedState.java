package com.java.order.state;

import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.order.model.CustomerOrder;
import com.java.order.state.engine.BaseOrderState;
import com.java.order.state.engine.STATES;

public class ConfirmedState extends BaseOrderState {

    private final String NEXTSTATE = STATES.PACKAGING;

    public ConfirmedState(CustomerOrder order) {
        super(order);
    }

    @Override
    public CustomerOrder transitNext() throws CrudGenericException, ResourceNotFoundException {
        return changeState(NEXTSTATE);
    }

    @Override
    public CustomerOrder cancel() throws CrudGenericException, ResourceNotFoundException {
        return changeState(STATES.CANCEL);
    }
}

package com.java.order.state;

import com.huston.rest.exception.AbnoramalBehaviorException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.order.model.CustomerOrder;
import com.java.order.state.engine.BaseOrderState;
import com.java.order.state.engine.STATES;

/**
 * Before create a order
 * Preconditions- no order id,cannot be exist in data base
 * PostConditions - must have a order id, must have atleast one product, should belong to customer
 */
public class InitStatus extends BaseOrderState {

    private final String NEXTSTATE = STATES.PENDING;

    public InitStatus(CustomerOrder order) {
        super(order);
    }

    @Override
    public CustomerOrder save() throws CrudGenericException, ResourceNotFoundException, AllianceException, AbnoramalBehaviorException {
        return orderDBService.saveOrder(this.order);
    }

    @Override
    public CustomerOrder cancel() throws CrudGenericException, ResourceNotFoundException {
        return changeState(STATES.CANCEL);
    }
}

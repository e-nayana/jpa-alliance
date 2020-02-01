package com.java.order.state;

import com.java.order.model.CustomerOrder;
import com.java.order.state.engine.BaseOrderState;

public class ReturnedState extends BaseOrderState {

    public ReturnedState(CustomerOrder order) {
        super(order);
    }
}

package com.java.order.state;

import com.java.order.model.CustomerOrder;
import com.java.order.state.engine.BaseOrderState;

public class NotFoundState extends BaseOrderState {

    public NotFoundState(CustomerOrder order) {
        super(order);
    }
}
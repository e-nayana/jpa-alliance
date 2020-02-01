package com.java.order.state;

import com.java.order.model.CustomerOrder;
import com.java.order.state.engine.BaseOrderState;

public class CancelState extends BaseOrderState {
    public CancelState(CustomerOrder order) {
        super(order);
    }
}

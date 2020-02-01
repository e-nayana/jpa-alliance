package com.java.order.state;

import com.java.order.model.CustomerOrder;
import com.java.order.state.engine.BaseOrderState;

public class DeliveredState extends BaseOrderState {


    public DeliveredState(CustomerOrder order) {
        super(order);
    }
}

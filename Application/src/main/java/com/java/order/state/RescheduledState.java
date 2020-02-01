package com.java.order.state;

import com.java.order.model.CustomerOrder;
import com.java.order.state.engine.BaseOrderState;

public class RescheduledState extends BaseOrderState {

    public RescheduledState(CustomerOrder order) {
        super(order);
    }
}

package com.java.order.state.exception;

import com.huston.rest.exception.SystemBussinessLogicException;

public class StateTransitException extends SystemBussinessLogicException {

    public StateTransitException(String message) {
        super(message);
    }
}

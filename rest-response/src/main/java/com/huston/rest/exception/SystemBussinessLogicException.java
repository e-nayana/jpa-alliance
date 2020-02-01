package com.huston.rest.exception;

public class SystemBussinessLogicException extends Exception {
    public SystemBussinessLogicException() {
    }

    public SystemBussinessLogicException(String message) {
        super(message);
    }

    public SystemBussinessLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemBussinessLogicException(Throwable cause) {
        super(cause);
    }

    public SystemBussinessLogicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

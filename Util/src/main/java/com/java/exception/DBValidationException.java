package com.java.exception;

public class DBValidationException extends Exception {

    public DBValidationException() {
    }

    public DBValidationException(String message) {
        super(message);
    }

    public DBValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBValidationException(Throwable cause) {
        super(cause);
    }

    public DBValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package com.huston.rest.exception;

public class AbnoramalBehaviorException extends Exception {

    public AbnoramalBehaviorException() {
    }

    public AbnoramalBehaviorException(String message) {
        super(message);
    }

    public AbnoramalBehaviorException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbnoramalBehaviorException(Throwable cause) {
        super(cause);
    }

    public AbnoramalBehaviorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

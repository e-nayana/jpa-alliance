package com.huston.springboot.crudgeneric.exception;

public class CustomCheckException extends CrudGenericException{

    public CustomCheckException(String message) {
        super(ExceptionType.CUSTOM_CHECK_EXCEPTION,message);
    }

    public CustomCheckException(String message, Throwable cause) {
        super(ExceptionType.CUSTOM_CHECK_EXCEPTION, message, cause);
    }

}

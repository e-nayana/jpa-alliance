package com.huston.springboot.crudgeneric.exception;

import com.huston.springboot.crudgeneric.Constants;

public class CrudGenericException extends Exception{

    private ExceptionType exceptionType;

    public CrudGenericException(ExceptionType exceptionType, Constants message) {
        super(message.getMessage());
        this.exceptionType = exceptionType;
    }

    public CrudGenericException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public CrudGenericException(ExceptionType exceptionType, Constants message, Throwable cause) {
        super(message.getMessage(), cause);
        this.exceptionType = exceptionType;
    }

    public CrudGenericException(ExceptionType exceptionType, String message, Throwable cause) {
        super(message, cause);
        this.exceptionType = exceptionType;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public enum ExceptionType {
        INVALID_DATA_EXCEPTION,
        ALREADY_EXIST_EXCEPTION,
        INVALID_ALLIANCE_FIELD,
        INVALID_ALLIANCE_REPOSITORY,
        INVALID_ALLIANCE_FOREIGN_KEY,
        INVALID_WHERE_ALLIANCE_FILTERS,
        ALLIANCE_FIELD_CANNOT_BE_FOUND,
        WHERE_ALLIANCE_FIELD_CANNOT_BE_FOUND,
        INVALID_ALLIANCE_ENTITY,
        CANNOT_CAST_TO_GENERIC_CRUD_REPOSITORY,
        CUSTOM_CHECK_EXCEPTION
    }

}

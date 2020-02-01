package com.huston.springboot.crudgeneric.exception;

import com.huston.springboot.crudgeneric.Constants;

public class AllianceException extends Exception{

    private ExceptionType exceptionType;

    public AllianceException(ExceptionType exceptionType, Constants message) {
        super(message.getMessage());
        this.exceptionType = exceptionType;
    }

    public AllianceException(ExceptionType exceptionType, Constants message, Throwable cause) {
        super(message.getMessage(), cause);
        this.exceptionType = exceptionType;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public enum ExceptionType {
        INCORRECT_RESULT_SIZE_DATA_ACCESS_EXCEPTION,
//        ALREADY_EXIST_EXCEPTION,
//        INVALID_ALLIANCE_FIELD,
//        INVALID_ALLIANCE_REPOSITORY,
//        INVALID_ALLIANCE_FOREIGN_KEY,
//        INVALID_WHERE_ALLIANCE_FILTERS,
//        ALLIANCE_FIELD_CANNOT_BE_FOUND,
//        WHERE_ALLIANCE_FIELD_CANNOT_BE_FOUND,
//        INVALID_ALLIANCE_ENTITY
    }

}

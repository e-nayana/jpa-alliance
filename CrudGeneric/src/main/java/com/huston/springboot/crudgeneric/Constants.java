package com.huston.springboot.crudgeneric;

public enum Constants {


    /**
     * Messages
     */
    MSG_INVALID_DATA_EXCEPTION_CANNOT_FIND_RECORDS("Cannot find the record"),
    MSG_INVALID_DATA_EXCEPTION_RECORD_ALREADY_EXIST("Record already exist"),
    MSG_INVALID_ALLIANCE_CANNOT_FIND_FIELD("Field cannot be found in resource entity"),
    MSG_INVALID_ALLIANCE_REPOSITORY_TYPE("Alliance repository type cannot be null"),
    MSG_INVALID_ALLIANCE_KEY("Alliance key cannot be null or empty"),
    MSG_ALLIANCE_FIELD_CANNOT_BE_FOUDN("Alliance field cannot be found"),
    ALREADY_EXIST_EXCEPTION(""),
    NOT_AUTHORIZED_EXCEPTION(""),
    WRONG_VERIFICATION_CODE(""),
    ACCOUNT_NOT_VERIFIED(""),
    ACCOUNT_ALREADY_VERIFIED(""),

    REPOSITORY_MUST_BE_A_INSTANCE_OF_GENERIC_CRUD_REPOSITORY("Repository must be a instance of GenericCrudRepository to use alliances"),

    RESULT_RETURNS_MORE_THAN_ONE_ELEMENTS("Result returns more than one elements");

    /**
     * constants
     */

    private final String message;
    Constants(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

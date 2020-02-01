package com.java.service;

import com.java.exception.DBValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Set;

public class ValidationCheck {

    public ValidationCheck() {
    }

    public static String validator(Object object) throws Exception {

        String validationError = "";

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(object);

        if (violations != null) {
            for (ConstraintViolation<Object> violation : violations) {
                if (validationError.equals("")) {
                    validationError = violation.getMessage();
                } else {
                    validationError = validationError +".\n </br>"  + violation.getPropertyPath() + " - " +violation.getMessage();
                }
            }
        }
        if(!"".equals(validationError)){
            throw new DBValidationException(validationError);
        }
        return validationError;
    }

    public static ResponseEntity<?> validatorErrorReturn(String validationMessage) {

        HashMap<String, Object> validationMap = new HashMap<>();
        validationMap.put("message",validationMessage);
        validationMap.put("error","Validation Error");
        return new ResponseEntity<HashMap>(validationMap, HttpStatus.CONFLICT);
    }
}

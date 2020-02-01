package com.huston.rest.error.response;

import com.huston.rest.exception.ValidationException;
import com.huston.rest.response.RESTResponse;
import com.huston.rest.response.RESTResponseManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class ErrorResponseAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public final RESTResponse handleConflictException(ValidationException ex, WebRequest request) {
        ex.printStackTrace();
        return RESTResponseManager.
                headerBuilder(HttpStatus.CONFLICT)
                .errorBodyBuilder()
                .setError(ex.getMessage())
                .setMessage(ex.fillInStackTrace().toString())
                .setPath(request.getContextPath())
                .responseBuilder()
                .build();
    }

    @ExceptionHandler(Exception.class)
    public final RESTResponse handleGlobalException(Exception ex, WebRequest request) {

        ex.printStackTrace();

        return RESTResponseManager.
                headerBuilder(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorBodyBuilder()
                .setError(ex.getMessage())
                .setMessage(ex.fillInStackTrace().toString())
                .setPath(request.getContextPath())
                .responseBuilder()
                .build();
    }
}

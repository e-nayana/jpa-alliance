package com.huston.rest.response;

import org.springframework.http.HttpStatus;

/**
 * @author huston
 * Starting class to build response
 */
public class RESTResponseManager {

    /**
     * Start building from HeaderBuilder
     * @param httpStatus
     * @return
     */
    public static Header.HeaderBuilder headerBuilder(HttpStatus httpStatus){
       return new Header.HeaderBuilder(httpStatus);
    }
}

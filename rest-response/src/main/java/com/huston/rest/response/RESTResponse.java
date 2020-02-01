package com.huston.rest.response;

import org.springframework.http.ResponseEntity;

/**
 * @author huston
 *
 * RESTResponse
 * RESTResponseBuilder static nested builder class
 * builder pattern
 */
public class RESTResponse extends ResponseEntity {

    private RESTResponse(RESTResponseBuilder restResponseBuilder) {
        super(restResponseBuilder.body, restResponseBuilder.header.getHeaderAttributes(), restResponseBuilder.header.getHttpStatus());
    }

    public static class RESTResponseBuilder<T>{
        private T body;
        private Header header;

        public RESTResponseBuilder(Header header, T responseBody){
            this.header = header;
            this.body = responseBody;
        }

        public RESTResponse build(){
            return new RESTResponse(this);
        }
    }
}

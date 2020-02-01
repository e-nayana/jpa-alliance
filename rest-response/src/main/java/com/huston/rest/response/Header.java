package com.huston.rest.response;

import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author huston
 *
 * Header
 * HeaderBuilder static nested builder class
 * builder pattern
 * some modification for chaining header to response body
 */
public class Header {

    private MultiValueMap<String,String> headerAttributes;
    private HttpStatus httpStatus;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public MultiValueMap<String, String> getHeaderAttributes() {
        return headerAttributes;
    }

    private Header(HeaderBuilder headerBuilder){
        this.headerAttributes = headerBuilder.headerAttributes;
        this.httpStatus = headerBuilder.httpStatus;
    }

    public static class HeaderBuilder{

        private MultiValueMap<String,String> headerAttributes = new LinkedMultiValueMap<>();
        private HttpStatus httpStatus;

        public HeaderBuilder(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
        }

        public HeaderBuilder setHeaderAttribute(String key, String value){
            this.headerAttributes.add(key, value);
            return this;
        }

        /**
         * according to the original builder pattern , this should be declared as public
         * @return
         */
        protected Header build(){
            return new Header(this);
        }

        /**
         * modification for chaining from header to body
         * @return
         */
        public ResponseBody.ResponseBodyBuilder bodyBuilder(){
            return new ResponseBody.ResponseBodyBuilder(build());
        }

        public ErrorResponseBody.ErrorResponseBodyBuilder errorBodyBuilder(){
            return new ErrorResponseBody.ErrorResponseBodyBuilder(build());
        }
    }
}

package com.huston.rest.response;

import java.util.HashMap;

/**
 * @author huston
 *
 * ResponseBody
 * ResponseBodyBuilder static nested builder class
 * builder pattern
 * some modification for chaining header to response body
 */
public class ResponseBody {

    private HashMap<String,?> bodyAttributes;

    public HashMap<String, ?> getBodyAttributes() {
        return bodyAttributes;
    }

    private ResponseBody(ResponseBodyBuilder responseBodyBuilder){
        this.bodyAttributes = responseBodyBuilder.bodyAttributes;
    }

    public static class ResponseBodyBuilder{

        private Header header;
        private HashMap<String,Object> bodyAttributes = new HashMap<>();

        public ResponseBodyBuilder(Header header) {
            this.header = header;
        }

        public ResponseBodyBuilder setBodyAttribute(String key, Object data) {
            bodyAttributes.put(key, data);
            return this;
        }

        /**
         * according to the original builder pattern , this should be declared as public
         * @return
         */
        protected ResponseBody build(){
            return new ResponseBody(this);
        }

        /**
         * modification for chaining from body to response
         * @return
         */
        public RESTResponse.RESTResponseBuilder responseBuilder(){
            return new RESTResponse.RESTResponseBuilder(this.header, build());
        }
    }
}

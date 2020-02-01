package com.huston.rest.response;

public class ErrorResponseBody {

    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ErrorResponseBody(ErrorResponseBodyBuilder bodyBuilder) {
        this.timestamp = bodyBuilder.timestamp;
        this.status = bodyBuilder.status;
        this.error = bodyBuilder.error;
        this.message = bodyBuilder.message;
        this.path = bodyBuilder.path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public static class ErrorResponseBodyBuilder{

        private Header header;
        private String timestamp;
        private Integer status;
        private String error;
        private String message;
        private String path;


        public ErrorResponseBodyBuilder(Header header) {
            this.header = header;
            this.timestamp = timestamp;
            this.status = status;
            this.error = error;
        }

        public ErrorResponseBodyBuilder setTimestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorResponseBodyBuilder setStatus(Integer status) {
            this.status = status;
            return this;
        }

        public ErrorResponseBodyBuilder setError(String error) {
            this.error = error;
            return this;
        }

        public ErrorResponseBodyBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponseBodyBuilder setPath(String path) {
            this.path = path;
            return this;
        }

        public ErrorResponseBody build(){
            return new ErrorResponseBody(this);
        }

        public RESTResponse.RESTResponseBuilder responseBuilder(){
            return new RESTResponse.RESTResponseBuilder(this.header, build());
        }


    }
}

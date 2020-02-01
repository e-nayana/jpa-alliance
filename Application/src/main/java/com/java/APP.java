package com.java;

public class APP {

    public enum PRODUCT_TYPE{
        COMBO("combo"),
        SINGLE("single");


        private final String type;
        PRODUCT_TYPE(String type) {
            this.type = type;
        }
        public String getType(){
            return this.type;
        }
    }

}

package com.java.product.model;

import com.huston.springboot.crudgeneric.GenericCrudEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class ProductFeedback extends GenericCrudEntity<Integer> {

    @NotNull(message = "Product cannot be null")
    private int productId;

    @NotNull(message = "Order cannot be null")
    private String orderId;

    private String feedback;

    private int rate;


    public ProductFeedback() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}

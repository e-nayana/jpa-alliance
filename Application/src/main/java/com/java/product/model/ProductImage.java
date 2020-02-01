package com.java.product.model;

import com.huston.springboot.crudgeneric.GenericCrudEntity;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class ProductImage extends GenericCrudEntity<Integer> {

    @NotNull(message = "Product cannot be null")
    private int productId;

    @NotNull(message = "Type cannot be null")
    private String type;

    private int orderNumber;

    private String image;

    private double size;

    @Transient
    private String status;

    public ProductImage() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.java.brand.model;

import com.huston.springboot.crudgeneric.GenericCrudEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Brand extends GenericCrudEntity<Integer> {

    @NotNull(message = "Brand Name cannot be null")
    private String name;

    private String image;

    @Transient
    private String newImage;

    @Transient
    private String imageStatus;

    public Brand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNewImage() {
        return newImage;
    }

    public void setNewImage(String newImage) {
        this.newImage = newImage;
    }

    public String getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus;
    }
}

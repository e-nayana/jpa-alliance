package com.java.category.model;

import com.huston.springboot.crudgeneric.GenericCrudEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Category extends GenericCrudEntity<Integer> {

    @NotNull(message = "Category Name cannot be null")
    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "level")
    private int level;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "code")
    private String code;

    public Category() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

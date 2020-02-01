package com.auth.dto;

import java.util.List;

public class CompanyStructureDTO {

    private int id;
    private int levelId;
    private int parentId;
    private int status;
    private String name;
    private List<CompanyStructureDTO> children;

    public CompanyStructureDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CompanyStructureDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CompanyStructureDTO> children) {
        this.children = children;
    }
}

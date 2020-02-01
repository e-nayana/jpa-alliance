package com.auth.dto;

import java.sql.Timestamp;

public class BranchDTO {

    private int id;
    private String branchName;
    private String branchCode;
    private String haveBranch;
    private int isActive;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public BranchDTO() {
    }

    public BranchDTO(int id) {
        this.id = id;
    }

    public BranchDTO(int id, String branchName, String branchCode, int isActive, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.branchName = branchName;
        this.branchCode = branchCode;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public BranchDTO(int id, String branchName, String branchCode, String haveBranch, int isActive, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.branchName = branchName;
        this.branchCode = branchCode;
        this.haveBranch = haveBranch;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getHaveBranch() {
        return haveBranch;
    }

    public void setHaveBranch(String haveBranch) {
        this.haveBranch = haveBranch;
    }
}

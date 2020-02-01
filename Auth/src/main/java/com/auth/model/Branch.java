package com.auth.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @NotNull(message = "Branch Name cannot be null")
    @Column(name = "branch_name")
    private String branchName;

    @NotNull(message = "Branch code cannot be null")
    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "is_active")
    private int isActive;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Branch() {
    }

    public Branch(String branchName, String branchCode, int isActive) {
        this.branchName = branchName;
        this.branchCode = branchCode;
        this.isActive = isActive;
    }

    public Branch(String branchName, String branchCode, int isActive, Timestamp createdAt, Timestamp updated_At) {
        this.branchName = branchName;
        this.branchCode = branchCode;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updated_At;
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

}

package com.auth.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "company_profile")
public class CompanyProfile {

    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "Company Name cannot be null")
    private String companyName;

    @Email(message = "Company Email should be valid")
    private String companyEmail;

    @NotNull(message = "Company Address cannot be null")
    private String companyAddress;

    @Size(min = 9, message = "Office Number must be 10 numbers")
    private String officeNumber;

    @Size(min = 9, message = "Mobile Number must be 10 numbers")
    private String mobileNumber;

    @NotNull(message = "Contact Person cannot be null")
    private String contactPerson;

    private String currency;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    public CompanyProfile() {
    }

    public CompanyProfile(String companyName, String companyEmail, String companyAddress, String officeNumber, String mobileNumber, String contactPerson) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyAddress = companyAddress;
        this.officeNumber = officeNumber;
        this.mobileNumber = mobileNumber;
        this.contactPerson = contactPerson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

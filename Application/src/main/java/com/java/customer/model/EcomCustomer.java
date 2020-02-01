package com.java.customer.model;

import com.huston.springboot.crudgeneric.Alliance;
import com.huston.springboot.crudgeneric.GenericCrudEntity;
import com.huston.springboot.crudgeneric.WhereAlliance;
import com.java.address.model.Address;
import com.java.address.repository.AddressRepository;
import com.java.contact.model.Contact;
import com.java.contact.repository.ContactRepository;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "customer")
public class EcomCustomer extends GenericCrudEntity<Integer> {

    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "birthday")
    private Date birthday;

    @Transient
    @Alliance(repositoryType = AddressRepository.class, localKey = "id", foreignKey = "tableId")
    @WhereAlliance(filters = {"tableName = customer"}, allianceEntity = Address.class)
    private List<Address> customerAddress;

    @Transient
    @Alliance(repositoryType = ContactRepository.class, localKey = "id", foreignKey = "tableId")
    @WhereAlliance(filters = {"tableName = customer"}, allianceEntity = Address.class)
    private List<Contact> customerContact;

    @Override
    public String toString() {
        return "EcomCustomer{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", customerAddress=" + customerAddress +
                ", customerContact=" + customerContact +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Address> getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(List<Address> customerAddress) {
        this.customerAddress = customerAddress;
    }

    public List<Contact> getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(List<Contact> customerContact) {
        this.customerContact = customerContact;
    }
}

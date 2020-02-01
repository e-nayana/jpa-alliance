package com.java.address.model;


import com.huston.springboot.crudgeneric.GenericCrudEntity;

import javax.persistence.Entity;

@Entity
public class Address extends GenericCrudEntity<Integer> {

  private Integer tableId;
  private String tableName;
  private String address;
  private String contact;
  private String city;
  private Integer districtId;
  private String postalCode;

  public Integer getTableId() {
    return tableId;
  }

  public void setTableId(Integer tableId) {
    this.tableId = tableId;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Integer getDistrictId() {
    return districtId;
  }

  public void setDistrictId(Integer districtId) {
    this.districtId = districtId;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  @Override
  public String toString() {
    return "Address{" +
            "tableId=" + tableId +
            ", tableName='" + tableName + '\'' +
            ", address='" + address + '\'' +
            ", contact='" + contact + '\'' +
            ", city='" + city + '\'' +
            ", districtId=" + districtId +
            ", postalCode='" + postalCode + '\'' +
            '}';
  }
}

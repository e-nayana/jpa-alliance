package com.java.contact.model;

import com.huston.springboot.crudgeneric.GenericCrudEntity;

import javax.persistence.Entity;

@Entity
public class Contact extends GenericCrudEntity<Integer> {

  private Integer tableId;
  private String tableName;
  private String type;
  private String contact;

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  @Override
  public String toString() {
    return "Contact{" +
            "tableId=" + tableId +
            ", tableName='" + tableName + '\'' +
            ", type='" + type + '\'' +
            ", contact='" + contact + '\'' +
            '}';
  }
}

package com.java.module;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "log")
public class Log {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private int id;
  @Column(name = "user_email")
  private String userEmail;
  @Column(name = "java_class")
  private String javaClass;
  @Column(name = "java_method")
  private String javaMethod;
  @Column(name = "old_version")
  private String oldVersion;
  @Column(name = "current_version")
  private String currentVersion;
  @Column(name = "sql_table")
  private String sqlTable;
  private String action;

  @Column(name = "created_at", updatable = false)
  @CreationTimestamp
  private Timestamp createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Timestamp updatedAt;

  public Log() {
  }

  public Log(String userEmail, String javaClass, String javaMethod, String action, String oldVersion, String currentVersion, String sqlTable) {
    this.userEmail = userEmail;
    this.javaClass = javaClass;
    this.javaMethod = javaMethod;
    this.action = action;
    this.oldVersion = oldVersion;
    this.currentVersion = currentVersion;
    this.sqlTable = sqlTable;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getJavaClass() {
    return javaClass;
  }

  public void setJavaClass(String javaClass) {
    this.javaClass = javaClass;
  }


  public String getJavaMethod() {
    return javaMethod;
  }

  public void setJavaMethod(String javaMethod) {
    this.javaMethod = javaMethod;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getOldVersion() {
    return oldVersion;
  }

  public void setOldVersion(String oldVersion) {
    this.oldVersion = oldVersion;
  }


  public String getCurrentVersion() {
    return currentVersion;
  }

  public void setCurrentVersion(String currentVersion) {
    this.currentVersion = currentVersion;
  }


  public String getSqlTable() {
    return sqlTable;
  }

  public void setSqlTable(String sqlTable) {
    this.sqlTable = sqlTable;
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

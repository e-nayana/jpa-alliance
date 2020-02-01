package com.java.module;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "draft")
public class Draft {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private int id;

  @NotNull(message = "Draft type is required.!")
  @Column(name = "draft_type")
  private String draftType;

  @NotNull(message = "Draft json is required.!")
  @Column(name = "draft_json")
  private String draftJson;

  @NotNull(message = "Is active is required.!")
  @Column(name = "is_active")
  private int isActive;

  @Column(name = "created_at", updatable = false)
  @CreationTimestamp
  private Timestamp createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Timestamp updatedAt;

  public Draft() {
  }

  public Draft(@NotNull(message = "Draft type is required.!") String draftType, @NotNull(message = "Draft json is required.!") String draftJson, @NotNull(message = "Is active is required.!") int isActive, Timestamp createdAt, Timestamp updatedAt) {
    this.draftType = draftType;
    this.draftJson = draftJson;
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

  public String getDraftType() {
    return draftType;
  }

  public void setDraftType(String draftType) {
    this.draftType = draftType;
  }

  public String getDraftJson() {
    return draftJson;
  }

  public void setDraftJson(String draftJson) {
    this.draftJson = draftJson;
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

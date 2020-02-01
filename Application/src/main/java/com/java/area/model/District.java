package com.java.area.model;

import javax.persistence.*;

@Entity
@Table(name = "district")
public class District {

  @Id
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "province_id")
  private Province province;

  @Column(name = "code")
  private String code;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Province getProvince() {
    return province;
  }

  public void setProvince(Province province) {
    this.province = province;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}

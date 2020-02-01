package com.java.area.model;

import com.huston.springboot.crudgeneric.Alliance;
import com.huston.springboot.crudgeneric.GenericCrudEntity;
import com.java.area.repository.AreaRepository;
import com.java.area.repository.ServiceOfAreaRepository;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "area_service")
public class ServiceOfArea extends GenericCrudEntity<Integer> {

  @NotNull(message = "Area is required")
  @Column(name = "area_id")
  private int areaId;

  @Transient
  @Alliance(repositoryType = AreaRepository.class,localKey = "areaId", foreignKey = "id")
  @NotFound(action = NotFoundAction.IGNORE)
  private Area area;

  @NotNull(message = "Price is required")
  @Column(name = "price")
  private Double price;

  @NotNull(message = "Hour is required")
  @Column(name = "hour")
  private int hour;

  public int getAreaId() {
    return areaId;
  }

  public void setAreaId(int areaId) {
    this.areaId = areaId;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }
}

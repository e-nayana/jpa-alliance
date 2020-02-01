package com.java.area.model;

import com.huston.springboot.crudgeneric.Alliance;
import com.huston.springboot.crudgeneric.GenericCrudEntity;
import com.java.area.repository.ServiceOfAreaRepository;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "area")
public class Area extends GenericCrudEntity<Integer> {

  @NotEmpty(message = "Area name is required")
  @Column(name = "name")
  private String name;

  @NotNull(message = "District is required")
  @ManyToOne
  @JoinColumn(name = "district_id")
  private District district;

  @Transient
  @Alliance(repositoryType = ServiceOfAreaRepository.class, foreignKey = "areaId")
  @NotFound(action = NotFoundAction.IGNORE)
  private ServiceOfArea serviceOfArea;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public District getDistrict() {
    return district;
  }

  public void setDistrict(District district) {
    this.district = district;
  }

  public ServiceOfArea getServiceOfArea() {
    return serviceOfArea;
  }

  public void setServiceOfArea(ServiceOfArea serviceOfArea) {
    this.serviceOfArea = serviceOfArea;
  }
}

package com.java.area.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.area.model.ServiceOfArea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceOfAreaRepository extends GenericCrudRepository<ServiceOfArea, Long> {
  ServiceOfArea findFirstByAreaId(int areaId);
}

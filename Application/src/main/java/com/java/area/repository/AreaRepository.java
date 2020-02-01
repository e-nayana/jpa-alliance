package com.java.area.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.area.model.Area;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends GenericCrudRepository<Area, Long> {
  Area findFirstById(int id);
}

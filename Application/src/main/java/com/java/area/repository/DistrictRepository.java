package com.java.area.repository;

import com.java.area.model.District;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends CrudRepository<District , Long> {
  List<District> findAll();
}

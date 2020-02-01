package com.java.area.repository;

import com.java.area.model.Province;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends CrudRepository<Province, Long> {
  List<Province> findAll();
}

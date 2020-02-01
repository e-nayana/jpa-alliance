package com.java.brand.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.brand.model.Brand;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends GenericCrudRepository<Brand,Integer> {
//    List<Brand> findAllByIsActive(Boolean isActive);
}

package com.java.product.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.product.model.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends GenericCrudRepository<Product,Integer> {
    List<Product> findTop5ByBrandIdAndIsActive(int brandId, boolean isActive);
    List<Product> findAllByTypeAndIsActive(String type, boolean isActive);
}

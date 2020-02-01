package com.java.product.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.product.model.ProductImage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends GenericCrudRepository<ProductImage,Integer> {
    List<ProductImage> findAllByProductIdAndIsActive(int productId, boolean isActive);
    List<ProductImage> findAllByProductId(int productId);
}

package com.java.product.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.product.model.ProductVariation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariationRepository extends GenericCrudRepository<ProductVariation,Integer> {
    List<ProductVariation> findAllByProductId(int productId);
    void deleteAllByProductIdAndIdNotIn(int productId, List<Integer> ids);
    void removeProductVariationByProductIdAndIdNotIn(int productId, List<Integer> ids);
}

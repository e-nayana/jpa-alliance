package com.java.product.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.product.model.ProductFeedback;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFeedbackRepository extends GenericCrudRepository<ProductFeedback,Integer> {
}

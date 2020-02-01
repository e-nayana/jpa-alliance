package com.java.product.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.product.model.ComboChildProduct;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboChildProductRepository extends GenericCrudRepository<ComboChildProduct,Integer> {

  void removeAllByComboProductId(int comboProductId);
}

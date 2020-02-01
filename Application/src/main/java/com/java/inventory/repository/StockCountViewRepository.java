package com.java.inventory.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.inventory.model.StockCountView;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCountViewRepository extends GenericCrudRepository<StockCountView,Long> {

  StockCountView getFirstByProductIdAndProductVariationId(int productId , int productVariationId);

}

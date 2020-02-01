package com.java.order.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.order.model.CustomerOrderView;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderViewRepository extends GenericCrudRepository<CustomerOrderView, Integer> {
}

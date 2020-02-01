package com.java.order.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.order.model.CustomerOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends GenericCrudRepository<CustomerOrder,Integer> {

}

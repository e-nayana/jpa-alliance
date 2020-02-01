package com.java.order.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.order.model.CustomerOrderItem;

public interface OrderItemRepository extends GenericCrudRepository<CustomerOrderItem, Integer> {

}

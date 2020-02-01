package com.java.order.service;

import com.huston.springboot.crudgeneric.GenericCrudService;
import com.java.order.model.CustomerOrderItem;

import java.util.List;

public interface OrderItemService extends GenericCrudService<CustomerOrderItem> {
    void deleteBatch(List<CustomerOrderItem> customerOrderItemList);
}

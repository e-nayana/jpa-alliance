package com.java.order.service.impl;

import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.java.order.model.CustomerOrderItem;
import com.java.order.repository.OrderItemRepository;
import com.java.order.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl extends GenericCrudServiceImpl<OrderItemRepository, CustomerOrderItem> implements OrderItemService {

    public void deleteBatch(List<CustomerOrderItem> customerOrderItemList){
        repository.deleteInBatch(customerOrderItemList);
    }
}

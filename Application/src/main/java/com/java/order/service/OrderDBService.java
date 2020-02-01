package com.java.order.service;

import com.huston.rest.exception.AbnoramalBehaviorException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.rest.exception.SystemBussinessLogicException;
import com.huston.springboot.crudgeneric.GenericCrudService;
import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.order.model.CustomerOrder;
import com.java.product.model.Product;

public interface OrderDBService extends GenericCrudService<CustomerOrder> {
    CustomerOrder saveOrder(CustomerOrder order) throws CrudGenericException, ResourceNotFoundException, AllianceException, AbnoramalBehaviorException;
    CustomerOrder update(CustomerOrder newOrder) throws CrudGenericException;
    void deductProductsFromInventory(CustomerOrder order) throws SystemBussinessLogicException;
    void restoreProductsInInventory(CustomerOrder order) throws SystemBussinessLogicException;
}

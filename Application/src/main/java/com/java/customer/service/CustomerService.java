package com.java.customer.service;

import com.huston.springboot.crudgeneric.GenericCrudService;
import com.java.customer.model.EcomCustomer;

public interface CustomerService extends GenericCrudService<EcomCustomer> {

    EcomCustomer customerByUserId(int userId);
}

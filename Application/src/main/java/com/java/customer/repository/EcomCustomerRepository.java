package com.java.customer.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.customer.model.EcomCustomer;
import org.springframework.stereotype.Repository;

@Repository
public interface EcomCustomerRepository extends GenericCrudRepository<EcomCustomer,Integer> {

    /**
     * get customer details by user id
     */
    EcomCustomer findFirstByUserId(int userId);
}

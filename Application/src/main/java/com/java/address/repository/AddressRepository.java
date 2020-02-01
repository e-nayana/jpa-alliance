package com.java.address.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.address.model.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends GenericCrudRepository<Address,Integer> {

}

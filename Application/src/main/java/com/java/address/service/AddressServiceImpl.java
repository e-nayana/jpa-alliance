package com.java.address.service;

import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.java.address.model.Address;
import com.java.address.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends GenericCrudServiceImpl<AddressRepository, Address>  implements AddressService {
}

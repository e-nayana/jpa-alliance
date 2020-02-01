package com.java.contact.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.contact.model.Contact;

public interface ContactRepository extends GenericCrudRepository<Contact,Integer> {
}

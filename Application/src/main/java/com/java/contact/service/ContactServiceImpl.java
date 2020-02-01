package com.java.contact.service;

import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.java.contact.model.Contact;
import com.java.contact.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl extends GenericCrudServiceImpl<ContactRepository, Contact> implements ContactService {
}

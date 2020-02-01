package com.java.authentication.module;

import com.java.customer.model.EcomCustomer;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

public class PublicAuthenticateResponse implements Serializable {

    private final EcomCustomer customer;
    private final UserDetails user;

    public PublicAuthenticateResponse(UserDetails user, EcomCustomer customer) {
        this.customer = customer;
        this.user = user;
    }

    public UserDetails getUser() {
        return user;
    }

    public EcomCustomer getCustomer() {
        return customer;
    }
}

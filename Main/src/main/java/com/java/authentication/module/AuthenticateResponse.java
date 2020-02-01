package com.java.authentication.module;

import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

public class AuthenticateResponse implements Serializable {

    private final UserDetails user;

    public AuthenticateResponse(UserDetails userDet) {
        this.user = userDet;
    }

    public UserDetails getUser() {
        return user;
    }

}

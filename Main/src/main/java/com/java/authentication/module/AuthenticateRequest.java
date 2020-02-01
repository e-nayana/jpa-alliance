package com.java.authentication.module;

import java.io.Serializable;

public class AuthenticateRequest implements Serializable {

    private String email;
    private String password;

    public AuthenticateRequest() {
    }

    public AuthenticateRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

package com.java.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;

public class AuthUser implements UserDetails {


    private final Long id;
    private final String email;
    private final String username;
    private final String name;
    private final String password;
    private final String google_auth_token;
    private final boolean status;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Collection<? extends String> permissions;


    public AuthUser(Long id, String email, String name, String password, String google_auth_token, boolean status, Collection<? extends GrantedAuthority> authorities, Collection<? extends String> permissions) {
        this.id = id;
        this.email = email;
        this.username = email;
        this.name = name;
        this.password = password;
        this.google_auth_token = google_auth_token;
        this.status = status;
        this.authorities = authorities;
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getGoogle_auth_token() {
        return google_auth_token;
    }

    public boolean getStatus() {
        return status;
    }

    public Collection<? extends String> getPermissions() {
        return permissions;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }
}

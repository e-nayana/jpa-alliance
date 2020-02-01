package com.java.authentication.module;

import com.auth.model.Permission;
import com.auth.model.Role;
import com.auth.model.User;
import com.java.module.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class AuthUserFactory {

    public AuthUserFactory() {
    }

    public static AuthUser create(User user) {
        return new AuthUser(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getPassword(),
                user.getGoogleAuthToken(),
                user.getStatus(),
                mapToGrantedAuthorities(user.getRoles()),
                mapGrantedPermissions(user.getRoles())
        );
    }


    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    private static List<String> mapGrantedPermissions(List<Role> roles){
        List<Permission> permissions = roles.iterator().next().getPermissions();
        return permissions.stream()
                .map(permission -> new String(permission.getName()))
                .collect(Collectors.toList());
    }
}

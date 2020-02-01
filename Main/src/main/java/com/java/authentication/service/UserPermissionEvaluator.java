package com.java.authentication.service;

import com.auth.repository.UserPermissionRepository;
import com.java.module.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class UserPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Override
    public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
        List<String> permissions = AuthUserService.authUserPermissions();
        return permissions.stream().anyMatch(permissionName -> permissionName.trim().toLowerCase().equals(permission.toString().trim().toLowerCase()));
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        throw new RuntimeException("Id-based permission evaluation not currently supported.");
    }

}

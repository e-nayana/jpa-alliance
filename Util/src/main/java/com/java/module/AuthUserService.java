package com.java.module;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class AuthUserService implements InitializingBean {
    private static AuthUserService authUserService;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public void afterPropertiesSet() throws Exception {
        authUserService = this;
    }

    public static String authUserEmail(){
        KeycloakPrincipal authUser = AuthUserService.authPrinciple();
        return authUser.getKeycloakSecurityContext().getToken().getEmail();
    }

    private AuthUser authUserDetails(){
        String userEmail = AuthUserService.authUserEmail();
        return (AuthUser) userDetailsService.loadUserByUsername(userEmail);
    }

    private static KeycloakPrincipal authPrinciple(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (KeycloakPrincipal) auth.getPrincipal();
    }

    private static AccessToken authToken(){
        KeycloakPrincipal authUser = AuthUserService.authPrinciple();
        return authUser.getKeycloakSecurityContext().getToken();
    }

    public static String authTokenString(){
        KeycloakPrincipal authUser = AuthUserService.authPrinciple();
        return authUser.getKeycloakSecurityContext().getTokenString();
    }

    public static AuthProfile authProfile(){
        AccessToken accessToken = AuthUserService.authToken();
        Map<String, Object> otherClaims = accessToken.getOtherClaims();
        AuthProfile authProfile = new AuthProfile(accessToken.getId(),
                accessToken.getSessionState(),accessToken.getName(),accessToken.getGivenName(),accessToken.getFamilyName(),accessToken.getPreferredUsername(),accessToken.getEmail(),
                accessToken.getEmailVerified(), (String) otherClaims.getOrDefault("mobile",null),
                accessToken.getPhoneNumberVerified(),accessToken.getAuthTime(),accessToken.getUpdatedAt(),accessToken.getExpiration(),accessToken.getIssuedAt(),accessToken.getIssuer());
        return authProfile;
    }

    public static Integer authUserId(){
        AuthUser authUser = authUserService.authUserDetails();
        return (int) (long) authUser.getId();
    }

//    public static Integer authUserProfileId(){
//        AuthUser authUser = authUserService.authUserDetails();
//        return authUser.getProfileId();
//    }

    public static List<String> authUserRoles(){
        AuthUser authUser = authUserService.authUserDetails();
        Collection<? extends GrantedAuthority> authorities = authUser.getAuthorities();
        List<String> returningAuthorities = new ArrayList<>();
        authorities.stream().forEach(item -> {
            returningAuthorities.add(((GrantedAuthority) item).getAuthority());
        });
        return returningAuthorities;
    }

    public static List<String> authUserPermissions(){
        AuthUser authUser = authUserService.authUserDetails();
        Collection<? extends String> permissions = authUser.getPermissions();
        List<String> returningPermissions = new ArrayList<>();
        permissions.stream().forEach(item -> {
            returningPermissions.add((String) item);
        });
        return returningPermissions;
    }

    public static AuthUser authUser(){
        return authUserService.authUserDetails();
    }

    public static boolean isRole(String role){
        List<String> authorities = AuthUserService.authUserRoles();
        for(String authority:authorities){
            if(authority.equals(role)){
                return true;
            }
        }
        return false;
    }

}


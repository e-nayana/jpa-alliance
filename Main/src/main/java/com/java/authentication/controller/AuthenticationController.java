package com.java.authentication.controller;

import com.auth.model.Role;
import com.auth.model.User;
import com.auth.service.RoleService;
import com.auth.service.UserService;
import com.java.authentication.module.AuthenticateRequest;
import com.java.authentication.module.AuthenticateResponse;
import com.java.authentication.module.PublicAuthenticateResponse;
import com.java.config.AppConfig;
import com.java.customer.model.EcomCustomer;
import com.java.customer.service.CustomerService;
import com.java.module.AuthProfile;
import com.java.module.AuthUser;
import com.java.module.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AuthenticationController {

    private UserDetailsService userDetailsService;
    private RoleService roleService;
    private UserService userService;
    private CustomerService customerService;

    @Autowired
    public AuthenticationController(RoleService roleService, UserDetailsService userDetailsService, UserService userService, CustomerService customerService){
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.customerService = customerService;
    }

    @RequestMapping(path = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequest request) throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        AuthenticateResponse authenticateResponse = new AuthenticateResponse(userDetails);
        return ResponseEntity.ok(authenticateResponse);
    }

    @RequestMapping(path = "/authenticate-public", method = RequestMethod.POST)
    public ResponseEntity<?> authenticatePublic(@RequestBody AuthenticateRequest request) throws Exception {
        UserDetails userDetails;
        String userEmail;
        EcomCustomer customer;
        AuthUser authUser;
        try {
            userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            authUser = (AuthUser) userDetails;
            customer = customerService.customerByUserId(authUser.getId().intValue());
        } catch (UsernameNotFoundException e) {
            try {
                userEmail = this.registerPublic();
                userDetails = userDetailsService.loadUserByUsername(userEmail);
                authUser = (AuthUser) userDetails;
                customer = customerService.customerByUserId(authUser.getId().intValue());
            } catch (Exception ex) {
                throw new Exception("User registration failed");
            }
        }
        PublicAuthenticateResponse authenticateResponse = new PublicAuthenticateResponse(authUser, customer);
        return ResponseEntity.ok(authenticateResponse);
    }

    @Transactional(rollbackOn = Exception.class)
    public String registerPublic() throws Exception {

        AuthProfile authProfile = AuthUserService.authProfile();
        String roleName = AppConfig.PUBLIC_CUSTOMER_ROLE_NAME;
        String userName = authProfile.getFirstName() + " " + authProfile.getLastName();

        Role role = roleService.show(roleName);
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        User user = new User();
        user.setAuthUserId(authProfile.getKeycloakId());
        user.setEmail(authProfile.getEmail());
        user.setName(userName);
        user.setStatus(true);
        user.setRoles(roles);
        user = userService.create(user);

        EcomCustomer customer = new EcomCustomer();
        customer.setName(userName);
        customer.setUserId(Long.valueOf(user.getId().toString()).intValue());
        customerService.create(customer);

        return user.getEmail();
    }
}

package com.auth.controller;


import com.auth.service.RoleService;
import com.java.exception.DBValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping(path = {"/all"}, method = RequestMethod.GET)
    @PreAuthorize("hasPermission(#usuario,'view_role')")
    List allRoles() {
        return roleService.allRoles();
    }

    @RequestMapping(path = {"/roleList"}, method = RequestMethod.POST)
    @PreAuthorize("hasPermission(#usuario,'view_role')")
    ResponseEntity<?> roleList(@RequestBody Map<String, Object> request) {
        try {
            return new ResponseEntity<Object>(roleService.roleList(request), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(path = {"/createRole"}, method = RequestMethod.POST)
    @PreAuthorize("hasPermission(#usuario,'create_role')")
    ResponseEntity<?> createRole(@RequestBody Map<String, Object> request) {
        try {
            roleService.saveDetails((Map<String, Object>) request.get("roleDetails"));
            return new ResponseEntity<Object>(roleService.roleList(request), HttpStatus.OK);
        }catch (DBValidationException e){
            return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
        }catch (Exception e){
            return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(path = {"/editRole"}, method = RequestMethod.POST)
    @PreAuthorize("hasPermission(#usuario,'edit_role')")
    ResponseEntity<?> editRole(@RequestBody Map<String, Object> request) {
        try {
            roleService.saveDetails((Map<String, Object>) request.get("roleDetails"));
            return new ResponseEntity<Object>(roleService.roleList(request), HttpStatus.OK);
        }catch (DBValidationException e){
            return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
        }catch (Exception e){
            return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
        }

    }

}


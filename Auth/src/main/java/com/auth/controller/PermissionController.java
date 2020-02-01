package com.auth.controller;

import com.auth.service.PermissionService;
import com.java.exception.DBValidationException;
import com.java.service.Pagenator;
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
@RequestMapping(path = "/permissions")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @RequestMapping(path = "/all",method = RequestMethod.GET)
    public List allPermissions(){
        return permissionService.allPermissions();
    }


    @RequestMapping(value = {"/permissionList"}, method = RequestMethod.POST)
    @PreAuthorize("hasPermission(#usuario,'view_permission')")
    Pagenator.PagenatedObject permissionList(@RequestBody Map<String, Object> request) {
        return permissionService.permissionList(request);
    }

    @RequestMapping(value = {"/editPermission"}, method = RequestMethod.POST)
    @PreAuthorize("hasPermission(#usuario,'edit_permission')")
    ResponseEntity<?> editPermission(@RequestBody Map<String, Object> request) {
        try {
            permissionService.saveDetails((Map<String, Object>) request.get("permissionDetails"));
            return new ResponseEntity<Object>(permissionService.permissionList(request), HttpStatus.OK);
        }catch (DBValidationException e){
            return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
        }catch (Exception e){
            return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = {"/createPermission"}, method = RequestMethod.POST)
    @PreAuthorize("hasPermission(#usuario,'create_permission')")
    ResponseEntity<?> createPermission(@RequestBody Map<String, Object> request) {
        try {
            permissionService.saveDetails((Map<String, Object>) request.get("permissionDetails"));
            return new ResponseEntity<Object>(permissionService.permissionList(request), HttpStatus.OK);
        }catch (DBValidationException e){
            return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
        }catch (Exception e){
            return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
        }
    }
}

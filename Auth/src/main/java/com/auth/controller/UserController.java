package com.auth.controller;

import com.auth.dto.UserDTO;
import com.auth.service.UserService;
import com.java.exception.DBValidationException;
import com.java.service.Pagenator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/all"}, method = RequestMethod.GET)
    @PreAuthorize("hasPermission(#usuario,'view_user')")
    List allUsers() {
        return userService.allUsers();
    }

    @RequestMapping(value = {"/userDetails"}, method = RequestMethod.GET)
    @PreAuthorize("hasPermission(#usuario,'view_user')")
    UserDTO userDetails(@RequestParam Integer id) {
        return userService.userDetails(id);
    }

    @RequestMapping(value = {"/userList"}, method = RequestMethod.POST)
    @PreAuthorize("hasPermission(#usuario,'view_user')")
    Pagenator.PagenatedObject userList(@RequestBody Map<String, Object> request) {
        return userService.userList(request);
    }

    @RequestMapping(value = {"/createUser"}, method = RequestMethod.POST)
    @PreAuthorize("hasPermission(#usuario,'create_user')")
    public ResponseEntity<?> createUser(@RequestBody UserDTO request) {
        try {
            return new ResponseEntity<Object>(userService.saveDetails(request), HttpStatus.OK);
        }catch (DBValidationException e){
            return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
        }catch (Exception e){
            return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = {"/editUser"}, method = RequestMethod.POST)
    @PreAuthorize("hasPermission(#usuario,'edit_user')")
    public ResponseEntity editUser(@RequestBody UserDTO request) {
        try {
            return new ResponseEntity<Object>(userService.saveDetails(request), HttpStatus.OK);
        }catch (DBValidationException e){
            return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
        }catch (Exception e){
            return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
        }
    }

}

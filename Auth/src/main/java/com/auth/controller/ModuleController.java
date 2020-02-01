package com.auth.controller;

import com.auth.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/module")
public class ModuleController {

    @Autowired
    ModuleService moduleService;

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List allModules(){
        return moduleService.allModules();
    }

}

package com.auth.controller;

import com.auth.dto.ConfigurationDTO;
import com.auth.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/config")
public class ConfigurationController {

    @Autowired
    ConfigurationService configurationService;

    @RequestMapping(path = "/all-configurations", method = RequestMethod.GET)
    public List allConfigurations(){
        return configurationService.allConfigurations();
    }

    @RequestMapping(path = "/user-configuration", method = RequestMethod.GET)
    public List userConfiguration(){
        return configurationService.configurationsByType("user");
    }

    @RequestMapping(path = "/company-configuration", method = RequestMethod.GET)
    public List companyConfiguration(){
        return configurationService.configurationsByType("company");
    }

    @RequestMapping(path = "/update-configuration", method = RequestMethod.POST)
    public ConfigurationDTO updateConfiguration(@RequestBody ConfigurationDTO request){
        return configurationService.updateConfiguration(request);
    }
}

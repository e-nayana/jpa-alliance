package com.auth.service;

import com.auth.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    final static int ACTIVE = 1;

    @Autowired
    ModuleRepository moduleRepository;

    public List allModules(){
        return moduleRepository.findAllByIsActive(ACTIVE);
    }

}

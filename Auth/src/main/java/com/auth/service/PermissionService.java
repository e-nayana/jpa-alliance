package com.auth.service;


import com.auth.model.Permission;
import com.auth.repository.PermissionRepository;
import com.java.service.Pagenator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    public List allPermissions(){
        return permissionRepository.findAll();
    }

    public Pagenator.PagenatedObject permissionList(Map<String, Object> inputData) {
        inputData.putIfAbsent("page",1);
        inputData.putIfAbsent("perPage",10);
        Pageable pageable = Pagenator.setPagination((Integer) inputData.get("page"), (Integer) inputData.get("perPage"));
        Page<Permission> permissions = permissionRepository.findAllByOrderByIdDesc(pageable);
        Pagenator.PagenatedObject pagenatedObject = Pagenator.setMetaData(permissions);
        return pagenatedObject ;
    }

    public PermissionRepository saveDetails(Map<String, Object> inputData) throws Exception {

        Permission permission;

        if(inputData.get("id")!=null){
            permission = permissionRepository.findById((Integer) inputData.get("id"));
        }else{
            permission = new Permission();
        }

        permission.setName((String) inputData.get("name"));
        String displayName = "";
        String[] displayNameArray = null;
        if(((String) inputData.get("displayName")).isEmpty()){
            displayNameArray = ((String) inputData.get("name")).split("_");
        }else {
            displayNameArray = ((String) inputData.get("displayName")).split(" ");
        }
        for(String word:displayNameArray){
            displayName += " " + StringUtils.capitalize(word);
        }
        permission.setDisplayName(displayName);
        permission.setDescription((String) inputData.get("description"));
        permission.setModuleId((int) inputData.get("moduleId"));
        permissionRepository.save(permission);

        return permissionRepository;
    }

}


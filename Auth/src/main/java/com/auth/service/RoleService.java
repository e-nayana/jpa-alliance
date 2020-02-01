package com.auth.service;


import com.auth.model.Permission;
import com.auth.model.Role;
import com.auth.repository.RolePermissionRepository;
import com.auth.repository.RoleRepository;
import com.java.service.Pagenator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Math.toIntExact;


@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RolePermissionRepository rolePermissionRepository;

    public List allRoles(){
        return roleRepository.findAll();
    }

    public Pagenator.PagenatedObject roleList(Map<String, Object> inputData) throws Exception {
        inputData.putIfAbsent("page",1);
        inputData.putIfAbsent("perPage",10);
        Pageable pageable = Pagenator.setPagination((Integer) inputData.get("page"), (Integer) inputData.get("perPage"));
        Page<Role> users = roleRepository.findAllByOrderByIdDesc(pageable);
        Pagenator.PagenatedObject pagenatedObject = Pagenator.setMetaData(users);
        return pagenatedObject;
    }

    public RoleRepository saveDetails(Map<String, Object> inputData) throws Exception {

        Role role;

        if(inputData.get("id")!=null){
            role = roleRepository.findById((Integer) inputData.get("id"));
            rolePermissionRepository.removeByRoleId((Integer) inputData.get("id"));
        }else{
            role = new Role();
        }

        role.setName((String) inputData.get("name"));
        role.setDescription((String) inputData.get("description"));

        Map<String, Boolean> permissionList = (Map<String, Boolean>) inputData.get("permissions");
        List<Permission> allowedPermissions = new ArrayList<>();
        permissionList.forEach((key, value)->{
            if(value){
                allowedPermissions.add(new Permission(Long.valueOf(key)));
            }
        });

        role.setPermissions(allowedPermissions);
        roleRepository.save(role);

        return roleRepository;
    }

    public Role show(String roleName){
        return roleRepository.findByName(roleName);
    }

}

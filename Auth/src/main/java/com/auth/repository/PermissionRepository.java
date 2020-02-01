package com.auth.repository;


import com.auth.model.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findById(Integer id);
    Page<Permission> findAllByOrderByIdDesc(Pageable pageable);

}

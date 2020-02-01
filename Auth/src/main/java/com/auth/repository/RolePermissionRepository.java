package com.auth.repository;

import com.auth.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, String>
{
    RolePermission findByRoleId(Integer id);

    @Modifying
    @Transactional
    @Query("delete from RolePermission r where r.roleId = ?1")
    void removeByRoleId(Integer roleId);

}

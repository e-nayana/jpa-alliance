package com.auth.repository;

import com.auth.model.UserPermit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermit, String>
{
    public List<UserPermit> findByUserEmailAndPermissionName(String userEmail, String userPermission);
}

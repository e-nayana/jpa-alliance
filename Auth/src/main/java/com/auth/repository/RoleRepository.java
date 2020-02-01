package com.auth.repository;

import com.auth.model.Role;
import com.sun.mail.imap.protocol.ID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findById(Integer id);
    Role findByName(String roleName);
    Page<Role> findAllByOrderByIdDesc(Pageable pageable);

}
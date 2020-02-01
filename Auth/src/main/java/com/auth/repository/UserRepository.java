package com.auth.repository;

import com.auth.model.Role;

import com.auth.model.User;
import com.sun.mail.imap.protocol.ID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(Integer id);
    User findByEmail(String userName);
    Page<User> findAllByOrderByIdDesc(Pageable pageable);
//    List<User> findAllByRoles(List<Role> roles);
}

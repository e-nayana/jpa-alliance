package com.auth.repository;

import com.auth.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findById(Integer id);
    Profile findByEmail(String userName);
    Page<Profile> findAllByOrderByIdDesc(Pageable pageable);
}

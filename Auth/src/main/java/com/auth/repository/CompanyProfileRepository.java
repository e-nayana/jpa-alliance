package com.auth.repository;

import com.auth.model.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Integer> {

    CompanyProfile findAllById(Integer id);
}

package com.auth.repository;

import com.auth.model.CompanyLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyLevelRepository extends JpaRepository<CompanyLevel, Integer>{
}

package com.auth.repository;

import com.auth.model.CompanyStructureJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyStructureJsonRepository extends JpaRepository<CompanyStructureJson, Integer>{

    CompanyStructureJson findAllById(int id);
    CompanyStructureJson findAllByPredefined(int isPredefined);
}

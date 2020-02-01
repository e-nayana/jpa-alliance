package com.auth.repository;

import com.auth.model.CompanyStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyStructureRepository extends JpaRepository<CompanyStructure, Integer>{

    List<CompanyStructure> findAllByParentId(int parentId);

    List<CompanyStructure> findCompanyStructureByLevelId(int levelId);
    List<CompanyStructure> findCompanyStructureByLevelIdAndParentId(int levelId, int parentId);
}

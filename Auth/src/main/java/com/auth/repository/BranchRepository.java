package com.auth.repository;

import com.auth.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> , JpaSpecificationExecutor{

    List<Branch> findBranchesByIsActive(int isActivity);

    Branch findBranchesByBranchNameOrBranchCode(String brancName, String branchCode);
}
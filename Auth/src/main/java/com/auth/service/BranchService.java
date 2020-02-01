package com.auth.service;

import com.auth.dto.BranchDTO;
import com.auth.model.Branch;
import com.auth.repository.BranchRepository;
import com.java.service.Pagenator;
import com.java.service.ReturnResponse;
import com.java.service.ValidationCheck;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class BranchService {

    private static final String FILE_PATH = "Main/src/main/resources/static/public/uploads/";

    final static int ACTIVE = 1;

    @Autowired
    BranchRepository branchRepository;

    public List<Branch> allBranches(){
        return branchRepository.findBranchesByIsActive(ACTIVE);
    }

    public Pagenator.PagenatedObject branchesPagination(HashMap<String, Object> inputData){

        try {
            inputData.putIfAbsent("page",1);
            inputData.putIfAbsent("perPage",10);

            Pageable pageable = Pagenator.setPagination((Integer) inputData.get("page"), (Integer) inputData.get("perPage"));

//            Page<Branch> branches= branchRepository.findAll(new Specification<Branch>() {
//                @Override
//                public Predicate toPredicate(Root<Branch> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                    List<Predicate> predicates = new ArrayList<>();
//
//                    if (!branchName.equals("")) {
//                        predicates.add(criteriaBuilder.equal(root.get("branchName"), branchName));
//                    }
//
//                    if (!branchCode.equals("")) {
//                        predicates.add(criteriaBuilder.like(root.get("branchCode"), "%"+branchCode+"%"));
//                    }
//
//                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//                }
//            },pageable);

//            return Pagenator.setMetaData(branches);
            return null;
        } catch (NullPointerException err) {
            throw err;
        } catch (Exception err) {
            throw err;
        }

    }

    public HashMap<String, Object> uploadBranch(HashMap<String, Object> file) throws Exception {
        try {
            String fileName = FILE_PATH + String.valueOf(file.get("fileName"));

            FileInputStream brancExcel = new FileInputStream(new File(fileName));
            Workbook workbook = new XSSFWorkbook(brancExcel);
            Sheet dataTypesheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = dataTypesheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();

                if (!currentRow.getCell(0).toString().equals("Name") && !currentRow.getCell(0).toString().equals("Code")) {

                    Branch existBranch = branchRepository.findBranchesByBranchNameOrBranchCode(currentRow.getCell(0).toString(), currentRow.getCell(1).toString());

                    if (existBranch == null || existBranch.getIsActive() == 0) {
                        Branch branch = new Branch();
                        branch.setBranchName(currentRow.getCell(0).toString());
                        branch.setBranchCode(currentRow.getCell(1).toString());
                        branch.setIsActive(ACTIVE);
                        branchRepository.save(branch);
                    }
                }
            }

            return ReturnResponse.response("Branch excel upload successful", 200);
        }catch (Exception err) {
            throw err;
        }
    }
}

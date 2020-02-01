package com.auth.controller;

import com.auth.dto.CompanyLevelDTO;
import com.auth.dto.CompanyProfileDTO;
import com.auth.dto.CompanyStructureDTO;
import com.auth.service.CompanyProfileService;
import com.auth.service.ExceptionHandlingFile;
import com.java.service.ReturnResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/company-profile")
public class CompanyProfileController {

    public final static String ERROR_CREATE_COMPANY = "Error create company";
    public final static String ERROR_CREATE_COMPANY_STRUCTURE = "Error create company Structure";

    @Autowired
    CompanyProfileService companyProfileService;

    @RequestMapping(path = "/company-profile")
    public CompanyProfileDTO companyProfile(){
        return companyProfileService.companyProfile();
    }

    @RequestMapping(path = "/save-company")
    public HashMap<String, Object> saveCompany(@RequestBody final CompanyProfileDTO companyProfileDTO){
        try {
            return companyProfileService.saveCompany(companyProfileDTO);
        } catch (Exception err) {
            ExceptionHandlingFile.createException(err);
            return ReturnResponse.response(ERROR_CREATE_COMPANY, 409);
        }
    }

    @RequestMapping(path = "/save-company-structure", method = RequestMethod.POST)
    public HashMap<String, Object> saveCompanyStructure(@RequestBody List<CompanyStructureDTO> request) {
        try {
            return companyProfileService.saveCompanyStructure(request);
        } catch (Exception err) {
            ExceptionHandlingFile.createException(err);
            return ReturnResponse.response(ERROR_CREATE_COMPANY_STRUCTURE, 409);
        }
    }

    @RequestMapping(path = "/save-company-level", method = RequestMethod.POST)
    public HashMap<String, Object> saveCompanyLevels(@RequestBody List<CompanyLevelDTO> request){
        return companyProfileService.saveCompanyLevel(request);
    }

    @RequestMapping(path = "/save-company-structure-json", method = RequestMethod.POST)
    public HashMap<String, Object> saveCompanyStructureJson(@RequestBody List<CompanyStructureDTO> request){
        return companyProfileService.saveCompanyStructureJson(request);
    }

    @RequestMapping(path = "/company-hierarchy", method = RequestMethod.GET)
    public List<CompanyStructureDTO> companyHierarchy(){
        return companyProfileService.companyHierarchy();
    }

    @RequestMapping(path = "/all-levels", method = RequestMethod.GET)
    public List allLevels(){
        return companyProfileService.allLevels();
    }

    @RequestMapping(path = "/load-predefined-hierarchy", method = RequestMethod.GET)
    public List<CompanyLevelDTO> loadPredefinedHierarchy(@RequestParam int id){
        return companyProfileService.predefinedCompanyHierarchyJson(id);
    }
    @RequestMapping(path = "/company-level", method = RequestMethod.GET)
    public List<CompanyLevelDTO> getCompanyLevels(){

        return companyProfileService.getCompanyLevels();
    }

    @RequestMapping(path = "/top-level", method = RequestMethod.GET)
    public List<CompanyStructureDTO> getTopLevel(){
        return companyProfileService.getTopLevel();
    }

    @RequestMapping(path = "/other-level", method = RequestMethod.POST)
    public List<CompanyStructureDTO> getOtherLevel(@RequestBody HashMap<String, Integer> request){
        return companyProfileService.getOtherLevel(request);
    }

    @RequestMapping(path = "/cost-centers", method = RequestMethod.GET)
    public List getCostCenters(){
        return companyProfileService.getCostCenters();
    }
}

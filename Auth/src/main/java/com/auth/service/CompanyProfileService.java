package com.auth.service;

import com.auth.dto.CompanyLevelDTO;
import com.auth.dto.CompanyProfileDTO;
import com.auth.dto.CompanyStructureDTO;
import com.auth.model.CompanyLevel;
import com.auth.model.CompanyProfile;
import com.auth.model.CompanyStructure;
import com.auth.model.CompanyStructureJson;
import com.auth.repository.CompanyLevelRepository;
import com.auth.repository.CompanyProfileRepository;
import com.auth.repository.CompanyStructureJsonRepository;
import com.auth.repository.CompanyStructureRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.service.ReturnResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyProfileService {

    public final static String SUCCESS_CREATE_COMPANY = "Successful create company";
    public final static String SUCCESS_CREATE_COMPANY_STRUCTURE = "Successful create company structure";
    public final static String SUCCESS_CREATE_COMPANY_STRUCTURE_JSON = "Successful saved company structure";

    public final static int ACTIVE = 1;
    public final static int INACTIVE = 0;
    public final static int TOP_LEVEL = 1;

    @Autowired
    CompanyProfileRepository companyProfileRepository;

    @Autowired
    CompanyStructureRepository companyStructureRepository;

    @Autowired
    CompanyStructureJsonRepository companyStructureJsonRepository;

    @Autowired
    CompanyLevelRepository companyLevelRepository;

    @Autowired
    ModelMapper modelMapper;

    public CompanyProfileDTO companyProfile(){
        CompanyProfile companyProfile = companyProfileRepository.findAllById(1);
        return modelMapper.map(companyProfile, CompanyProfileDTO.class);
    }

    public List<CompanyStructureDTO> companyHierarchyJson(){

        CompanyStructureJson companyStructureJson = companyStructureJsonRepository.findAllByPredefined(0);
        ObjectMapper mapper = new ObjectMapper();
        List<CompanyStructureDTO> hierarchy = new ArrayList<CompanyStructureDTO>();

        try {
            hierarchy = mapper.readValue(companyStructureJson.getJson(), new TypeReference<List<CompanyStructureDTO>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hierarchy;

    }

    public List<CompanyLevelDTO> getCompanyLevels() {
        try {
            List<CompanyLevel> companyLevelList = companyLevelRepository.findAll();
            return companyLevelList.stream()
                    .map(level -> modelMapper.map(level, CompanyLevelDTO.class))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CompanyStructureDTO> getTopLevel() {
        try {
            List<CompanyStructure> companyStructures = companyStructureRepository.findCompanyStructureByLevelId(TOP_LEVEL);
            return companyStructures.stream()
                    .map(level -> modelMapper.map(level, CompanyStructureDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CompanyStructureDTO> getOtherLevel(HashMap<String, Integer> request) {
        try {
            int levelNumber = request.get("levelNumber");
            int structureId = request.get("structureId");

            List<CompanyStructure> companyStructures = companyStructureRepository.findCompanyStructureByLevelIdAndParentId(levelNumber + 1, structureId);
            return companyStructures.stream()
                    .map(level -> modelMapper.map(level, CompanyStructureDTO.class))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CompanyLevelDTO> predefinedCompanyHierarchyJson(int id){

        CompanyStructureJson companyStructureJson = companyStructureJsonRepository.findAllById(id);
        ObjectMapper mapper = new ObjectMapper();
        List<CompanyLevelDTO> hierarchy = new ArrayList<CompanyLevelDTO>();

        try {
            hierarchy = mapper.readValue(companyStructureJson.getJson(), new TypeReference<List<CompanyLevelDTO>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hierarchy;

    }

    public List<CompanyStructureDTO> companyHierarchy(){

        List<CompanyStructure> companyStructure = companyStructureRepository.findAllByParentId(0);

        if(companyStructure.size()==0){
            return this.companyHierarchyJson();
        }

        List<CompanyStructureDTO> hierarchy = new ArrayList<CompanyStructureDTO>();

        hierarchy = companyStructure.stream()
                .map(level -> modelMapper.map(level, CompanyStructureDTO.class))
                .collect(Collectors.toList());

        return hierarchy;

    }

    public HashMap<String, Object> saveCompany(CompanyProfileDTO companyProfileDTO) throws Exception {
        try {
//            CompanyProfile companyProfile = new CompanyProfile(companyProfileDTO.getCompanyName()
//                    , companyProfileDTO.getCompanyEmail()
//                    , companyProfileDTO.getCompanyAddress()
//                    , companyProfileDTO.getOfficeNumber()
//                    , companyProfileDTO.getMobileNumber()
//                    , companyProfileDTO.getContactPerson());
//
//            if (companyProfileDTO.getId() != 0) {
//                companyProfile.setId(companyProfileDTO.getId());
//            }
//
//            String validationError = ValidationCheck.validator(companyProfile);
//
//            if (!validationError.equals("")) {
//                return ReturnResponse.response(validationError, 406);
//            }

            companyProfileRepository.save(modelMapper.map(companyProfileDTO, CompanyProfile.class));

            return ReturnResponse.response(SUCCESS_CREATE_COMPANY, 200);
        } catch (Exception err) {
            throw err;
        }
    }

    public HashMap<String, Object> saveCompanyStructureJson(List<CompanyStructureDTO> inputData){

        ObjectMapper mapper = new ObjectMapper();
        CompanyStructureJson companyStructureJson;
        try {
            String companyStructureJsonString = mapper.writeValueAsString(inputData);

            companyStructureJson = companyStructureJsonRepository.findAllByPredefined(0);

            if(companyStructureJson==null){
                companyStructureJson = new CompanyStructureJson();
            }
            companyStructureJson.setJson(companyStructureJsonString);
            companyStructureJsonRepository.save(companyStructureJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ReturnResponse.response(SUCCESS_CREATE_COMPANY_STRUCTURE_JSON, 200);
    }

    public HashMap<String, Object> saveCompanyStructure(List<CompanyStructureDTO> request) throws Exception {

        this.saveCompanyStructureJson(request);
        try {
            int parentId = 0;
            this.saveCompanyHierarchy(request,parentId);

            return ReturnResponse.response(SUCCESS_CREATE_COMPANY_STRUCTURE, 200);
        } catch (Exception err) {
            throw err;
        }
    }

    private void saveCompanyHierarchy(List<CompanyStructureDTO> request, int parentId) throws Exception {
        this.saveCompanyStructureJson(request);
        try {

            CompanyStructure savedCompanyStructure = new CompanyStructure();

            for (CompanyStructureDTO companyStructureDto: request) {

                companyStructureDto.setParentId(parentId);
                savedCompanyStructure = companyStructureRepository.save(modelMapper.map(companyStructureDto, CompanyStructure.class));

            }

        } catch (Exception err) {
            throw err;
        }
    }

    public HashMap<String, Object> saveCompanyLevel(List<CompanyLevelDTO> companyLevelDTOs){

        for(CompanyLevelDTO companyLevelDTO:companyLevelDTOs){
            companyLevelRepository.save(modelMapper.map(companyLevelDTO, CompanyLevel.class));
        }

        return ReturnResponse.response(SUCCESS_CREATE_COMPANY_STRUCTURE_JSON, 200);

    }

    public List allLevels(){
        return companyLevelRepository.findAll();
    }

    public List getCostCenters(){
        return companyStructureRepository.findAll();
    }

}

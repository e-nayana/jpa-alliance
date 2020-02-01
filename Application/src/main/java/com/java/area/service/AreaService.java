package com.java.area.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.springboot.crudgeneric.GenericCrudService;
import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.huston.springboot.crudgeneric.pagination.ResultPage;
import com.java.area.model.Area;
import com.java.area.model.District;
import com.java.area.model.Province;
import com.java.order.ControllerRequestCriteria;

import java.util.List;

public interface AreaService extends GenericCrudService<Area> {
  List<Province> getProvinces();

  List<District> getDistricts();

  List<Area> searchArea(Area area) throws AllianceException, CrudGenericException;

  Area findArea(int id) throws AllianceException, CrudGenericException;

  Area createArea(Area area) throws AllianceException, CrudGenericException;

  Area updateArea(Area area) throws AllianceException, CrudGenericException;

  ResultPage search(ControllerRequestCriteria controllerRequestCriteria, String... alliancesFields) throws ResourceNotFoundException, JsonProcessingException;
}

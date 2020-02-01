package com.java.area.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.springboot.crudgeneric.GenericCrudService;
import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.huston.springboot.crudgeneric.pagination.ResultPage;
import com.java.area.model.ServiceOfArea;
import com.java.order.ControllerRequestCriteria;

import java.util.List;

public interface ServiceOfAreaService extends GenericCrudService<ServiceOfArea> {

  List<ServiceOfArea> searchServiceOfArea(ServiceOfArea serviceOfArea) throws AllianceException, CrudGenericException;

  ServiceOfArea createServiceOfArea(ServiceOfArea serviceOfArea) throws AllianceException, CrudGenericException;

  ServiceOfArea updateServiceOfArea(ServiceOfArea serviceOfArea) throws AllianceException, CrudGenericException;

  ServiceOfArea findByAreaId(int areaId);

  ResultPage search(ControllerRequestCriteria controllerRequestCriteria, String... alliancesFields) throws ResourceNotFoundException, JsonProcessingException;
}

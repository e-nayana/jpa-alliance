package com.java.area.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.huston.springboot.crudgeneric.pagination.ResultPage;
import com.java.area.model.Area;
import com.java.area.model.ServiceOfArea;
import com.java.area.repository.AreaRepository;
import com.java.area.repository.ServiceOfAreaRepository;
import com.java.order.ControllerRequestCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ServiceOfAreaServiceImpl extends GenericCrudServiceImpl<ServiceOfAreaRepository,ServiceOfArea> implements ServiceOfAreaService {

  private ServiceOfAreaRepository serviceOfAreaRepository;
  private AreaRepository areaRepository;

  public ServiceOfAreaServiceImpl(ServiceOfAreaRepository serviceOfAreaRepository , AreaRepository areaRepository) {
    this.serviceOfAreaRepository = serviceOfAreaRepository;
    this.areaRepository = areaRepository;
  }

  @Override
  public List<ServiceOfArea> searchServiceOfArea(ServiceOfArea serviceOfArea) throws AllianceException, CrudGenericException{
    List<ServiceOfArea> serviceOfAreas = activeList("area");
    return serviceOfAreas;
  }

  @Override
  public ServiceOfArea createServiceOfArea(ServiceOfArea serviceOfArea) throws AllianceException, CrudGenericException {
    serviceOfArea = save(serviceOfArea);
    return serviceOfArea;
  }

  @Transactional
  @Override
  public ServiceOfArea updateServiceOfArea(ServiceOfArea serviceOfArea) throws AllianceException, CrudGenericException {

    if(serviceOfArea.getIsActive()){
      Area area = this.areaRepository.findFirstById(serviceOfArea.getAreaId());
      area.setIsActive(true);
      this.areaRepository.save(area);
      this.areaRepository.flush();
    }

    ServiceOfArea serviceOfArea1 = update(serviceOfArea);

    return null;
  }

  @Override
  public ServiceOfArea findByAreaId(int areaId) {
    return serviceOfAreaRepository.findFirstByAreaId(areaId);
  }

  @Override
  public ResultPage search(ControllerRequestCriteria controllerRequestCriteria, String... alliancesFields) throws ResourceNotFoundException, JsonProcessingException {
    return page(controllerRequestCriteria.getCurrentPage(), controllerRequestCriteria.getPerPage(), searchingCriteria(controllerRequestCriteria.getSearchCriteria()),alliancesFields);
  }

}

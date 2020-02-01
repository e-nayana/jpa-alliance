package com.java.area.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.huston.springboot.crudgeneric.pagination.ResultPage;
import com.java.area.model.Area;
import com.java.area.model.District;
import com.java.area.model.Province;
import com.java.area.model.ServiceOfArea;
import com.java.area.repository.AreaRepository;
import com.java.area.repository.DistrictRepository;
import com.java.area.repository.ProvinceRepository;
import com.java.area.repository.ServiceOfAreaRepository;
import com.java.order.ControllerRequestCriteria;
import org.springframework.beans.factory.annotation.Autowired;
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
@Transactional
public class AreaServiceImpl extends GenericCrudServiceImpl<AreaRepository,Area> implements AreaService {

  private ProvinceRepository provinceRepository;
  private DistrictRepository districtRepository;
  private ServiceOfAreaRepository serviceOfAreaRepository;

  @Autowired
  public AreaServiceImpl(ProvinceRepository provinceRepository, DistrictRepository districtRepository , ServiceOfAreaRepository serviceOfAreaRepository) {
    this.provinceRepository = provinceRepository;
    this.districtRepository = districtRepository;
    this.serviceOfAreaRepository = serviceOfAreaRepository;
  }

  @Override
  public List<Province> getProvinces(){
    return  this.provinceRepository.findAll();
  }

  @Override
  public List<District> getDistricts(){
    return this.districtRepository.findAll();
  }

  @Override
  public List<Area> searchArea(Area area) throws AllianceException, CrudGenericException{
    List<Area> areas =  activeList("serviceOfArea");
    return areas;
  }

  @Override
  public Area findArea(int id) throws AllianceException, CrudGenericException {
    Area area = show(id,"serviceOfArea");
    return area;
  }

  @Transactional
  @Override
  public Area createArea(Area area) throws AllianceException, CrudGenericException{
    area = save(area);

    //auth add disabled service of area
    ServiceOfArea serviceOfArea = new ServiceOfArea();
    serviceOfArea.setAreaId(area.getId());
    serviceOfArea.setHour(0);
    serviceOfArea.setPrice(0.00);
    serviceOfArea.setIsActive(false);

    serviceOfAreaRepository.save(serviceOfArea);
    //
    return area;
  }

  @Transactional
  @Override
  public Area updateArea(Area area) throws AllianceException, CrudGenericException {
    Area area1 = update(area);

    if(!area.getIsActive()){
      ServiceOfArea serviceOfArea = serviceOfAreaRepository.findFirstByAreaId(area.getId());
      serviceOfArea.setIsActive(false);
      serviceOfAreaRepository.save(serviceOfArea);
    }
    return  area1;
  }

  @Override
  public ResultPage search(ControllerRequestCriteria controllerRequestCriteria, String... alliancesFields) throws ResourceNotFoundException, JsonProcessingException {
    return page(controllerRequestCriteria.getCurrentPage(), controllerRequestCriteria.getPerPage(), searchingCriteria(controllerRequestCriteria.getSearchCriteria()),alliancesFields);
  }


}

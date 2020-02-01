package com.java.area.controller;

import com.huston.rest.response.RESTResponse;
import com.huston.rest.response.RESTResponseManager;
import com.java.area.model.ServiceOfArea;
import com.java.area.service.ServiceOfAreaService;
import com.java.order.ControllerRequestCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("area_service")
public class ServiceOfAreaController  {

  private ServiceOfAreaService serviceOfAreaService;

  @Autowired
  public ServiceOfAreaController(ServiceOfAreaService serviceOfAreaService) {
    this.serviceOfAreaService = serviceOfAreaService;
  }

  @PostMapping("/search")
  public RESTResponse searchServiceOfArea(@RequestBody ControllerRequestCriteria paginationCriteria) throws Exception {

    Object serviceOfAreas = serviceOfAreaService.search(paginationCriteria , "area");

    RESTResponse response =  RESTResponseManager
      .headerBuilder(HttpStatus.ACCEPTED)
      .bodyBuilder()
      .setBodyAttribute("serviceOfAreas", serviceOfAreas)
      .responseBuilder()
      .build();
    return response;
  }

  @PostMapping()
  public RESTResponse createServiceOfArea(@Valid @RequestBody ServiceOfArea serviceOfArea) throws Exception {
      ServiceOfArea newServiceOfArea = this.serviceOfAreaService.createServiceOfArea(serviceOfArea);
      RESTResponse response =  RESTResponseManager
        .headerBuilder(HttpStatus.ACCEPTED)
        .bodyBuilder()
        .setBodyAttribute("serviceOfArea", newServiceOfArea)
        .responseBuilder()
        .build();
      return response;
  }

  @PutMapping("/{id}")
  public RESTResponse updateServiceOfArea(@PathVariable int id , @Valid @RequestBody ServiceOfArea serviceOfArea) throws Exception {
      ServiceOfArea updatedArea = this.serviceOfAreaService.updateServiceOfArea(serviceOfArea);
      RESTResponse response =  RESTResponseManager
        .headerBuilder(HttpStatus.ACCEPTED)
        .bodyBuilder()
        .setBodyAttribute("serviceOfArea", updatedArea)
        .responseBuilder()
        .build();
    return response;
  }
}

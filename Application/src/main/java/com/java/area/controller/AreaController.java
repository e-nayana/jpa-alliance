package com.java.area.controller;

import com.huston.rest.response.RESTResponse;
import com.huston.rest.response.RESTResponseManager;
import com.java.area.model.Area;
import com.java.area.model.District;
import com.java.area.model.Province;
import com.java.area.service.AreaService;
import com.java.dto.PaginatedListRequest;
import com.java.order.ControllerRequestCriteria;
import com.java.service.Pagenator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("area")
public class AreaController {

  private AreaService areaService;//

  public AreaController(AreaService areaService) {
    this.areaService = areaService;
  }

  @GetMapping("provinces")
  public RESTResponse getProvinces() throws Exception{
    List<Province> provinces = areaService.getProvinces();
    RESTResponse response =  RESTResponseManager
      .headerBuilder(HttpStatus.ACCEPTED)
      .bodyBuilder()
      .setBodyAttribute("provinces", provinces)
      .responseBuilder()
      .build();
    return response;
  }

  @GetMapping("districts")
  public RESTResponse getDistrict() throws Exception{
    List<District> districts = areaService.getDistricts();
    RESTResponse response =  RESTResponseManager
      .headerBuilder(HttpStatus.ACCEPTED)
      .bodyBuilder()
      .setBodyAttribute("districts", districts)
      .responseBuilder()
      .build();
    return response;
  }

  @PostMapping()
  public RESTResponse createArea(@Valid @RequestBody Area area) throws Exception{
      Area createdArea = areaService.createArea(area);
      RESTResponse response =  RESTResponseManager
        .headerBuilder(HttpStatus.ACCEPTED)
        .bodyBuilder()
        .setBodyAttribute("area", createdArea)
        .responseBuilder()
        .build();
    return response;
  }

  @GetMapping("{id}")
  public RESTResponse findArea(@PathVariable int id) throws Exception{
    Area area = areaService.findArea(id);
    RESTResponse response =  RESTResponseManager
      .headerBuilder(HttpStatus.ACCEPTED)
      .bodyBuilder()
      .setBodyAttribute("area", area)
      .responseBuilder()
      .build();
    return response;
  }

  @PutMapping(path = "{id}")
  public RESTResponse updateArea(@PathVariable int id,@Valid @RequestBody Area area) throws Exception {
    Area updatedArea = areaService.updateArea(area);
    RESTResponse response =  RESTResponseManager
      .headerBuilder(HttpStatus.ACCEPTED)
      .bodyBuilder()
      .setBodyAttribute("area", area)
      .responseBuilder()
      .build();
    return response;
  }

  @GetMapping("/select-list")
  public RESTResponse getSelectList() throws Exception {

    List<Area> areas = areaService.list();

    RESTResponse response =  RESTResponseManager
      .headerBuilder(HttpStatus.ACCEPTED)
      .bodyBuilder()
      .setBodyAttribute("areas", areas)
      .responseBuilder()
      .build();
    return response;
  }

  @PostMapping(path = "search")
  public RESTResponse searchArea(@RequestBody ControllerRequestCriteria paginationCriteria) throws Exception{

    Object areas = areaService.search(paginationCriteria);
    return RESTResponseManager.headerBuilder(HttpStatus.OK)
      .bodyBuilder()
      .setBodyAttribute("areas",areas)
      .responseBuilder()
      .build();
  }

  @GetMapping(path = "no-auth")
  public RESTResponse areasNoAuth(){
    List<Area> areas = areaService.activeList("serviceOfArea");
    RESTResponse response =  RESTResponseManager
            .headerBuilder(HttpStatus.ACCEPTED)
            .bodyBuilder()
            .setBodyAttribute("result", areas)
            .responseBuilder()
            .build();
    return response;
  }




}

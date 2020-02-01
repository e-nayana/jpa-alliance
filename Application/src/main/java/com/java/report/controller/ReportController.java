package com.java.report.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.rest.response.RESTResponse;
import com.huston.rest.response.RESTResponseManager;
import com.java.order.ControllerRequestCriteria;
import com.java.inventory.service.StockCountViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reports")
public class ReportController {


  @Autowired
  public ReportController(StockCountViewService stockCountViewService) {
  }

}

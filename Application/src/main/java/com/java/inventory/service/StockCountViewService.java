package com.java.inventory.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.springboot.crudgeneric.GenericCrudService;
import com.huston.springboot.crudgeneric.pagination.ResultPage;
import com.java.order.ControllerRequestCriteria;
import com.java.inventory.model.StockCountView;

public interface StockCountViewService extends GenericCrudService<StockCountView> {
  ResultPage search(ControllerRequestCriteria controllerRequestCriteria, String... alliancesFields) throws ResourceNotFoundException, JsonProcessingException;
  StockCountView getStockCountViewOfProduct(StockCountView stockCountView);
}

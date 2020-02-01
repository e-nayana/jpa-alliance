package com.java.inventory.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.huston.springboot.crudgeneric.pagination.ResultPage;
import com.java.order.ControllerRequestCriteria;
import com.java.inventory.model.StockCountView;
import com.java.inventory.repository.StockCountViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockCountViewServiceImpl extends GenericCrudServiceImpl<StockCountViewRepository, StockCountView> implements StockCountViewService{

  private StockCountViewRepository stockCountViewRepository;

  @Autowired
  public StockCountViewServiceImpl(StockCountViewRepository stockCountViewRepository) {
    this.stockCountViewRepository = stockCountViewRepository;
  }

  @Override
  public ResultPage search(ControllerRequestCriteria controllerRequestCriteria, String... alliancesFields) throws ResourceNotFoundException, JsonProcessingException {
    return page(controllerRequestCriteria.getCurrentPage(), controllerRequestCriteria.getPerPage(), searchingCriteria(controllerRequestCriteria.getSearchCriteria()),alliancesFields);
  }

  @Override
  public StockCountView getStockCountViewOfProduct(StockCountView stockCountView){
    return this.stockCountViewRepository.getFirstByProductIdAndProductVariationId(
      stockCountView.getProductId(),stockCountView.getProductVariationId()
    );
  }

}

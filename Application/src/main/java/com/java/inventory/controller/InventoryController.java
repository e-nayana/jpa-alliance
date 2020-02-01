package com.java.inventory.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.rest.response.RESTResponse;
import com.huston.rest.response.RESTResponseManager;
import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.dto.PaginatedListRequest;
import com.java.inventory.model.StockCountView;
import com.java.inventory.service.InventoryService;
import com.java.inventory.service.StockCountViewService;
import com.java.order.ControllerRequestCriteria;
import com.java.service.LoggerFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "inventory")
public class InventoryController {

    private InventoryService inventoryService;
    private StockCountViewService stockCountViewService;

  @Autowired
  public InventoryController(InventoryService inventoryService, StockCountViewService stockCountViewService) {
    this.inventoryService = inventoryService;
    this.stockCountViewService = stockCountViewService;
  }

  /**
     * get paginated record list
     * @return Pagenator.PagenatedObject
     */
    @RequestMapping(path = "list", method = RequestMethod.POST)
    public ResponseEntity<?> list(@RequestBody PaginatedListRequest request){
        return new ResponseEntity<>(inventoryService.list(request.getPage(),request.getPerPage(), request.getFilter()), HttpStatus.OK);
    }

    @RequestMapping(path = "/inventory-summary", method = RequestMethod.GET)
    public ResponseEntity<?> inventorySummary() {
        try {
            return new ResponseEntity<>(inventoryService.getInventorySummary(), HttpStatus.OK);
        } catch (Exception e) {
            new LoggerFile().createLog(this.getClass(),"inventorySummary","error","inventory Summary error.", LoggerFile.createLogDetailsFromException(e));
            return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(path = "/inventory-summary-for-ebay-stock-update", method = RequestMethod.GET)
    public ResponseEntity<?> inventorySummaryForEbayStockUpdate() {
        try {
            return new ResponseEntity<Object>(inventoryService.inventorySummaryForEbayStockUpdate(), HttpStatus.OK);
        } catch (Exception e) {
            new LoggerFile().createLog(this.getClass(),"inventorySummaryForEbayStockUpdate",
                    "error","inventory Summary for ebay stock update error.",LoggerFile.createLogDetailsFromException(e));
            return new ResponseEntity<Object>(e, HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public RESTResponse getStockCount(){
      RESTResponse response =  RESTResponseManager
        .headerBuilder(HttpStatus.ACCEPTED)
        .bodyBuilder()
        .setBodyAttribute("areas", "")
        .responseBuilder()
        .build();
      return response;
    }

    @GetMapping("stock-count-list")
    public RESTResponse getStockCountViewData(ControllerRequestCriteria paginationCriteria) throws ResourceNotFoundException, JsonProcessingException {
      Object stockCountViewSearchPaginateData = this.stockCountViewService.search(paginationCriteria);
      RESTResponse response =  RESTResponseManager
        .headerBuilder(HttpStatus.ACCEPTED)
        .bodyBuilder()
        .setBodyAttribute("stockCountViewData", stockCountViewSearchPaginateData)
        .responseBuilder()
        .build();
      return response;
    }

    @GetMapping("stock-count/no-auth")
    public RESTResponse getStockCountViewItem(StockCountView stockCountView) throws ResourceNotFoundException, JsonProcessingException {
      StockCountView stockCountView1 = this.stockCountViewService.getStockCountViewOfProduct(stockCountView);
      RESTResponse response =  RESTResponseManager
        .headerBuilder(HttpStatus.ACCEPTED)
        .bodyBuilder()
        .setBodyAttribute("result", stockCountView1)
        .responseBuilder()
        .build();
      return response;
    }

}

package com.java.order.controller;

import com.huston.rest.response.RESTResponse;
import com.huston.rest.response.RESTResponseManager;
import com.java.order.ControllerRequestCriteria;
import com.java.order.model.CustomerOrder;
import com.java.order.service.CustomerOrderViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "customer-orders")
public class CustomerOrderController {

  private CustomerOrderViewService customerOrderViewService;

  @Autowired
  public CustomerOrderController(CustomerOrderViewService customerOrderViewService) {
    this.customerOrderViewService = customerOrderViewService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public RESTResponse getList(ControllerRequestCriteria paginationCriteria) throws Exception{
      return RESTResponseManager.headerBuilder(HttpStatus.OK)
              .bodyBuilder()
              .setBodyAttribute("result",new CustomerOrder().getOrderState().getActiveListForAuthenticatedCustomer(paginationCriteria.getCurrentPage(), paginationCriteria.getPerPage()))
              .responseBuilder()
              .build();
  }

  @GetMapping("{orderId}")
  public RESTResponse getOrder(@PathVariable int orderId) throws Exception{
    return RESTResponseManager.headerBuilder(HttpStatus.OK)
      .bodyBuilder()
      .setBodyAttribute("order", new CustomerOrder().getOrderState().get(orderId))
      .responseBuilder().build();
  }


}

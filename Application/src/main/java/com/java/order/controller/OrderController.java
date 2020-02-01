package com.java.order.controller;

import com.huston.rest.response.RESTResponse;
import com.huston.rest.response.RESTResponseManager;
import com.java.order.ControllerRequestCriteria;
import com.java.order.model.CustomerOrder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "orders")
public class OrderController {

  @RequestMapping(method = RequestMethod.GET)
  public RESTResponse orderList(ControllerRequestCriteria controllerRequestCriteria){

    return RESTResponseManager
      .headerBuilder(HttpStatus.ACCEPTED)
      .bodyBuilder()
      .setBodyAttribute("result", new CustomerOrder().getOrderState().getActiveList(controllerRequestCriteria.getCurrentPage(), controllerRequestCriteria.getPerPage()))
      .responseBuilder()
      .build();
  }

  @RequestMapping(value = "{order_id}", method = RequestMethod.GET)
  public RESTResponse getOrder(@PathVariable(name = "order_id") Integer orderId) throws Exception{

    RESTResponse response =  RESTResponseManager
      .headerBuilder(HttpStatus.ACCEPTED)
      .bodyBuilder()
      .setBodyAttribute("order", new CustomerOrder().getOrderState().get(orderId))
      .responseBuilder()
      .build();

    return response;

  }

  @RequestMapping(method = RequestMethod.POST)
  public RESTResponse saveOrder(@RequestBody CustomerOrder order) throws Exception{

    RESTResponse response =  RESTResponseManager
      .headerBuilder(HttpStatus.ACCEPTED)
      .bodyBuilder()
      .setBodyAttribute("order", order.getOrderState().save())
      .responseBuilder()
      .build();

    return response;
  }

  @RequestMapping(method = RequestMethod.PUT)
  public RESTResponse updateOrder(@RequestBody CustomerOrder order) throws Exception{

    RESTResponse response =  RESTResponseManager
            .headerBuilder(HttpStatus.ACCEPTED)
            .bodyBuilder()
            .setBodyAttribute("order", order.getOrderState().update(order))
            .responseBuilder()
            .build();

    return response;
  }

  @RequestMapping(path = "confirm", method = RequestMethod.POST)
  public RESTResponse confirmOrder(@RequestBody CustomerOrder order) throws Exception{

    RESTResponse response =  RESTResponseManager
            .headerBuilder(HttpStatus.ACCEPTED)
            .bodyBuilder()
            .setBodyAttribute("order", order.getOrderState().confirm())
            .responseBuilder()
            .build();

    return response;
  }

  @RequestMapping(path = "search", method = RequestMethod.GET)
  public RESTResponse orderSearch(ControllerRequestCriteria controllerRequestCriteria) throws Exception{

    return RESTResponseManager
            .headerBuilder(HttpStatus.ACCEPTED)
            .bodyBuilder()
            .setBodyAttribute("result", new CustomerOrder().getOrderState().searchOrders(controllerRequestCriteria))
            .responseBuilder()
            .build();
  }

  @RequestMapping(path = "transit-next", method = RequestMethod.POST)
  public RESTResponse transitNext(@RequestBody CustomerOrder order) throws Exception{

    return RESTResponseManager
            .headerBuilder(HttpStatus.ACCEPTED)
            .bodyBuilder()
            .setBodyAttribute("result", order.getOrderState().transitNext())
            .responseBuilder()
            .build();
  }

  @RequestMapping(path = "transit-prev", method = RequestMethod.POST)
  public RESTResponse transitPrev(@RequestBody CustomerOrder order) throws Exception{

    return RESTResponseManager
            .headerBuilder(HttpStatus.ACCEPTED)
            .bodyBuilder()
            .setBodyAttribute("result", order.getOrderState().transitPrev())
            .responseBuilder()
            .build();
  }



}

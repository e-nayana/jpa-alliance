package com.java.order.state.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huston.rest.exception.AbnoramalBehaviorException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.rest.exception.SystemBussinessLogicException;
import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.huston.springboot.crudgeneric.pagination.ResultPage;
import com.java.order.ControllerRequestCriteria;
import com.java.order.model.CustomerOrder;
import com.java.order.state.exception.StateTransitException;

import java.util.Map;

public interface OrderState {
    CustomerOrder transitNext() throws CrudGenericException, ResourceNotFoundException, StateTransitException;
    CustomerOrder transitPrev() throws StateTransitException;
    CustomerOrder save() throws StateTransitException, CrudGenericException, ResourceNotFoundException, AllianceException, AbnoramalBehaviorException;
    CustomerOrder update(CustomerOrder order) throws StateTransitException, CrudGenericException;
    CustomerOrder confirm() throws CrudGenericException, ResourceNotFoundException,StateTransitException, SystemBussinessLogicException;
    CustomerOrder cancel() throws CrudGenericException, ResourceNotFoundException,StateTransitException, SystemBussinessLogicException;
    ResultPage getActiveList(Integer page, Integer perPage);
    ResultPage getActiveListForAuthenticatedCustomer(Integer page, Integer perPage) throws ResourceNotFoundException;
  ResultPage searchOrders(ControllerRequestCriteria controllerRequestCriteria) throws ResourceNotFoundException , JsonProcessingException;
  CustomerOrder get(Integer orderId) throws AllianceException, CrudGenericException;
}

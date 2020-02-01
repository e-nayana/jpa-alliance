package com.java.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.springboot.crudgeneric.GenericCrudService;
import com.huston.springboot.crudgeneric.pagination.ResultPage;
import com.java.order.ControllerRequestCriteria;
import com.java.order.model.CustomerOrderView;

public interface CustomerOrderViewService extends GenericCrudService<CustomerOrderView> {

    ResultPage getActiveListForAuthenticatedCustomer(Integer page, Integer perPage, String... alliancesFields) throws ResourceNotFoundException;
    ResultPage search(ControllerRequestCriteria controllerRequestCriteria, String... alliancesFields) throws ResourceNotFoundException, JsonProcessingException;

}

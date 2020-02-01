package com.java.order.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.huston.springboot.crudgeneric.pagination.ResultPage;
import com.java.customer.model.EcomCustomer;
import com.java.customer.service.CustomerService;
import com.java.module.AuthUserService;
import com.java.order.ControllerRequestCriteria;
import com.java.order.model.CustomerOrderView;
import com.java.order.repository.CustomerOrderViewRepository;
import com.java.order.service.CustomerOrderViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerOrderViewImpl extends GenericCrudServiceImpl<CustomerOrderViewRepository, CustomerOrderView> implements CustomerOrderViewService {


    private CustomerService customerService;

    @Autowired
    public CustomerOrderViewImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResultPage getActiveListForAuthenticatedCustomer(Integer page, Integer perPage, String... alliancesFields) throws ResourceNotFoundException{

        final Integer authUserId = AuthUserService.authUserId();
        if(authUserId == null){
            throw new ResourceNotFoundException("Auth User was not found");
        }

        final EcomCustomer ecomCustomer = customerService.customerByUserId(authUserId);
        if(ecomCustomer == null){
            throw new ResourceNotFoundException("Customer was not found");
        }

        Map<String, String> filters = new HashMap<>();
        filters.put("customerId", ecomCustomer.getId().toString());
        return page(page, perPage, searchingCriteria(filters),alliancesFields);

    }

    @Override
    public ResultPage search(ControllerRequestCriteria controllerRequestCriteria, String... alliancesFields) throws ResourceNotFoundException, JsonProcessingException  {
        return page(controllerRequestCriteria.getCurrentPage(), controllerRequestCriteria.getPerPage(), searchingCriteria(controllerRequestCriteria.getSearchCriteria()),alliancesFields);
    }
}

package com.java.customer.controller;

import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.customer.service.CustomerService;
import com.java.dto.PaginatedListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * get paginated record list
     * @return Pagenator.PagenatedObject
     */
    @RequestMapping(path = "list", method = RequestMethod.POST)
    public ResponseEntity<?> list(@RequestBody PaginatedListRequest request){
        return new ResponseEntity<>(customerService.list(request.getPage(),request.getPerPage(), request.getFilter()), HttpStatus.OK);
    }


    /**
     * get paginated record list
     * @return Pagenator.PagenatedObject
     */
    @RequestMapping(path = "index", method = RequestMethod.POST)
    public ResponseEntity<?> show(@RequestBody Integer Id) throws AllianceException, CrudGenericException {
        return new ResponseEntity<Object>(customerService.show(Id,"customerAddress","customerContact"), HttpStatus.OK);
    }

}

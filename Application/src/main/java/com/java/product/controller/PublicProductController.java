package com.java.product.controller;

import com.huston.rest.response.RESTResponse;
import com.huston.rest.response.RESTResponseManager;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.dto.PaginatedListRequest;
import com.java.order.ControllerRequestCriteria;
import com.java.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//no-auth
@RestController
@RequestMapping(path = "public/products")
public class PublicProductController {

    ProductService productService;

    @Autowired
    public PublicProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> getProductList(@RequestBody PaginatedListRequest request){
        return new ResponseEntity<>(productService.list(request.getPage(),request.getPerPage(), request.getFilter()), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable Integer id) throws CrudGenericException {
        return new ResponseEntity<>(productService.showProductFullDetails(id), HttpStatus.OK);
    }

    @RequestMapping(path = "/similar-products", method = RequestMethod.GET)
    public ResponseEntity<?> similarProducts(@RequestParam int id) throws CrudGenericException {
        return RESTResponseManager.headerBuilder(HttpStatus.OK)
                .bodyBuilder()
                .setBodyAttribute("result",productService.similarProduct(id))
                .responseBuilder()
                .build();
    }

    @RequestMapping(path = "/latest-products", method = RequestMethod.GET)
    public RESTResponse getLatestProducts(ControllerRequestCriteria controllerRequestCriteria) throws Exception {
        return RESTResponseManager.headerBuilder(HttpStatus.OK)
                .bodyBuilder()
                .setBodyAttribute("result",productService.search(controllerRequestCriteria.getCurrentPage(), controllerRequestCriteria.getPerPage(), controllerRequestCriteria.getSearchCriteria()))
                .responseBuilder()
                .build();
    }
}

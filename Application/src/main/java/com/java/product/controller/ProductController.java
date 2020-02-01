package com.java.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.rest.response.RESTResponse;
import com.huston.rest.response.RESTResponseManager;
import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.dto.PaginatedListRequest;
import com.java.order.ControllerRequestCriteria;
import com.java.order.model.CustomerOrder;
import com.java.product.model.Product;
import com.java.product.model.ProductImage;
import com.java.product.model.ProductVariation;
import com.java.product.service.ProductService;
import com.java.product.service.ProductVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductVariationService productVariationService;

    @RequestMapping(path = "all", method = RequestMethod.GET)
    public ResponseEntity<?> productAll(){
        return new ResponseEntity<>(productService.activeList(), HttpStatus.OK);
    }

    @RequestMapping(path = "product-variations-by-product", method = RequestMethod.GET)
    public ResponseEntity<?> productVariationsByProduct(@RequestParam int productId){
        return new ResponseEntity<>(productVariationService.productVariationsByProduct(productId), HttpStatus.OK);
    }
    /**
     * get paginated record list
     * @return Pagenator.PagenatedObject
     */
    @RequestMapping(method = RequestMethod.GET)
    public RESTResponse getList(ControllerRequestCriteria paginationCriteria) throws ResourceNotFoundException, JsonProcessingException {
        return RESTResponseManager.headerBuilder(HttpStatus.OK)
                .bodyBuilder()
                .setBodyAttribute("result",productService.search(paginationCriteria, "brand"))
                .responseBuilder()
                .build();
    }

    @RequestMapping(path = "select-list", method = RequestMethod.POST)
    public RESTResponse selectList(@RequestBody Product product){

      List<Product> products = productService.activeList();

      RESTResponse response =  RESTResponseManager
        .headerBuilder(HttpStatus.ACCEPTED)
        .bodyBuilder()
        .setBodyAttribute("products", products)
        .responseBuilder()
        .build();
      return response;
    }

    @RequestMapping(path = "single-product-list", method = RequestMethod.GET)
    public RESTResponse singleProductList(){

        List<Product> products = productService.singleProductList();

        RESTResponse response =  RESTResponseManager
                .headerBuilder(HttpStatus.ACCEPTED)
                .bodyBuilder()
                .setBodyAttribute("products", products)
                .responseBuilder()
                .build();
        return response;
    }

    /**
     * show record
     * @return product
     */
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> show(@PathVariable Integer id) throws CrudGenericException {
        return new ResponseEntity<>(productService.show(id), HttpStatus.OK);
    }

    @RequestMapping(path = "show-product-full-details", method = RequestMethod.GET)
    public ResponseEntity<?> showProductFullDetails(@RequestParam Integer id) throws CrudGenericException {
        return new ResponseEntity<>(productService.showProductFullDetails(id), HttpStatus.OK);
    }

    /**
     * create new record
     * @param product
     * @throws CrudGenericException
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid Product product) throws Exception {
        Product product1 = this.productService.createNewProduct(product);
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }

    /**
     * update existing record
     * @param product and id
     * @return product
     * @throws CrudGenericException
     */
    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid Product product) throws Exception {
      Product product1 = this.productService.updateProduct(product);
      return new ResponseEntity<>(product1, HttpStatus.OK);
    }

    @RequestMapping(path = "/save-product-images", method = RequestMethod.POST)
    public RESTResponse saveProductImages(@RequestBody @Valid List<ProductImage> productImages) throws CrudGenericException, AllianceException {
        return new RESTResponseManager()
                .headerBuilder(HttpStatus.OK)
                .bodyBuilder()
                .setBodyAttribute("product", productService.saveProductImages(productImages))
                .responseBuilder()
                .build();
    }

    @RequestMapping(path = "/save-product-variation", method = RequestMethod.POST)
    public ResponseEntity<?> saveProductVariation(@RequestBody @Valid List<ProductVariation> productVariations) throws CrudGenericException, AllianceException {
        return new ResponseEntity<>(productService.saveProductVariation(productVariations), HttpStatus.OK);
    }
}

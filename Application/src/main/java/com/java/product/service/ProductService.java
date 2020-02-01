package com.java.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.huston.rest.exception.ResourceNotFoundException;
import com.huston.springboot.crudgeneric.GenericCrudService;
import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.huston.springboot.crudgeneric.pagination.ResultPage;
import com.java.order.ControllerRequestCriteria;
import com.java.product.model.Product;
import com.java.product.model.ProductImage;
import com.java.product.model.ProductVariation;
import com.java.service.Pagenator;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductService extends GenericCrudService<Product> {

    Product show(Integer id) throws CrudGenericException;

    ResultPage search(int page, int perPage, Map<String, String> filters);

    Product showProductFullDetails(Integer id) throws CrudGenericException;

    Product saveProductImages(List<ProductImage> productImages) throws AllianceException, CrudGenericException;

    Product saveProductVariation(List<ProductVariation> productVariations) throws AllianceException, CrudGenericException;

    Pagenator.PagenatedObject similarProduct(int id) throws CrudGenericException;

    Product createNewProduct(Product product) throws Exception;

    Product updateProduct(Product product) throws Exception;

    ResultPage search(ControllerRequestCriteria controllerRequestCriteria, String... alliancesFields) throws ResourceNotFoundException, JsonProcessingException;

    List<Product> singleProductList();
}

package com.java.product.service;

import com.huston.springboot.crudgeneric.GenericCrudService;
import com.java.product.model.ProductVariation;

import java.util.List;

public interface ProductVariationService extends GenericCrudService<ProductVariation> {
    List<ProductVariation> productVariationsByProduct(int productId);
}

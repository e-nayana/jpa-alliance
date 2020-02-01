package com.java.product.service;

import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.java.product.model.ProductVariation;
import com.java.product.repository.ProductVariationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariationServiceImpl extends GenericCrudServiceImpl<ProductVariationRepository, ProductVariation> implements ProductVariationService {

    @Override
    public List<ProductVariation> productVariationsByProduct(int productId) {
        return repository.findAllByProductId(productId);
    }
}

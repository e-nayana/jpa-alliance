package com.java.product.service;

import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.product.model.ComboChildProduct;
import com.java.product.model.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComboChildProductService {

  ComboChildProduct createNewComboChildProduct(ComboChildProduct comboChildProduct) throws AllianceException, CrudGenericException;
  void createNewComboChildProductList(Product comboProduct) throws Exception;
  void deleteChildProductsFromComboProductId(int comboProductId) throws AllianceException, CrudGenericException;
}

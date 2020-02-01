package com.java.product.service;

import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.huston.springboot.crudgeneric.exception.AllianceException;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.product.model.ComboChildProduct;
import com.java.product.model.Product;
import com.java.product.repository.ComboChildProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComboChildProductServiceImpl extends GenericCrudServiceImpl<ComboChildProductRepository, ComboChildProduct> implements ComboChildProductService {

  ComboChildProductRepository comboChildProductRepository;

  @Autowired
  public ComboChildProductServiceImpl(ComboChildProductRepository comboChildProductRepository){
    this.comboChildProductRepository = comboChildProductRepository;
  }

  @Override
  public ComboChildProduct createNewComboChildProduct(ComboChildProduct comboChildProduct) throws AllianceException, CrudGenericException {
    comboChildProduct = save(comboChildProduct);
    return comboChildProduct;
  }

  @Override
  public void createNewComboChildProductList(Product comboProduct) throws Exception {

    if(comboProduct.getComboChildProducts().size() == 0){
      throw new Exception("Please enter at lease one child product.");
    }

    for(ComboChildProduct comboChildProduct : comboProduct.getComboChildProducts()){
      comboChildProduct.setComboProductId(comboProduct.getId());
      this.createNewComboChildProduct(comboChildProduct);
    }
  }

  @Override
  public void deleteChildProductsFromComboProductId(int comboProductId) throws AllianceException, CrudGenericException {
    this.comboChildProductRepository.removeAllByComboProductId(comboProductId);
    this.comboChildProductRepository.flush();
  }

}

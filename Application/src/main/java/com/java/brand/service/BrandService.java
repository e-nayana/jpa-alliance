package com.java.brand.service;

import com.huston.springboot.crudgeneric.GenericCrudService;
import com.huston.springboot.crudgeneric.exception.CrudGenericException;
import com.java.brand.model.Brand;

public interface BrandService extends GenericCrudService<Brand> {
    Brand show(Integer id) throws CrudGenericException;
}

package com.java.category.service;

import com.huston.springboot.crudgeneric.GenericCrudServiceImpl;
import com.java.category.model.Category;
import com.java.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends GenericCrudServiceImpl<CategoryRepository, Category> implements CategoryService {

}

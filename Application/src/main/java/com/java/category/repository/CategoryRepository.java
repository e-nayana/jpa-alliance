package com.java.category.repository;

import com.huston.springboot.crudgeneric.GenericCrudRepository;
import com.java.category.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends GenericCrudRepository<Category,Integer> {
}

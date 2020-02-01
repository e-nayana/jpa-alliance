package com.huston.springboot.crudgeneric;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface GenericCrudRepository<T extends GenericCrudEntity,ID extends Serializable> extends JpaRepository<T,ID>, JpaSpecificationExecutor<T> {

    List<T> findByIsActive(Boolean isActive);
    List<T> findAll(Specification<T> var1);
    Optional<T> findOne(Specification<T> var1);
}

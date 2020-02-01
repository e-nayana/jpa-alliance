package com.auth.repository;

import com.auth.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Integer>{

    List<Configuration> findAllByType(String type);
    Configuration findAllByCode(String code);

}

package com.auth.service;

import com.auth.dto.ConfigurationDTO;
import com.auth.model.Configuration;
import com.auth.repository.ConfigurationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfigurationService {

    @Autowired
    ConfigurationRepository configurationRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<ConfigurationDTO> allConfigurations(){
        return configurationRepository.findAll().stream()
                .map(level -> modelMapper.map(level, ConfigurationDTO.class))
                .collect(Collectors.toList());
    }

    public List configurationsByType(String type){
        List<Configuration> configurations = configurationRepository.findAllByType(type);

        return configurations.stream()
                .map(level -> modelMapper.map(level, ConfigurationDTO.class))
                .collect(Collectors.toList());
    }

    public ConfigurationDTO updateConfiguration(ConfigurationDTO configurationDTO){
        Configuration configuration = configurationRepository.findAllByCode(configurationDTO.getCode());
        configuration.setValue(configurationDTO.getValue());
        configuration = configurationRepository.save(configuration);
        return modelMapper.map(configuration,ConfigurationDTO.class);
    }

}

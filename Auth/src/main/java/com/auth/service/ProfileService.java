package com.auth.service;

import com.auth.dto.ProfileDTO;
import com.auth.model.Profile;
import com.auth.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository profileRepository;

    ModelMapper modelMapper;

    public ProfileDTO saveDetails(ProfileDTO inputData){

        Profile profile = modelMapper.map(inputData, Profile.class);
        profileRepository.save(profile);
        ProfileDTO profileDTO = modelMapper.map(profile, ProfileDTO.class);
        return profileDTO;
    }
}

package com.auth.service;

import com.auth.dto.ProfileDTO;
import com.auth.dto.UserDTO;
import com.auth.model.Profile;
import com.auth.model.Role;
import com.auth.model.User;
import com.auth.repository.UserRepository;
import com.java.service.Pagenator;
import com.java.service.ReturnResponse;
import com.java.service.ValidationCheck;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private RoleService roleService;

    public List allUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

//    public List<User> usersFilterByRole(int roleId) {
//        List<Role> roles = new ArrayList<>();
//        Role role = new Role();
//        role.setId((long)roleId);
//        roles.add(role);
//        List<User> users = userRepository.findAllByRoles(roles);
//        return users;
//    }

    public Pagenator.PagenatedObject userList(Map<String, Object> inputData) {
        inputData.putIfAbsent("page",1);
        inputData.putIfAbsent("perPage",10);
        Pageable pageable = Pagenator.setPagination((Integer) inputData.get("page"), (Integer) inputData.get("perPage"));
        Page<User> users = userRepository.findAllByOrderByIdDesc(pageable);
        Pagenator.PagenatedObject pagenatedObject = Pagenator.setMetaData(users);
        return pagenatedObject;
    }

    public UserDTO userDetails(Integer id){
        User user = userRepository.findById(id);
        user.setPassword("******");
        UserDTO postDto = modelMapper.map(user, UserDTO.class);
        return postDto;
    }

    public User create(User user){
        return userRepository.save(user);
    }

    public ResponseEntity saveDetails(UserDTO inputData) throws Exception {

        User user;
        String validatorReturnMessage = "";
        ProfileDTO inputProfile;
        String hashPw;

        if(inputData.getId()==null){
            user = userRepository.findByEmail(inputData.getEmail());
            if(user != null){
                validatorReturnMessage = "Email already exist";
            }
            hashPw = this.passwordEncoder((String) inputData.getPassword());

        }else {
            user = userRepository.findByEmail(inputData.getEmail());
            hashPw = user.getPassword();
        }

        user = modelMapper.map(inputData,User.class);
        user.setPassword(hashPw);

        inputProfile = inputData.getProfile();
        inputProfile.setEmail(user.getEmail());

        if (validatorReturnMessage.equals("")) {
            validatorReturnMessage = ValidationCheck.validator(modelMapper.map(inputProfile,Profile.class));
        }

        if (validatorReturnMessage.equals("")) {
            validatorReturnMessage = ValidationCheck.validator(user);
        }

        if (validatorReturnMessage.equals("")){

            //user.setProfile(null);
            User savedUser = userRepository.save(user);
            inputProfile.setUserId(Integer.valueOf(String.valueOf(savedUser.getId())));
            inputProfile = profileService.saveDetails(inputProfile);
            //savedUser.setProfile(modelMapper.map(inputProfile, Profile.class));

        }else{
            Map<String, String> returnData = new HashMap<String, String>();
            returnData.put("error",validatorReturnMessage);
            return new ResponseEntity<Object>(returnData, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Object>(ReturnResponse.returnSuccessJsonObject(),HttpStatus.OK);
    }

    public String passwordEncoder(String password) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = bCryptPasswordEncoder.encode(password);

        return(hashedPassword);
    }
}

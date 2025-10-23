package com.ayhanunlu.service.impl;

import com.ayhanunlu.data.dto.UserDto;
import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.enums.Role;
import com.ayhanunlu.enums.Status;
import com.ayhanunlu.mapper.UserMapper;
import com.ayhanunlu.repository.UserRepository;
import com.ayhanunlu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

/*
    @Override
    public void registerNewUser(String username, String password) {
        saveEmployeeToDb(addUserDtoDetails(username, password));
    }
*/

    @Override
    public void registerNewUser(UserDto userDto){
        saveEmployeeToDb(userDto);
    }

    @Override
    public UserDto addUserDtoDetails(String username, String password) {
        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(passwordEncoder.encode(password));
        userDto.setRole(Role.EMPLOYEE);
        userDto.setStatus(Status.ACTIVE);
        return userDto;
    }


/*    @Override
    public UserEntity createEmployee(UserDto userDto) {
    //userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity userEntity = userMapper.fromUserDtoToUserEntity(userDto);
        userRepository.save(userEntity);
        return userEntity;
    }*/

    @Override
    public void saveEmployeeToDb(UserDto userDto) {
        System.out.println(userDto.getUsername());
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setRole(Role.EMPLOYEE);
        userDto.setStatus(Status.ACTIVE);
        UserEntity userEntity = userMapper.fromUserDtoToUserEntity(userDto);
        System.out.println(userEntity.getUsername());
        userEntity.setCreatedBy(userEntity.getUsername());
        userEntity.setUpdatedBy(userEntity.getUsername());
        userRepository.save(userEntity);
    }


}

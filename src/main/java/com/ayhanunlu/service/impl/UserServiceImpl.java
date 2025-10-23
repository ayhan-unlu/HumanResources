package com.ayhanunlu.service.impl;

import com.ayhanunlu.data.dto.UserDto;
import com.ayhanunlu.data.entity.UserEntity;
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

/*
    @Autowired
    private PasswordEncoder passwordEncoder;
*/

    @Override
    public UserEntity createEmployee(UserDto userDto) {
    //userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity userEntity = userMapper.fromUserDtoToUserEntity(userDto);
        userRepository.save(userEntity);
        return userEntity;
    }


}

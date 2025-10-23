package com.ayhanunlu.service;

import com.ayhanunlu.data.dto.UserDto;
import com.ayhanunlu.data.entity.UserEntity;

public interface UserService {

    UserEntity createEmployee(UserDto userDto);
}

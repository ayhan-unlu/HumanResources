package com.ayhanunlu.service;

import com.ayhanunlu.data.dto.UserDto;
import com.ayhanunlu.data.entity.UserEntity;

public interface UserService {

   // void registerNewUser(String username, String password);
    void registerNewUser(UserDto userDto);
    UserDto addUserDtoDetails(String username, String password);
    void saveEmployeeToDb(UserDto userDto);
}

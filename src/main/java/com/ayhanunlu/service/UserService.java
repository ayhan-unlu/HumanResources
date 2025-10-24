package com.ayhanunlu.service;

import com.ayhanunlu.data.dto.LoginResult;
import com.ayhanunlu.data.dto.UserDto;
import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.enums.LoginResponse;

public interface UserService {

    void createDefaultAdmin();
    boolean registerNewUser(UserDto userDto);
    boolean saveEmployeeToDb(UserDto userDto);
    LoginResult login(UserDto userDto);
    void setStatusBlocked(UserDto userDto);
}

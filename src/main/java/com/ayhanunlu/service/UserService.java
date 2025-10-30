package com.ayhanunlu.service;

import com.ayhanunlu.data.dto.AdminSessionDto;
import com.ayhanunlu.data.dto.LoginResult;
import com.ayhanunlu.data.dto.UserDto;
import com.ayhanunlu.data.dto.UserSessionDto;
import com.ayhanunlu.data.entity.EmployeeDetailsEntity;
import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.enums.LoginResponse;

public interface UserService {

    void createDefaultAdmin();
    boolean registerNewUser(UserDto userDto);
    boolean saveEmployeeToDb(UserDto userDto);
    void saveDefaultEmployeeDetailsToDb(UserEntity userEntity);
    LoginResult login(UserDto userDto);
    void setStatusBlocked(UserDto userDto);
    UserSessionDto prepareUserSessionDto(UserEntity userEntity);
    AdminSessionDto prepareAdminSessionDto(UserEntity userEntity);
    EmployeeDetailsEntity getEmployeeDetailsEntity(UserEntity userEntity);
}

package com.ayhanunlu.service.impl;

import com.ayhanunlu.data.dto.LoginResult;
import com.ayhanunlu.data.dto.UserDto;
import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.enums.LoginResponse;
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

    @Override
    public void createDefaultAdmin() {
        if (userRepository.findByUsername("a") == null) {
            UserDto userDto = new UserDto();
            userDto.setUsername("a");
            userDto.setPassword(passwordEncoder.encode("a"));
            userDto.setRole(Role.ADMIN);
            userDto.setStatus(Status.ACTIVE);
            UserEntity userEntity = userMapper.fromUserDtoToUserEntity(userDto);
            userEntity.setCreatedBy("SYSTEM");
            userEntity.setUpdatedBy("SYSTEM");
            userRepository.save(userEntity);
        }
    }

    @Override
    public boolean registerNewUser(UserDto userDto) {
        return saveEmployeeToDb(userDto);

    }

    @Override
    public boolean saveEmployeeToDb(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()) == null) {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userDto.setRole(Role.EMPLOYEE);
            userDto.setStatus(Status.ACTIVE);
            UserEntity userEntity = userMapper.fromUserDtoToUserEntity(userDto);
            userEntity.setCreatedBy(userEntity.getUsername());
            userEntity.setUpdatedBy(userEntity.getUsername());
            userRepository.save(userEntity);
            return true;
        }else return false;
    }

    @Override
    public LoginResult login(UserDto userDto) {
//      }



       /* UserEntity userEntity = userRepository.findByUsername(userDto.getUsername());

        if ((userEntity != null) && passwordEncoder.matches(userDto.getPassword(), userEntity.getPassword())) {
            UserDto loggedInUserDto = userMapper.fromUserEntityToUserDto(userEntity);
            userDto.setPassword(null);
            return loggedInUserDto;
        }
        */
        return getLoginResult(userDto);
    }

    public LoginResponse controlUserEntity(UserEntity userEntity) {
        if (userEntity == null) {
            return LoginResponse.FAIL;
        }
        if (userEntity.getStatus().equals(Status.BLOCKED)) {
            return LoginResponse.BLOCKED;
        } else return null;
    }

    public LoginResult getLoginResult(UserDto userDto) {

        UserEntity dbUserEntity = userRepository.findByUsername(userDto.getUsername());
        if (dbUserEntity == null) {
            return new LoginResult(LoginResponse.NO_SUCH_USER, null);
        }
        if (dbUserEntity.getStatus().equals(Status.BLOCKED)) {
            return new LoginResult(LoginResponse.BLOCKED, null);
        }

        if (!passwordEncoder.matches(userDto.getPassword(), dbUserEntity.getPassword())) {
            return new LoginResult(LoginResponse.FAIL, null);
        }

        return new LoginResult(LoginResponse.SUCCESS, dbUserEntity);
    }

    public void setStatusBlocked(UserDto userDto) {
        UserEntity userEntity = userRepository.findByUsername(userDto.getUsername());
        userEntity.setStatus(Status.BLOCKED);
        userRepository.save(userEntity);
    }

}

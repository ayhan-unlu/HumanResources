package com.ayhanunlu.service.impl;

import com.ayhanunlu.data.dto.AdminSessionDto;
import com.ayhanunlu.data.dto.LoginResult;
import com.ayhanunlu.data.dto.UserDto;
import com.ayhanunlu.data.dto.UserSessionDto;
import com.ayhanunlu.data.entity.EmployeeDetailsEntity;
import com.ayhanunlu.data.entity.UserEntity;
import com.ayhanunlu.enums.LoginResponse;
import com.ayhanunlu.enums.Role;
import com.ayhanunlu.enums.Status;
import com.ayhanunlu.mapper.UserMapper;
import com.ayhanunlu.repository.EmployeeDetailsRepository;
import com.ayhanunlu.repository.UserRepository;
import com.ayhanunlu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

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
            UserEntity admin = userRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Admin with Id 1 not found"));
            userEntity.setAdmin(admin);
            userRepository.save(userEntity);
            saveDefaultEmployeeDetailsToDb(userEntity);
            return true;
        } else return false;
    }

    @Override
    public void saveDefaultEmployeeDetailsToDb(UserEntity userEntity) {
        EmployeeDetailsEntity employeeDetailsEntity = new EmployeeDetailsEntity();
        employeeDetailsEntity.setUserEntity(userEntity);
        employeeDetailsEntity.setCreatedBy(userEntity.getUsername());
        employeeDetailsEntity.setUpdatedBy(userEntity.getUsername());
        employeeDetailsEntity.setSalary(0);
        employeeDetailsEntity.setLeaveDate(null);
        employeeDetailsEntity.setLeaveDuration(0);
        employeeDetailsRepository.save(employeeDetailsEntity);
    }


    @Override
    public LoginResult login(UserDto userDto) {
        LoginResult loginResult = getLoginResult(userDto);
        if (loginResult.getLoginResponse() == LoginResponse.SUCCESS) {
            UserEntity userEntity = loginResult.getUserEntity();
            if(userEntity.getRole()==Role.ADMIN){
                getAllEmployeesByAdmin(userEntity);
            }

        }
        return loginResult;
    }

    public LoginResponse controlUserEntity(UserEntity userEntity) {
        if (userEntity == null) {
            return LoginResponse.NO_SUCH_USER;
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
        if (userEntity != null) {
            userEntity.setStatus(Status.BLOCKED);
            userRepository.save(userEntity);
        } else {
        }
    }

    @Override
    public UserSessionDto prepareUserSessionDto(UserEntity userEntity) {
        UserSessionDto userSessionDto = new UserSessionDto();
        userSessionDto.setId(userEntity.getId());
        userSessionDto.setUsername(userEntity.getUsername());
        EmployeeDetailsEntity employeeDetailsEntity = getEmployeeDetailsEntity(userEntity);
        userSessionDto.setDetailId(employeeDetailsEntity.getId());
        userSessionDto.setRole(userEntity.getRole());
        userSessionDto.setStatus(userEntity.getStatus());
        userSessionDto.setSalary(employeeDetailsEntity.getSalary());
        userSessionDto.setLeaveDate(employeeDetailsEntity.getLeaveDate());
        userSessionDto.setLeaveDuration(employeeDetailsEntity.getLeaveDuration());

        ///  details still not completed
        return userSessionDto;
    }

    @Override
    public AdminSessionDto prepareAdminSessionDto(UserEntity userEntity) {
        AdminSessionDto adminSessionDto = new AdminSessionDto();
        adminSessionDto.setId(userEntity.getId());
        adminSessionDto.setUsername(userEntity.getUsername());
        adminSessionDto.setRole(userEntity.getRole());
        adminSessionDto.setStatus(userEntity.getStatus());
        return adminSessionDto;
    }

    @Override
    public EmployeeDetailsEntity getEmployeeDetailsEntity(UserEntity userEntity) {
        return employeeDetailsRepository.findByUserEntity(userEntity);
    }

    @Override
    public List<UserEntity> getAllEmployeesByAdmin(UserEntity admin) {
        List<UserEntity> employeeList = userRepository.findAllByAdmin(admin);
        for (UserEntity userEntity : employeeList) {
            System.out.println(userEntity.getId());
            System.out.println(userEntity.getUsername());
        }
        return employeeList;
    }

}

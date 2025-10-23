package com.ayhanunlu.mapper;

import com.ayhanunlu.data.dto.UserDto;
import com.ayhanunlu.data.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto fromUserEntityToUserDto(UserEntity userEntity);
    UserEntity fromUserDtoToUserEntity(UserDto userDto);
}

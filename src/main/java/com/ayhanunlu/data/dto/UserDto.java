package com.ayhanunlu.data.dto;

import com.ayhanunlu.enums.Role;
import com.ayhanunlu.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class UserDto {

    private Long id;
    private String username;
    private String password;
    private Role role;
    private Status status;
}


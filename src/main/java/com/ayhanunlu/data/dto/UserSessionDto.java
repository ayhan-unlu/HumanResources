package com.ayhanunlu.data.dto;


import com.ayhanunlu.enums.Role;
import com.ayhanunlu.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserSessionDto {
    private Long userId;
    private String username;
    private Long detailId;
    private Role role;
    private Status status;
    private int salary;
    private LocalDateTime leaveDate;
    private int leaveDuration;
}

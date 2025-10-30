package com.ayhanunlu.data.dto;

import com.ayhanunlu.enums.Role;
import com.ayhanunlu.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeInfoDto {
    private Long id;
    private String username;
    private Long employeeDetailId;
    private Role role;
    private Status status;
    private int salary;
    private LocalDateTime leaveDate;
    private int leaveDuration;
}

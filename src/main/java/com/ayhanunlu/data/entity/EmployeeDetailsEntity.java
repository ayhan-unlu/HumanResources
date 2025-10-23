package com.ayhanunlu.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "employee_details")
public class EmployeeDetailsEntity extends BaseEntity implements Serializable {

    @Column(name = "salary")
    private int salary;

    @Column(name = "leave_date")
    private LocalDateTime leaveDate;

    @Column(name = "leave_duration")
    private int leaveDuration;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserEntity userEntity;


}

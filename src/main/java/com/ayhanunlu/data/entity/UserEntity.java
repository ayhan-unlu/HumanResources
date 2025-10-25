package com.ayhanunlu.data.entity;

import com.ayhanunlu.enums.Role;
import com.ayhanunlu.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity implements Serializable {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    /// Employee <-> Employee details 1:1
    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private EmployeeDetailsEntity employeeDetailsEntity;

    /// Employee <-> Admin N:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admin_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserEntity admin;

    /// Admin <-> Employee 1:N
    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<UserEntity> employees;


}

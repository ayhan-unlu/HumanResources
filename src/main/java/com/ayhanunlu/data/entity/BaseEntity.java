package com.ayhanunlu.data.entity;


import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_by")
    @CreatedBy
    private String createdBy;

    @Column(name="created_date")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name="updated_by")
    @LastModifiedBy
    private String updatedBy;

    @Column(name="updated_date")
    @LastModifiedDate
    private LocalDateTime updatedDate;

}

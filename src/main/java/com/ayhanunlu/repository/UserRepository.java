package com.ayhanunlu.repository;

import com.ayhanunlu.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    List<UserEntity> findAllByAdmin(UserEntity admin);
}

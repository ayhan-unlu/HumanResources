package com.ayhanunlu.config;

import com.ayhanunlu.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class AdminInitializer {
    private final UserService userService;

    @PostConstruct
    public void initializeAdmin(){
        userService.createDefaultAdmin();
    }
}

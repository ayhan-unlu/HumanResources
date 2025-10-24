package com.ayhanunlu;

import com.ayhanunlu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HumanResourcesApplication {

    static void main(String[] args) {
        SpringApplication.run(HumanResourcesApplication.class, args);
    }

}

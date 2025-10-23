package com.ayhanunlu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HumanResourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumanResourcesApplication.class, args);
    }

}

package org.generations.playerservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PlayerServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayerServicesApplication.class, args);
    }

}

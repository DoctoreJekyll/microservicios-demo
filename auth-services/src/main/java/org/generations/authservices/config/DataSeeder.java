// src/main/java/org/generations/restfuldemodyd/config/DataSeeder.java
package org.generations.authservices.config;

import org.generations.authservices.model.AppUser;
import org.generations.authservices.model.Role;
import org.generations.authservices.repository.RolRepository;
import org.generations.authservices.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seed(RolRepository rolRepo, UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            Role rUser = rolRepo.findByName("ROLE_USER").orElseGet(() ->
                    rolRepo.save(Role.builder().name("ROLE_USER").build())
            );
            Role rAdmin = rolRepo.findByName("ROLE_ADMIN").orElseGet(() ->
                    rolRepo.save(Role.builder().name("ROLE_ADMIN").build())
            );

            if (userRepo.findByUsername("admin").isEmpty()) {
                userRepo.save(
                    AppUser.builder()
                        .username("admin")
                        .password(encoder.encode("admin123"))
                        .roles(Set.of(rAdmin, rUser))
                        .build()
                );
            }
        };
    }
}

package com.example.reportsharingplatform;

import com.example.reportsharingplatform.model.Role;
import com.example.reportsharingplatform.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReportsharingplatformApplication {
    @Bean
    CommandLineRunner init(RoleRepository roleRepository) {

        return args -> {

            Role adminRole = roleRepository.findRoleByAuthorization("ADMIN");
            if (adminRole == null) {
                Role newAdminRole = new Role();
                newAdminRole.setAuthorization("ADMIN");
                roleRepository.save(newAdminRole);
            }

            Role evaluatorRole = roleRepository.findRoleByAuthorization("EVALUATOR");
            if (evaluatorRole == null) {
                Role newUserRole = new Role();
                newUserRole.setAuthorization("EVALUATOR");
                roleRepository.save(newUserRole);
            }
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(ReportsharingplatformApplication.class, args);
    }

}

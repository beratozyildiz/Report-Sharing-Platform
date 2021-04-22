package com.example.reportsharingplatform.repository;

import com.example.reportsharingplatform.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findRoleByAuthorization(String authorization);
}

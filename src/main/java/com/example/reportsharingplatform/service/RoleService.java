package com.example.reportsharingplatform.service;

import com.example.reportsharingplatform.model.Log;
import com.example.reportsharingplatform.model.Role;
import com.example.reportsharingplatform.repository.LogRepository;
import com.example.reportsharingplatform.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private LogRepository logRepository;

    public List<Role> getList() {
        Log log = new Log()
                .setId(UUID.randomUUID().toString())
                .setDate(new Date())
                .setActor(null)
                .setType(Log.Type.roleRead);
        logRepository.save(log);
        return roleRepository.findAll();
    }

    public Role getRole(String authorization) {
        return roleRepository.findRoleByAuthorization(authorization);
    }

    public Role save(Role role) {
        Log log = new Log()
                .setId(UUID.randomUUID().toString())
                .setDate(new Date())
                .setActor(null);
        if (roleRepository.findRoleByAuthorization(role.getAuthorization()) != null)
            log.setType(Log.Type.roleUpdate);
        else
            log.setType(Log.Type.roleCreate);
        logRepository.save(log);
        return roleRepository.save(role);
    }

    public void delete(String authorization) {
        Log log = new Log()
                .setId(UUID.randomUUID().toString())
                .setDate(new Date())
                .setActor(null)
                .setType(Log.Type.roleDelete);
        logRepository.save(log);
        Role role = roleRepository.findRoleByAuthorization(authorization);
        roleRepository.delete(role);
    }
}

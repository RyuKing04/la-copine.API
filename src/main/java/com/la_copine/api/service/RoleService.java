package com.la_copine.api.service;

import com.la_copine.api.model.Role;
import com.la_copine.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}

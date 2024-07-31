package com.la_copine.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.la_copine.api.dto.RoleRequestDTO;
import com.la_copine.api.model.Role;
import com.la_copine.api.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> getById(Integer id) {
        return roleRepository.findById(id);
    }

    public void saveOrUpdate(RoleRequestDTO role) {
        roleRepository.save(mapRequestDtoToEntity(role));
    }

    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }

    private Role mapRequestDtoToEntity(RoleRequestDTO roleDTO) {
        return Role.builder()
                .name(roleDTO.getName())
                .build();
    }
}

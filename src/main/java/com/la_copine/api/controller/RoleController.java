package com.la_copine.api.controller;

import com.la_copine.api.model.Role;
import com.la_copine.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping()
    public List<Role> getAll() {
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Role> getById(@PathVariable("id") Integer id) {
        return roleService.getById(id);
    }

    @PostMapping
    public void saveOrUpdate(@RequestBody Role role) {
        roleService.saveOrUpdate(role);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        roleService.delete(id);
    }
}

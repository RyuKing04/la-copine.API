package com.la_copine.api.controller;

import com.la_copine.api.model.Role;
import com.la_copine.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping()
    public List<Role> getAll() {
        return roleService.getAll();
    }
}

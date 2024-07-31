package com.la_copine.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.la_copine.api.dto.RoleRequestDTO;
import com.la_copine.api.model.Role;
import com.la_copine.api.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            List<Role> result = roleService.getAll();
            if (result.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") int id) {
        try {
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid id");
            }

            Optional<Role> result = roleService.getById(id);
            if (result == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody RoleRequestDTO role) {
        try {
            if (role == null) {
                return ResponseEntity.badRequest().body("Invalid role");
            }

            roleService.saveOrUpdate(role);
            return ResponseEntity.created(null).body(role);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") int id, @Valid @RequestBody RoleRequestDTO role) {
        try {
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid id");
            }
            if (role == null) {
                return ResponseEntity.badRequest().body("Invalid role");
            }

            roleService.saveOrUpdate(role);
            return ResponseEntity.ok(role);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        try {
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid id");
            }

            roleService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

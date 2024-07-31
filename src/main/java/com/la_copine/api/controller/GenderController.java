package com.la_copine.api.controller;

import java.util.List;

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

import com.la_copine.api.dto.GenderRequestDTO;
import com.la_copine.api.model.Gender;
import com.la_copine.api.service.GenderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gender")
public class GenderController {
    @Autowired
    GenderService genderService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            List<Gender> result = genderService.getAllGenders();
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

            Gender result = genderService.getGenderById(id);
            if (result == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody GenderRequestDTO gender) {
        try {
            if (gender == null) {
                return ResponseEntity.badRequest().body("Invalid gender");
            }

            genderService.createOrUpdateGender(gender);
            return ResponseEntity.created(null).body(gender);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") int id, @Valid @RequestBody GenderRequestDTO gender) {
        try {
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid id");
            }
            if (gender == null) {
                return ResponseEntity.badRequest().body("Invalid role");
            }

            genderService.createOrUpdateGender(gender);
            return ResponseEntity.ok(gender);
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

            genderService.deleteGender(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
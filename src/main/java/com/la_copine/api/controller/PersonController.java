package com.la_copine.api.controller;

import com.la_copine.api.dto.PersonRequestDTO;
import com.la_copine.api.dto.PersonResponseDTO;
import com.la_copine.api.model.Person;
import com.la_copine.api.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            List<PersonResponseDTO> result = personService.getAllPersons();
            if (result.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        try {
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid id");
            }

            PersonResponseDTO result = personService.getPersonById(id);
            if (result == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody PersonRequestDTO personDTO) {
        try {
            if (personDTO == null) {
                return ResponseEntity.badRequest().body("Invalid person");
            }

            Person personByEmail = personService.getPersonByEmail(personDTO.getEmail());

            if (personByEmail != null) {
                return ResponseEntity.badRequest().body("Email already exists");
            }

            return ResponseEntity.created(null).body(personService.createPerson(personDTO));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

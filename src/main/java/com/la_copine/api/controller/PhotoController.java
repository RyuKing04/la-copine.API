package com.la_copine.api.controller;

import com.la_copine.api.dto.PhotoRequestDTO;
import com.la_copine.api.dto.PhotoResponseDTO;
import com.la_copine.api.model.Photo;
import com.la_copine.api.service.PhotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        try {
            List<PhotoResponseDTO> result = photoService.getAllPhotos();
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

            PhotoResponseDTO result = photoService.getPhotoById(id);
            if (result == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody PhotoRequestDTO photoDTO) {
        try {
            if (photoDTO == null) {
                return ResponseEntity.badRequest().body("Invalid photo");
            }

            Photo newPhoto = photoService.createPhoto(photoDTO);
            return ResponseEntity.created(null).body(newPhoto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @Valid @RequestBody PhotoRequestDTO photoDTO) {
        try {
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid id");
            }

            Photo updatedPhoto = photoService.updatePhoto(id, photoDTO);
            if (updatedPhoto == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(updatedPhoto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            if (id <= 0) {
                return ResponseEntity.badRequest().body("Invalid id");
            }

            photoService.deletePhoto(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}

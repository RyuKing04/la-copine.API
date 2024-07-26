package com.la_copine.api.controller;

import com.la_copine.api.model.Preference;
import com.la_copine.api.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/preference")
public class PreferenceController {
    @Autowired
    PreferenceService preferenceService;

    @GetMapping()
    public List<Preference> getAll() {
        return preferenceService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Preference> getById(@PathVariable("id") Integer id){
        return preferenceService.getById(id);
    }

    @PostMapping
    public void saveOrUpdate(@RequestBody Preference preference){
        preferenceService.saveOrUpdate(preference);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        preferenceService.delete(id);
    }
}

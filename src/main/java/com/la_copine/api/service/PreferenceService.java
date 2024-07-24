package com.la_copine.api.service;

import com.la_copine.api.model.Preference;
import com.la_copine.api.repository.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreferenceService {
    @Autowired
    PreferenceRepository preferenceRepository;

    public List<Preference> getAll() {
        return preferenceRepository.findAll();
    }

    public Optional<Preference> getById(Integer id){ return preferenceRepository.findById(id); }

    public void saveOrUpdate(Preference preference){ preferenceRepository.save(preference); }

    public void delete(Integer id){ preferenceRepository.deleteById(id); }
}

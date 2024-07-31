package com.la_copine.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.la_copine.api.dto.GenderRequestDTO;
import com.la_copine.api.model.Gender;
import com.la_copine.api.repository.GenderRepository;

@Service
public class GenderService {
    @Autowired
    private GenderRepository genderRepository;

    public List<Gender> getAllGenders() {
        List<Gender> genders = genderRepository.findAll();
        return genders;
    }

    public Gender getGenderById(int id) {
        Gender gender = genderRepository.findById(id).orElse(null);
        return gender;
    }

    public Gender createOrUpdateGender(GenderRequestDTO gender) {
        Gender newGender = mapDtoToEntity(gender);
        return genderRepository.save(newGender);
    }

    private Gender mapDtoToEntity(GenderRequestDTO genderDTO) {
        return Gender.builder()
                .name(genderDTO.getName())
                .build();
    }

    public void deleteGender(int id) {
        genderRepository.deleteById(id);
    }
}

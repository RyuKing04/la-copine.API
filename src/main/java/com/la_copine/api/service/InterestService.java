package com.la_copine.api.service;

import com.la_copine.api.dto.InterestResponseDTO;
import com.la_copine.api.model.Interest;
import com.la_copine.api.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InterestService {
    @Autowired
    InterestRepository interestRepository;

    public List<InterestResponseDTO> getAll() {
        List<Interest> interests = interestRepository.findAll();
        return interests.stream().map(this::mapEntityToResponseDto).collect(Collectors.toList());
    }

    public Optional<Interest> getById(Integer id) {
        return interestRepository.findById(id);
    }

    public void saveOrUpdate(Interest preference) {
        interestRepository.save(preference);
    }

    public void delete(Integer id) {
        interestRepository.deleteById(id);
    }

    public InterestResponseDTO mapEntityToResponseDto(Interest interest) {
        InterestResponseDTO interestResponseDTO = new InterestResponseDTO();
        interestResponseDTO.setId(interest.getId());
        interestResponseDTO.setName(interest.getName());
        return interestResponseDTO;
    }
}

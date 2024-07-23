package com.la_copine.api.service;

import com.la_copine.api.model.Interest;
import com.la_copine.api.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterestService {
    @Autowired
    InterestRepository interestRepository;

    public List<Interest> getAll() {
        return interestRepository.findAll();
    }

    public Optional<Interest> getById(Integer id){ return interestRepository.findById(id); }

    public void saveOrUpdate(Interest preference){ interestRepository.save(preference); }

    public void delete(Integer id){ interestRepository.deleteById(id); }

}

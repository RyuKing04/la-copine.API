package com.la_copine.api.repository;

import com.la_copine.api.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface InterestRepository extends JpaRepository<Interest, Integer> {
    Set<Interest> findByPersonsId(Long id);
}

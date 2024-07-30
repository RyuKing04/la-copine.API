package com.la_copine.api.repository;

import com.la_copine.api.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Set<Photo> findByPersonId(Long personId);
}

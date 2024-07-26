package com.la_copine.api.repository;

import com.la_copine.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByEmail(String email);
    // find all persons with interests (many to many)
    
}

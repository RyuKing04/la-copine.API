package com.la_copine.api.service;

import com.la_copine.api.dto.InterestResponseDTO;
import com.la_copine.api.dto.PersonRequestDTO;
import com.la_copine.api.dto.PersonResponseDTO;
import com.la_copine.api.model.*;
import com.la_copine.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private InterestRepository interestRepository;

    @Autowired
    private PhotoRepository photoRepository;

    public List<PersonResponseDTO> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        return persons.stream().map(this::mapEntityToResponseDto).collect(Collectors.toList());
    }

    public PersonResponseDTO getPersonById(Long id) {
        Person person = personRepository.findById(id).orElse(null);
        return person != null ? mapEntityToResponseDto(person) : null;
    }

    public Person getPersonByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person createPerson(PersonRequestDTO personDTO) {
        Person person = new Person();
        mapDtoToEntity(personDTO, person);
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person personDetails) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found for this id :: " + id));
        return personRepository.save(personDetails);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    private void mapDtoToEntity(PersonRequestDTO personDTO, Person person) {
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setBio(personDTO.getBio());
        person.setBirthDate(personDTO.getBirthDate());
        person.setNickname(personDTO.getNickname());
        person.setEmail(personDTO.getEmail());
        person.setPopularity(personDTO.getPopularity());
        person.setPassword(personDTO.getPassword());
        person.setPhoneNumber(personDTO.getPhoneNumber());
        person.setAddress(personDTO.getAddress());
        person.setActive(personDTO.isActive());

        // Handle relationships
        Gender gender = genderRepository.findById(personDTO.getGenderId())
                .orElseThrow(() -> new RuntimeException("Gender not found for this id :: " + personDTO.getGenderId()));
        person.setGender(gender);

        Role role = roleRepository.findById(Math.toIntExact(personDTO.getRoleId()))
                .orElseThrow(() -> new RuntimeException("Role not found for this id :: " + personDTO.getRoleId()));
        person.setRole(role);

        Set<Interest> interests = personDTO.getInterestIds().stream()
                .map(id -> interestRepository.findById(Math.toIntExact(id))
                        .orElseThrow(() -> new RuntimeException("Interest not found for this id :: " + id)))
                .collect(Collectors.toSet());
        person.setInterests(interests);
    }

    private PersonResponseDTO mapEntityToResponseDto(Person person) {
        PersonResponseDTO personResponseDTO = new PersonResponseDTO();
        personResponseDTO.setId(person.getId());
        personResponseDTO.setFirstName(person.getFirstName());
        personResponseDTO.setLastName(person.getLastName());
        personResponseDTO.setGender(person.getGender());
        personResponseDTO.setBio(person.getBio());
        personResponseDTO.setBirthDate(person.getBirthDate());
        personResponseDTO.setNickname(person.getNickname());
        personResponseDTO.setEmail(person.getEmail());
        personResponseDTO.setPopularity(person.getPopularity());
        personResponseDTO.setRole(person.getRole());
        personResponseDTO.setPhoneNumber(person.getPhoneNumber());
        personResponseDTO.setAddress(person.getAddress());
        personResponseDTO.setActive(person.isActive());
        //personResponseDTO.setInterests(interestRepository.findByPersonsId(person.getId()).stream().map(Interest::getName).collect(Collectors.toSet()));
        // Map interests to InterestResponseDTO
        personResponseDTO.setInterests(interestRepository.findByPersonsId(person.getId()).stream().map(interest -> {
            InterestResponseDTO interestResponseDTO = new InterestResponseDTO();
            interestResponseDTO.setId(interest.getId());
            interestResponseDTO.setName(interest.getName());
            return interestResponseDTO;
        }).collect(Collectors.toSet()));
        personResponseDTO.setPhotos(photoRepository.findByPersonId(person.getId()).stream().map(Photo::getUrl).collect(Collectors.toSet()));
        return personResponseDTO;
    }
}

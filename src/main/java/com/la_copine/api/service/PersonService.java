package com.la_copine.api.service;

import com.la_copine.api.dto.InterestResponseDTO;
import com.la_copine.api.dto.PersonRequestDTO;
import com.la_copine.api.dto.PersonResponseDTO;
import com.la_copine.api.model.Person;
import com.la_copine.api.model.Photo;
import com.la_copine.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Person person = mapRequestDtoToEntity(personDTO);
        return personRepository.save(person);
    }

    public Person updatePerson(Long id, Person personDetails) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found for this id :: " + id));
        return personRepository.save(personDetails);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    private Person mapRequestDtoToEntity(PersonRequestDTO personDTO) {
        return Person.builder()
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .bio(personDTO.getBio())
                .birthDate(personDTO.getBirthDate())
                .nickname(personDTO.getNickname())
                .email(personDTO.getEmail())
                .popularity(personDTO.getPopularity())
                .password(personDTO.getPassword())
                .phoneNumber(personDTO.getPhoneNumber())
                .address(personDTO.getAddress())
                .active(personDTO.isActive())
                .gender(genderRepository.findById(personDTO.getGenderId())
                        .orElseThrow(() -> new RuntimeException("Role not found for this id :: " + personDTO.getGenderId()))
                )
                .role(roleRepository.findById(Math.toIntExact(personDTO.getRoleId()))
                        .orElseThrow(() -> new RuntimeException("Role not found for this id :: " + personDTO.getRoleId()))
                )
                .interests(personDTO.getInterestIds().stream()
                        .map(id -> interestRepository.findById(Math.toIntExact(id))
                                .orElseThrow(() -> new RuntimeException("Interest not found for this id :: " + id)))
                        .collect(Collectors.toSet())
                )
                .build();
    }

    private PersonResponseDTO mapEntityToResponseDto(Person person) {
        return PersonResponseDTO.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .gender(person.getGender())
                .bio(person.getBio())
                .birthDate(person.getBirthDate())
                .nickname(person.getNickname())
                .email(person.getEmail())
                .popularity(person.getPopularity())
                .role(person.getRole())
                .phoneNumber(person.getPhoneNumber())
                .address(person.getAddress())
                .active(person.isActive())
                .interests(interestRepository.findByPersonsId(person.getId()).stream().map(interest -> InterestResponseDTO.builder()
                        .id(interest.getId())
                        .name(interest.getName())
                        .build()).collect(Collectors.toSet())
                )
                .photos(photoRepository.findByPersonId(person.getId()).stream().map(Photo::getUrl).collect(Collectors.toSet()))
                .build();
    }
}

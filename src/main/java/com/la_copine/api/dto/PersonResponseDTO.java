package com.la_copine.api.dto;

import com.la_copine.api.model.Gender;
import com.la_copine.api.model.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class PersonResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String bio;
    private LocalDateTime birthDate;
    private String nickname;
    private String email;
    private int popularity;
    private Role role;
    private String phoneNumber;
    private String address;
    private boolean active;
    private Set<InterestResponseDTO> interests;
    private Set<String> photos;
}

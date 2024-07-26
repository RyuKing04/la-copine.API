package com.la_copine.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequestDTO {
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotBlank
    private Long genderId;

    @NotBlank
    @Size(max = 500)
    private String bio;

    @NotBlank
    private LocalDateTime birthDate;

    @Size(max = 50)
    private String nickname;

    @Size(max = 50)
    @NotBlank
    private String email;

    private int popularity;

    @Size(max = 50)
    @NotBlank
    private String password;

    @NotBlank
    private Long roleId;

    @Size(max = 15)
    private String phoneNumber;

    @Size(max = 100)
    private String address;

    private boolean active;

    private Set<Long> interestIds;
}

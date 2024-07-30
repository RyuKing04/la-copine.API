package com.la_copine.api.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Transactional
@Table(name = "person")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @Column(name = "bio", nullable = false)
    @Size(max = 500)
    private String bio;

    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @Column(name = "nickname")
    @Size(max = 50)
    private String nickname;

    @Column(name = "email", nullable = false, unique = true)
    @Size(max = 50)
    private String email;

    @Column(name = "popularity", nullable = false)
    private int popularity;

    @Column(name = "password", nullable = false)
    @Size(max = 50)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "phone_number", nullable = false, unique = true)
    @Size(max = 15)
    private String phoneNumber;

    @Column(name = "address", nullable = false)
    @Size(max = 100)
    private String address;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "person_interest",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id")
    )
    private Set<Interest> interests;
}

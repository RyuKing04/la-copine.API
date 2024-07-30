package com.la_copine.api.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Entity
@Builder
@Transactional
@Table(name = "photo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "url", nullable = false)
    @Size(max = 500)
    private String url;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
}

package com.la_copine.api.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Entity
@Builder
@Transactional
@Table(name = "message")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Person sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Person receiver;

    @Column(name = "type")
    @Size(max = 50)
    private String type;

    @Column(name = "text")
    @Size(max = 500)
    private String text;

    @Column(name = "message_status")
    @Size(max = 50)
    private String status;
}

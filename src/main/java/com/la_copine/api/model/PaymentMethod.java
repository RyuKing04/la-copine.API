package com.la_copine.api.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int persona_id;
    private String expiration_date;
    private String card_number;
    private String CVV;
}

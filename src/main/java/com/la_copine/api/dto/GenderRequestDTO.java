package com.la_copine.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenderRequestDTO {

    @NotBlank
    @NonNull
    @Size(max = 50, min = 3, message = "Name must be between 3 and 50 characters")
    private String name;

}

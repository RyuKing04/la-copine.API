package com.la_copine.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoRequestDTO {
    @NotBlank
    private String url;
    @NotBlank
    private Long personId;
}

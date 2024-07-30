package com.la_copine.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResponseDTO {
    private Long id;
    private String url;
    private Long personId;
}

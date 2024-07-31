package com.la_copine.api.dto;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDTO {
  @NotBlank
  @NonNull
  @Size(max = 50, min = 3, message = "Name must be between 3 and 50 characters")
  private String name;
}

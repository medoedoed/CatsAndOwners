package ru.medoedoed.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColorDto {
  private long id;

  @NotBlank(message = "Color name field shouldn't be blank")
  private String colorName;
}

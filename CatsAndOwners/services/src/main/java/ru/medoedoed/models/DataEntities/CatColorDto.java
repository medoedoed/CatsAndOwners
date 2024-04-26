package ru.medoedoed.models.DataEntities;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatColorDto {
  private long id;

  @NotBlank(message = "Color name shouldn't be blank")
  private String colorName;
}

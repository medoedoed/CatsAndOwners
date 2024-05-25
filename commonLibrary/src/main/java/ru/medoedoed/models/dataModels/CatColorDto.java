package ru.medoedoed.models.dataModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatColorDto {
  private Long id;

  @NotBlank(message = "Color name shouldn't be blank")
  private String colorName;

  @JsonCreator
  public CatColorDto(@JsonProperty("id") Long id, @JsonProperty("colorName") String colorName) {
    this.id = id;
    this.colorName = colorName;
  }
}

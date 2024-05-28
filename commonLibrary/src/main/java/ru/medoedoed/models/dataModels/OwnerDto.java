package ru.medoedoed.models.dataModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OwnerDto {
  private Long id;

  @NotBlank(message = "Name field shouldn't be blank")
  private String name;

  @PastOrPresent(message = "The birth date shouldn't have to be in the future ")
  private LocalDate birthDate;

  private List<Long> catsId;

  @JsonCreator
  public OwnerDto(
      @JsonProperty("id") Long id,
      @JsonProperty("name") String name,
      @JsonProperty("birthDate") LocalDate birthDate,
      @JsonProperty("catsId") List<Long> catsId) {
    this.id = id;
    this.name = name;
    this.birthDate = birthDate;
    this.catsId = catsId;
  }
}

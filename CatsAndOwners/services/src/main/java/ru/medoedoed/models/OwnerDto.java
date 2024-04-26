package ru.medoedoed.models;

import jakarta.validation.constraints.NotBlank;
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

  @NotBlank(message = "Birth date field shouldn't be blank")
  private LocalDate birthDate;

  private List<Long> catsId;
}

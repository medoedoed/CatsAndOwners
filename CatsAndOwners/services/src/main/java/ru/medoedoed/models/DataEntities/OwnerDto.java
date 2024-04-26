package ru.medoedoed.models.DataEntities;

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
}

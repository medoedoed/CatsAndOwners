package ru.medoedoed.models.dataEntities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatDto {
  private Long id;

  @NotBlank(message = "Name field shouldn't be blank")
  private String name;

  @PastOrPresent(message = "The birth date shouldn't have to be in the future ")
  private LocalDate birthDate;

  private String breed;

  @NotNull
  private Long colorId;

  @NotNull
  private Long ownerId;

  private List<Long> friendsId;
}

package ru.medoedoed.models;

import jakarta.validation.constraints.NotBlank;
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

  @NotBlank(message = "ColorID shouldn't be blank")
  private long colorId;

  @NotBlank(message = "OwnerID shouldn't be blank")
  private long ownerId;

  private List<Long> friendsId;
}

package ru.medoedoed.models.dataEntities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ru.medoedoed.utils.*;

@Data
@Builder
public class UserDto {
  private Long id;

  @NotBlank(message = "Name field shouldn't be blank")
  private String username;

  @NotNull
  private Long ownerId;

  @NotBlank
  private String password;

  @NotBlank
  private Role role;
}

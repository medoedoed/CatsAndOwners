package ru.medoedoed.models.DataEntities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ru.medoedoed.utils.UserRole;

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
  private UserRole role;
}

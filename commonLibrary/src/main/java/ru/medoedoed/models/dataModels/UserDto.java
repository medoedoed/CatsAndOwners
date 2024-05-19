package ru.medoedoed.models.dataModels;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;
import ru.medoedoed.role.Role;

@Data
@Builder
public class UserDto {
  private Long id;

  @NotBlank(message = "Name field shouldn't be blank")
  private String username;

  @Nullable
  private Long ownerId;

  @NotBlank
  private String password;

  @NotBlank
  private Role role;
}

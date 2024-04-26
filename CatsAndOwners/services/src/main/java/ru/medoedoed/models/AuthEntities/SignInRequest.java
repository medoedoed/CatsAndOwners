package ru.medoedoed.models.AuthEntities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInRequest {
  @NotBlank
  @Size(min = 5, max = 15)
  private String username;

  @NotBlank
  @Size(min = 6, max = 24)
  private String password;
}

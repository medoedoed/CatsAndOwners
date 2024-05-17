package ru.medoedoed.authModels;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInRequest {
  @NotBlank
  @Size(min = 5, max = 15)
  private String username;

  @NotBlank
  @Size(min = 6, max = 24)
  private String password;
}

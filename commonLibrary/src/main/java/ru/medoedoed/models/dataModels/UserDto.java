package ru.medoedoed.models.dataModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

  @JsonCreator
  public UserDto(
      @JsonProperty("id") Long id,
      @JsonProperty("username") String username,
      @JsonProperty("ownerId") @Nullable Long ownerId,
      @JsonProperty("password") String password,
      @JsonProperty("role") Role role) {
    this.id = id;
    this.username = username;
    this.ownerId = ownerId;
    this.password = password;
    this.role = role;
  }
}

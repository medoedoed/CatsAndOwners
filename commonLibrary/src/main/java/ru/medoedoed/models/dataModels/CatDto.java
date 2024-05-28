package ru.medoedoed.models.dataModels;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
  
  private Long ownerId;

  private List<Long> friendsId;

  @JsonCreator
  public CatDto(
      @JsonProperty("id") Long id,
      @JsonProperty("name") String name,
      @JsonProperty("birthDate") LocalDate birthDate,
      @JsonProperty("breed") String breed,
      @JsonProperty("colorId") Long colorId,
      @JsonProperty("ownerId") Long ownerId,
      @JsonProperty("friendsId") List<Long> friendsId) {
    this.id = id;
    this.name = name;
    this.birthDate = birthDate;
    this.breed = breed;
    this.colorId = colorId;
    this.ownerId = ownerId;
    this.friendsId = friendsId;
  }
}

package ru.medoedoed.utils;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.medoedoed.applicators.DataApplicator;
import ru.medoedoed.dao.OwnerDao;
import ru.medoedoed.jpa.UserJpa;
import ru.medoedoed.models.dataModels.UserDto;

@Component
@RequiredArgsConstructor
public class UserApplicator implements DataApplicator<UserDto, UserJpa> {
  private final OwnerDao ownerDao;

  @Override
  public UserJpa DataToJpa(@NotNull UserDto data) {
    var user = new UserJpa();
    user.setUsername(data.getUsername());
    user.setPassword(data.getPassword());
    user.setRole(data.getRole());
    user.setId(data.getId());
    if (data.getOwnerId() != null) {
      user.setOwner(
              ownerDao
                      .findById(data.getOwnerId())
                      .orElseThrow(
                              () ->
                                      new EntityNotFoundException(
                                              "Owner with id " + data.getOwnerId() + " not found")));
    } else {
      user.setOwner(null);
    }

    return user;
  }

  @Override
  public UserDto JpaToData(UserJpa jpa) {
    Long ownerId = null;
    if (jpa.getOwner() != null) ownerId = jpa.getOwner().getId();
    return UserDto.builder()
            .id(jpa.getId())
            .username(jpa.getUsername())
            .password(jpa.getPassword())
            .role(jpa.getRole())
            .ownerId(ownerId)
            .build();
  }
}

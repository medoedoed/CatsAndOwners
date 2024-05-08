package ru.medoedoed.services.dataApplicator;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.medoedoed.dao.OwnerDao;
import ru.medoedoed.models.DataEntities.UserDto;
import ru.medoedoed.models.User;

@Component
@RequiredArgsConstructor
public class UserApplicator implements DataApplicator<UserDto, User> {
  private final OwnerDao ownerDao;

  @Override
  public User DataToJpa(@NotNull UserDto data) {
    var user = new User();
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
    }

    return user;
  }

  @Override
  public UserDto JpaToData(User jpa) {
    return UserDto.builder()
        .id(jpa.getId())
        .username(jpa.getUsername())
        .password(jpa.getPassword())
        .role(jpa.getRole())
        .ownerId(jpa.getOwner().getId())
        .build();
  }
}

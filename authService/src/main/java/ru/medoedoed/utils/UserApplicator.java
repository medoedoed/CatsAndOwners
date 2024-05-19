package ru.medoedoed.utils;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.medoedoed.crudService.DataApplicator;
import ru.medoedoed.jpaEntity.OwnerJpa;
import ru.medoedoed.jpaEntity.UserJpa;
import ru.medoedoed.models.dataModels.UserDto;

@Component
@RequiredArgsConstructor
public class UserApplicator implements DataApplicator<UserDto, UserJpa> {
  @Override
  public UserJpa DataToJpa(@NotNull UserDto data) {
    var user = new UserJpa();
    user.setUsername(data.getUsername());
    user.setPassword(data.getPassword());
    user.setRole(data.getRole());
    user.setId(data.getId());
    if (data.getOwnerId() != null) {
      user.setOwner(
              new OwnerJpa());
//          (OwnerJpa) rabbitTemplate.convertSendAndReceive("owner.request", data.getOwnerId())); TODO
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

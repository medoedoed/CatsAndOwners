package ru.medoedoed.utils;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.medoedoed.crudService.DataApplicator;
import ru.medoedoed.dao.CatDao;
import ru.medoedoed.jpaEntity.CatColorJpa;
import ru.medoedoed.jpaEntity.CatJpa;
import ru.medoedoed.jpaEntity.OwnerJpa;
import ru.medoedoed.models.dataModels.CatDto;

@Component
@RequiredArgsConstructor
public class CatApplicator implements DataApplicator<CatDto, CatJpa> {
  private final CatDao catDao;
  private final RabbitTemplate rabbitTemplate;

  @Override
  public CatJpa DataToJpa(@NotNull CatDto data) {
    var cat = new CatJpa();
    cat.setId(data.getId());
    cat.setName(data.getName());
    cat.setBirthDate(data.getBirthDate());
    cat.setBreed(data.getBreed());
    cat.setColor((CatColorJpa) rabbitTemplate.convertSendAndReceive("color.request", data.getColorId()));
    cat.setOwnerJpa((OwnerJpa) rabbitTemplate.convertSendAndReceive("color.request", data.getOwnerId()));
    if (data.getFriendsId() != null) {
      cat.setFriends(
          data.getFriendsId().stream()
              .map(friendId -> catDao.findById(friendId).orElseThrow())
              .toList());
    }

    return cat;
  }

  @Override
  public CatDto JpaToData(@NotNull CatJpa jpa) {
    return CatDto.builder()
        .id(jpa.getId())
        .name(jpa.getName())
        .birthDate(jpa.getBirthDate())
        .breed(jpa.getBreed())
        .colorId(jpa.getColor().getId())
        .ownerId(jpa.getOwnerJpa().getId())
        .friendsId(jpa.getFriends().stream().map(CatJpa::getId).toList())
        .build();
  }
}

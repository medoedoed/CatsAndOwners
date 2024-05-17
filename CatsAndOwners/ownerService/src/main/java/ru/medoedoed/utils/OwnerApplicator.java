package ru.medoedoed.utils;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.medoedoed.crudService.DataApplicator;
import ru.medoedoed.jpaEntity.CatJpa;
import ru.medoedoed.jpaEntity.OwnerJpa;
import ru.medoedoed.models.dataModels.OwnerDto;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class OwnerApplicator implements DataApplicator<OwnerDto, OwnerJpa> {
  private final RabbitTemplate rabbitTemplate;

  @Override
  public OwnerJpa DataToJpa(@NotNull OwnerDto data) {
    var owner = new OwnerJpa();
    owner.setId(data.getId());
    owner.setName(data.getName());
    owner.setBirthDate(data.getBirthDate());
    //TODO
    if (data.getCatsId() != null) {
      owner.setCats((ArrayList<CatJpa>) rabbitTemplate.convertSendAndReceive("cats.request", data.getCatsId()));
    }
    return owner;
  }

  @Override
  public OwnerDto JpaToData(@NotNull OwnerJpa jpa) {
    return OwnerDto.builder()
        .id(jpa.getId())
        .name(jpa.getName())
        .birthDate(jpa.getBirthDate())
        .catsId(jpa.getCats().stream().map(CatJpa::getId).toList())
        .build();
  }
}

package ru.medoedoed.utils;

import jakarta.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.medoedoed.crudService.DataApplicator;
import ru.medoedoed.dao.CatDao;
import ru.medoedoed.jpaEntity.CatColorJpa;
import ru.medoedoed.jpaEntity.CatJpa;
import ru.medoedoed.jpaEntity.OwnerJpa;
import ru.medoedoed.models.dataModels.CatColorDto;
import ru.medoedoed.models.dataModels.CatDto;
import ru.medoedoed.models.dataModels.OwnerDto;
import ru.medoedoed.rabbitmq.CatRabbitProducer;

@Component
@RequiredArgsConstructor
public class CatApplicator implements DataApplicator<CatDto, CatJpa> {
  private final CatDao catDao;
  private final CatRabbitProducer catRabbitProducer;

  @Override
  public CatJpa DataToJpa(@NotNull CatDto data) {
    CatJpa cat = new CatJpa();
    cat.setId(data.getId());
    cat.setName(data.getName());
    cat.setBirthDate(data.getBirthDate());
    cat.setBreed(data.getBreed());

    CatColorJpa color = createColorJpa(data.getColorId());
    cat.setColor(color);

    OwnerJpa owner = createOwnerJpa(data.getOwnerId(), cat);
    cat.setOwner(owner);

    cat.setFriends(
        Optional.ofNullable(data.getFriendsId()).orElse(Collections.emptyList()).stream()
            .map(friendId -> catDao.findById(friendId).orElseThrow())
            .toList());

    return cat;
  }

  private CatColorJpa createColorJpa(Long colorId) {
    CatColorDto colorDto = catRabbitProducer.getColorById(colorId);
    CatColorJpa color = new CatColorJpa();
    color.setId(colorDto.getId());
    color.setColorName(colorDto.getColorName());
    return color;
  }

  private OwnerJpa createOwnerJpa(Long ownerId, CatJpa cat) {
    OwnerDto ownerDto = catRabbitProducer.getOwnerById(ownerId);
    OwnerJpa owner = new OwnerJpa();
    owner.setId(ownerDto.getId());
    owner.setName(ownerDto.getName());
    owner.setBirthDate(ownerDto.getBirthDate());
    owner.setCats(
        ownerDto.getCatsId().stream().map(catDao::findById).flatMap(Optional::stream).toList());
    return owner;
  }

  @Override
  public CatDto JpaToData(@NotNull CatJpa jpa) {
    return CatDto.builder()
        .id(jpa.getId())
        .name(jpa.getName())
        .birthDate(jpa.getBirthDate())
        .breed(jpa.getBreed())
        .colorId(jpa.getColor().getId())
        .ownerId(jpa.getOwner().getId())
        .friendsId(jpa.getFriends().stream().map(CatJpa::getId).toList())
        .build();
  }
}


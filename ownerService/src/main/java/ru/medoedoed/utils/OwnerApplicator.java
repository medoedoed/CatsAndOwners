package ru.medoedoed.utils;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.medoedoed.applicators.DataApplicator;
import ru.medoedoed.dao.CatDao;
import ru.medoedoed.jpa.CatJpa;
import ru.medoedoed.jpa.OwnerJpa;
import ru.medoedoed.models.dataModels.OwnerDto;


@Component
@RequiredArgsConstructor
public class OwnerApplicator implements DataApplicator<OwnerDto, OwnerJpa> {
  private final CatDao catDao;

  @Override
  public OwnerJpa DataToJpa(@NotNull OwnerDto data) {
    var owner = new OwnerJpa();
    owner.setId(data.getId());
    owner.setName(data.getName());
    owner.setBirthDate(data.getBirthDate());
    if (owner.getCats() != null) {
      owner.setCats(
              data.getCatsId().stream().map(catId -> catDao.findById(catId).orElseThrow()).toList());
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

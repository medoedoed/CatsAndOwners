package ru.medoedoed.services.dataApplicator;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.medoedoed.dao.CatDao;
import ru.medoedoed.models.Cat;
import ru.medoedoed.models.Owner;
import ru.medoedoed.models.DataEntities.OwnerDto;

@Component
@RequiredArgsConstructor
public class OwnerApplicator implements DataApplicator<OwnerDto, Owner> {
  private final CatDao catDao;

  @Override
  public Owner DataToJpa(@NotNull OwnerDto data) {
    var owner = new Owner();
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
  public OwnerDto JpaToData(@NotNull Owner jpa) {
    return OwnerDto.builder()
        .id(jpa.getId())
        .name(jpa.getName())
        .birthDate(jpa.getBirthDate())
        .catsId(jpa.getCats().stream().map(Cat::getId).toList())
        .build();
  }
}

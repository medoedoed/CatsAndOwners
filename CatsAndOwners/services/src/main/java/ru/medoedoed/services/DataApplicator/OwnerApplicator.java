package ru.medoedoed.services.DataApplicator;

import lombok.RequiredArgsConstructor;
import ru.medoedoed.dao.CatDao;
import ru.medoedoed.models.Cat;
import ru.medoedoed.models.Owner;
import ru.medoedoed.models.OwnerDto;

@RequiredArgsConstructor
public class OwnerApplicator implements DataApplicator<OwnerDto, Owner> {
  private final CatDao catDao;

  @Override
  public Owner DataToJpa(OwnerDto data) {
    var owner = new Owner();
    owner.setId(data.getId());
    owner.setName(data.getName());
    owner.setBirthDate(data.getBirthDate());
    owner.setCats(
        data.getCatsId().stream().map(catId -> catDao.findById(catId).orElseThrow()).toList());
    return owner;
  }

  @Override
  public OwnerDto JpaToData(Owner jpa) {
    return OwnerDto.builder()
        .id(jpa.getId())
        .name(jpa.getName())
        .birthDate(jpa.getBirthDate())
        .catsId(jpa.getCats().stream().map(Cat::getId).toList())
        .build();
  }
}

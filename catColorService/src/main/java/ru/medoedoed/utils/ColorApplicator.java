package ru.medoedoed.utils;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import ru.medoedoed.crudService.DataApplicator;
import ru.medoedoed.jpaEntity.CatColorJpa;
import ru.medoedoed.models.CatColor;
import ru.medoedoed.models.dataEntities.CatColorDto;
import ru.medoedoed.models.dataModels.CatColorDto;

@Component
public class ColorApplicator implements DataApplicator<CatColorDto, CatColorJpa> {
  @Override
  public CatColorJpa DataToJpa(@NotNull CatColorDto data) {
    var catColor = new CatColorJpa();
    catColor.setId(data.getId());
    catColor.setColorName(data.getColorName());
    return catColor;
  }

  @Override
  public CatColorDto JpaToData(@NotNull CatColorJpa jpa) {
    return CatColorDto.builder().id(jpa.getId()).colorName(jpa.getColorName()).build();
  }
}

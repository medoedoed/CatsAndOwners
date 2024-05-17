package ru.medoedoed.utils;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import ru.medoedoed.models.CatColor;
import ru.medoedoed.models.dataEntities.CatColorDto;

@Component
public class ColorApplicator implements DataApplicator<CatColorDto, CatColor> {
  @Override
  public CatColor DataToJpa(@NotNull CatColorDto data) {
    var catColor = new CatColor();
    catColor.setId(data.getId());
    catColor.setColorName(data.getColorName());
    return catColor;
  }

  @Override
  public CatColorDto JpaToData(@NotNull CatColor jpa) {
    return CatColorDto.builder().id(jpa.getId()).colorName(jpa.getColorName()).build();
  }
}

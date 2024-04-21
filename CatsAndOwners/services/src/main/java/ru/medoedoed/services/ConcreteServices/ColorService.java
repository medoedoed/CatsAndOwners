package ru.medoedoed.services.ConcreteServices;

import ru.medoedoed.dao.ColorDao;
import ru.medoedoed.models.CatColor;
import ru.medoedoed.models.CatColorDto;
import ru.medoedoed.services.DataApplicator.ColorApplicator;
import ru.medoedoed.services.ServiceImpl;

public class ColorService extends ServiceImpl<CatColor, CatColorDto> {
  public ColorService(ColorDao colorDao, ColorApplicator colorApplicator) {
    super(colorDao, colorApplicator);
  }
}

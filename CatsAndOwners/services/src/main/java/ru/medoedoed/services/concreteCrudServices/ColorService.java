package ru.medoedoed.services.concreteCrudServices;

import org.springframework.stereotype.Service;
import ru.medoedoed.dao.ColorDao;
import ru.medoedoed.models.CatColor;
import ru.medoedoed.models.dataEntities.CatColorDto;
import ru.medoedoed.services.ServiceImpl;
import ru.medoedoed.services.dataApplicator.ColorApplicator;

@Service
public class ColorService extends ServiceImpl<CatColor, CatColorDto> {
  public ColorService(ColorDao colorDao, ColorApplicator colorApplicator) {
    super(colorDao, colorApplicator);
  }
}

package ru.medoedoed.services;

import org.springframework.stereotype.Service;
import ru.medoedoed.crudService.ServiceImpl;
import ru.medoedoed.dao.ColorDao;
import ru.medoedoed.jpaEntity.CatColorJpa;
import ru.medoedoed.models.dataModels.CatColorDto;
import ru.medoedoed.utils.ColorApplicator;

@Service
public class ColorService extends ServiceImpl<CatColorJpa, CatColorDto> {
  public ColorService(ColorDao colorDao, ColorApplicator colorApplicator) {
    super(colorDao, colorApplicator);
  }
}

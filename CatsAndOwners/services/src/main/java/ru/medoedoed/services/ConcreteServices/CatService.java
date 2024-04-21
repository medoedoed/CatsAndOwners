package ru.medoedoed.services.ConcreteServices;

import ru.medoedoed.dao.CatDao;
import ru.medoedoed.models.Cat;
import ru.medoedoed.models.CatDto;
import ru.medoedoed.services.DataApplicator.CatApplicator;
import ru.medoedoed.services.ServiceImpl;

public class CatService extends ServiceImpl<Cat, CatDto> {
  public CatService(CatDao catDao, CatApplicator catApplicator) {
    super(catDao, catApplicator);
  }
}

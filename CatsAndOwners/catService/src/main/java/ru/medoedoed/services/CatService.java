package ru.medoedoed.services;

import org.springframework.stereotype.Service;
import ru.medoedoed.dao.CatDao;
import ru.medoedoed.models.Cat;
import ru.medoedoed.models.dataEntities.CatDto;
import ru.medoedoed.services.dataApplicator.CatApplicator;
import ru.medoedoed.services.ServiceImpl;

@Service
public class CatService extends ServiceImpl<Cat, CatDto> {
  public CatService(CatDao catDao, CatApplicator catApplicator) {
    super(catDao, catApplicator);
  }
}

package ru.medoedoed.services;

import org.springframework.stereotype.Service;
import ru.medoedoed.crudService.ServiceImpl;
import ru.medoedoed.dao.CatDao;
import ru.medoedoed.jpa.CatJpa;
import ru.medoedoed.models.dataModels.CatDto;
import ru.medoedoed.utils.CatApplicator;

@Service
public class CatService extends ServiceImpl<CatJpa, CatDto> {
  public CatService(CatDao catDao, CatApplicator catApplicator) {
    super(catDao, catApplicator);
  }
}

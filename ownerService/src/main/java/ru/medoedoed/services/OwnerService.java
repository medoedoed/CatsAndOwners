package ru.medoedoed.services;

import org.springframework.stereotype.Service;
import ru.medoedoed.crudService.ServiceImpl;
import ru.medoedoed.dao.OwnerDao;
import ru.medoedoed.jpa.OwnerJpa;
import ru.medoedoed.models.dataModels.OwnerDto;
import ru.medoedoed.utils.OwnerApplicator;

@Service
public class OwnerService extends ServiceImpl<OwnerJpa, OwnerDto> {
  public OwnerService(OwnerDao ownerDao, OwnerApplicator ownerApplicator) {
    super(ownerDao, ownerApplicator);
  }
}

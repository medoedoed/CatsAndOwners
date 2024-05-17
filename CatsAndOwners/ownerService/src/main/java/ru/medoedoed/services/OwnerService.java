package ru.medoedoed.services;

import org.springframework.stereotype.Service;
import ru.medoedoed.dao.OwnerDao;
import ru.medoedoed.models.Owner;
import ru.medoedoed.models.dataEntities.OwnerDto;
import ru.medoedoed.services.dataApplicator.OwnerApplicator;

@Service
public class OwnerService extends ServiceImpl<Owner, OwnerDto> {
  public OwnerService(OwnerDao ownerDao, OwnerApplicator ownerApplicator) {
    super(ownerDao, ownerApplicator);
  }
}

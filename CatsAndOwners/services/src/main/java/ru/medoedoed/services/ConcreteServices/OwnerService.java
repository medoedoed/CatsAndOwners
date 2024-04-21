package ru.medoedoed.services.ConcreteServices;

import ru.medoedoed.dao.OwnerDao;
import ru.medoedoed.models.Owner;
import ru.medoedoed.models.OwnerDto;
import ru.medoedoed.services.DataApplicator.OwnerApplicator;
import ru.medoedoed.services.ServiceImpl;

public class OwnerService extends ServiceImpl<Owner, OwnerDto> {
  public OwnerService(OwnerDao ownerDao, OwnerApplicator ownerApplicator) {
    super(ownerDao, ownerApplicator);
  }
}

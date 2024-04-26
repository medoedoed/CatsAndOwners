package ru.medoedoed.services.concreteServices;

import org.springframework.stereotype.Service;
import ru.medoedoed.dao.OwnerDao;
import ru.medoedoed.models.Owner;
import ru.medoedoed.models.DataEntities.OwnerDto;
import ru.medoedoed.services.ServiceImpl;
import ru.medoedoed.services.dataApplicator.OwnerApplicator;

@Service
public class OwnerService extends ServiceImpl<Owner, OwnerDto> {
  public OwnerService(OwnerDao ownerDao, OwnerApplicator ownerApplicator) {
    super(ownerDao, ownerApplicator);
  }
}

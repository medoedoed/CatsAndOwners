package ru.medoedoed.crudService;

import org.springframework.stereotype.Component;

@Component
public interface DataApplicator<DataT, JpaT> {
  JpaT DataToJpa(DataT data);
  DataT JpaToData(JpaT jpa);
}

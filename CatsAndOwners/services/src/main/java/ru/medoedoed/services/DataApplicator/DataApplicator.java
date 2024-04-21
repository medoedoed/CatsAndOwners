package ru.medoedoed.services.DataApplicator;

public interface DataApplicator<DataT, JpaT> {
  JpaT DataToJpa(DataT data);
  DataT JpaToData(JpaT jpa);
}

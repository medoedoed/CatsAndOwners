package ru.medoedoed.applicators;

public interface DataApplicator<DataT, JpaT> {
  JpaT DataToJpa(DataT data);
  DataT JpaToData(JpaT jpa);
}

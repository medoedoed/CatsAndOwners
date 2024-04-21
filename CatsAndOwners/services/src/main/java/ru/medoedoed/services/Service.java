package ru.medoedoed.services;

import java.util.List;

public interface Service<DataT> {
  Long save(DataT entity);

  DataT getById(Long id);

  void update(DataT entity);

  void delete(Long id);

  List<DataT> getAll();
}

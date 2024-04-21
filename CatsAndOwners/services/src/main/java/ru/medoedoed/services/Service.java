package ru.medoedoed.services;

import java.util.List;

public interface Service<DataT> {
  Long save(DataT data);

  DataT getById(Long id);

  void update(DataT data);

  void delete(Long id);

  List<DataT> getAll();
}

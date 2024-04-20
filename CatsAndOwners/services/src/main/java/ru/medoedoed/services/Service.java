package ru.medoedoed.services;

import java.util.List;

public interface Service<T> {
  Long save(T entity);

  T getById(Long id);

  void update(T entity);

  void delete(Long id);

  List<T> getAll();
}

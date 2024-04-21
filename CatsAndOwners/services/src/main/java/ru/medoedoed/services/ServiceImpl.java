package ru.medoedoed.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.medoedoed.services.DataApplicator.DataApplicator;

import java.util.List;

@RequiredArgsConstructor
public class ServiceImpl<JpaT, DataT> implements Service<DataT> {
  protected final JpaRepository<JpaT, Long> jpaRepository;
  protected final DataApplicator<DataT, JpaT> applicator;
  @Override
  public Long save(DataT entity) {
    var res = jpaRepository.save(applicator.DataToJpa(entity));
    return 0L;
  }

  @Override
  public DataT getById(Long id) {
    return null;
  }

  @Override
  public void update(DataT entity) {

  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public List<DataT> getAll() {
    return List.of();
  }
}

package ru.medoedoed.services;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.medoedoed.models.JpaEntity;
import ru.medoedoed.services.DataApplicator.DataApplicator;

@RequiredArgsConstructor
public class ServiceImpl<JpaT extends JpaEntity, DataT> implements Service<DataT> {
  protected final JpaRepository<JpaT, Long> jpaRepository;
  protected final DataApplicator<DataT, JpaT> applicator;

  @Override
  public Long save(DataT data) {
    var entity = jpaRepository.save(applicator.DataToJpa(data));
    return entity.getId();
  }

  @Override
  public DataT getById(Long id) {
    return applicator.JpaToData(
        jpaRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found")));
  }

  @Override
  public void update(DataT data) {
    jpaRepository.save(applicator.DataToJpa(data));
  }

  @Override
  public void delete(Long id) {
    jpaRepository.deleteById(id);
  }

  @Override
  public List<DataT> getAll() {
    return jpaRepository.findAll().stream().map(applicator::JpaToData).toList();
  }
}

package ru.medoedoed.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.medoedoed.jpaEntity.CatColorJpa;

@Repository
public interface ColorDao extends JpaRepository<CatColorJpa, Long> {}

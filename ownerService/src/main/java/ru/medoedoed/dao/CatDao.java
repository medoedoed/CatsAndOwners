package ru.medoedoed.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.medoedoed.jpa.CatJpa;

@Repository
public interface CatDao extends JpaRepository<CatJpa, Long> {}

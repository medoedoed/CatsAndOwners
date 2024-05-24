package ru.medoedoed.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.medoedoed.jpa.OwnerJpa;

@Repository
public interface OwnerDao extends JpaRepository<OwnerJpa, Long> {}

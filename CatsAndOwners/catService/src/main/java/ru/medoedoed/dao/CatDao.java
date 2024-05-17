package ru.medoedoed.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.medoedoed.models.Cat;

@Repository
public interface CatDao extends JpaRepository<Cat, Long> {}

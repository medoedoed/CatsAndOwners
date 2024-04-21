package ru.medoedoed.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.medoedoed.models.CatColor;

@Repository
public interface ColorDao extends JpaRepository<CatColor, Long> {}

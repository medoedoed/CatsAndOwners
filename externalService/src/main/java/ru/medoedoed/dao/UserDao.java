package ru.medoedoed.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.medoedoed.jpa.UserJpa;

@Repository
public interface UserDao extends JpaRepository<UserJpa, Long> {
  Optional<UserJpa> findByUsername(String username);
}

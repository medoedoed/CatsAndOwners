package ru.medoedoed.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.medoedoed.models.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {}

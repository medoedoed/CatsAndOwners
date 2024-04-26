package ru.medoedoed.services.concreteServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.medoedoed.dao.UserDao;
import ru.medoedoed.models.User;
import ru.medoedoed.utils.UserRole;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserDao userDao;

  public User save(User user) {
    return userDao.save(user);
  }


  public User create(User user) {
    if (userDao.existsByUsername(user.getUsername())) {
      throw new RuntimeException("Пользователь с таким именем уже существует");
    }

    return userDao.save(user);
  }

  public User getByUsername(String username) {
    return userDao.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

  }

  public UserDetailsService userDetailsService() {
    return this::getByUsername;
  }

  public User getCurrentUser() {
    var username = SecurityContextHolder.getContext().getAuthentication().getName();
    return getByUsername(username);
  }

  @Deprecated
  public void getAdmin() {
    var user = getCurrentUser();
    user.setRole(UserRole.admin);
    save(user);
  }
}

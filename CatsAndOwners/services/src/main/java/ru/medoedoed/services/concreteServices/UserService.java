package ru.medoedoed.services.concreteServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.medoedoed.dao.OwnerDao;
import ru.medoedoed.dao.UserDao;
import ru.medoedoed.models.DataEntities.OwnerDto;
import ru.medoedoed.models.DataEntities.UserDto;
import ru.medoedoed.models.User;
import ru.medoedoed.services.dataApplicator.OwnerApplicator;
import ru.medoedoed.services.dataApplicator.UserApplicator;
import ru.medoedoed.utils.UserRole;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserDao userDao;
  private final OwnerDao ownerDao;
  private final UserApplicator userApplicator;
  private final OwnerApplicator ownerApplicator;

  public Long save(UserDto userData) {
    return userDao.save(userApplicator.DataToJpa(userData)).getId();
  }

  public Long createOwner(OwnerDto ownerData) {
    var userData = getCurrentUser();
    if (userData.getOwnerId() != null) {
      throw new IllegalArgumentException("You already have owner");
    }
    return ownerDao.save(ownerApplicator.DataToJpa(ownerData)).getId();
  }

  public User getByUsername(String username) {
    return userDao
        .findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
  }

  public UserDetailsService userDetailsService() {
    return this::getByUsername;
  }

  public UserDto getCurrentUser() {
    var username = SecurityContextHolder.getContext().getAuthentication().getName();
    return userApplicator.JpaToData(getByUsername(username));
  }

  @Deprecated
  public void getAdmin() {
    var user = getCurrentUser();
    user.setRole(UserRole.admin);
    save(user);
  }
}

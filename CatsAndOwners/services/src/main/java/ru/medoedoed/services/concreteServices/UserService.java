package ru.medoedoed.services.concreteServices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.medoedoed.dao.OwnerDao;
import ru.medoedoed.dao.UserDao;
import ru.medoedoed.models.DataEntities.OwnerDto;
import ru.medoedoed.models.DataEntities.UserDto;
import ru.medoedoed.models.User;
import ru.medoedoed.services.ServiceImpl;
import ru.medoedoed.services.dataApplicator.DataApplicator;
import ru.medoedoed.services.dataApplicator.OwnerApplicator;
import ru.medoedoed.utils.UserRole;

@Service
public class UserService extends ServiceImpl<User, UserDto> {
  private final UserDao userDao;
  private final OwnerDao ownerDao;
  private final OwnerApplicator ownerApplicator;

  public UserService(
      JpaRepository<User, Long> jpaRepository,
      DataApplicator<UserDto, User> applicator,
      OwnerDao ownerDao,
      OwnerApplicator ownerApplicator) {
    super(jpaRepository, applicator);
    this.userDao = (UserDao) jpaRepository;
    this.ownerDao = ownerDao;
    this.ownerApplicator = ownerApplicator;
  }

  public Long createOwner(OwnerDto ownerData) {
    var userData = getCurrentUser();
    if (userData.getOwnerId() != null) {
      throw new IllegalArgumentException("You already have owner");
    }
    return ownerDao.save(ownerApplicator.DataToJpa(ownerData)).getId();
  }


  public UserDto getCurrentUser() {
    var username = SecurityContextHolder.getContext().getAuthentication().getName();
    return getByUsername(username);
  }

  public UserDto getByUsername(String username) {
    return applicator.JpaToData(userDao.findByUsername(username)
       .orElseThrow(() -> new IllegalArgumentException("User with username " + username + " not found")));
  }

  @Deprecated
  public void getAdmin() {
    var user = getCurrentUser();
    user.setRole(UserRole.admin);
    save(user);
  }
}

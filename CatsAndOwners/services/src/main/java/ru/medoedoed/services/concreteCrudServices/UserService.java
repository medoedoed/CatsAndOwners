package ru.medoedoed.services.concreteCrudServices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.medoedoed.dao.UserDao;
import ru.medoedoed.models.User;
import ru.medoedoed.models.dataEntities.UserDto;
import ru.medoedoed.services.ServiceImpl;
import ru.medoedoed.services.dataApplicator.DataApplicator;
import ru.medoedoed.utils.UserRole;

@Service
public class UserService extends ServiceImpl<User, UserDto> {
  private final UserDao userDao;

  public UserService(
      JpaRepository<User, Long> jpaRepository,
      DataApplicator<UserDto, User> applicator) {
    super(jpaRepository, applicator);
    this.userDao = (UserDao) jpaRepository;
  }

  public void setOwner(Long ownerId) {
    var userData = getCurrentUser();
    if (userData.getOwnerId() != null) {
      throw new IllegalArgumentException("You already have owner");
    }
    
    userData.setOwnerId(ownerId);
    userDao.save(applicator.DataToJpa(userData));
  }

  public UserDto getCurrentUser() {
    var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var username = ((UserDto) principal).getUsername();
    return getByUsername(username);
  }

  public UserDto getByUsername(String username) {
    return applicator.JpaToData(userDao.findByUsername(username)
       .orElseThrow(() -> new IllegalArgumentException("User with username " + username + " not found")));
  }

  @Deprecated
  public void getAdmin() {
    var user = getCurrentUser();
    user.setRole(UserRole.ADMIN);
    save(user);
  }
}

package ru.medoedoed.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.medoedoed.crudService.DataApplicator;
import ru.medoedoed.crudService.ServiceImpl;
import ru.medoedoed.dao.UserDao;
import ru.medoedoed.jpaEntity.UserJpa;
import ru.medoedoed.models.dataModels.UserDto;
import ru.medoedoed.role.Role;

@Service
public class UserService extends ServiceImpl<UserJpa, UserDto> {
  private final UserDao userDao;

  public UserService(
      JpaRepository<UserJpa, Long> jpaRepository,
      DataApplicator<UserDto, UserJpa> applicator) {
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

  public void setAdmin(Long userId) {
    var user = getById(userId);
    user.setRole(Role.ADMIN_ROLE);
    save(user);
  }
}

package ru.medoedoed.utils;

import lombok.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.medoedoed.models.dataModels.UserDto;

import java.util.Objects;

@Component
public class AccessProvider {
  private @NonNull Boolean isAdmin() {
    return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ADMIN_ROLE"));
  }

  public void checkAccess(@NonNull Long userId) {
    var data = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var access = isAdmin() || (Objects.equals(data.getOwnerId(), userId));
    if (!access) {
      throw new AccessDeniedException("Access denied");
    }
  }

  public void checkAdmin() {
    if (!isAdmin()) {
      throw new AccessDeniedException("Access denied");
    }
  }
}

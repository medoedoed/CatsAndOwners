package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.dataEntities.OwnerDto;
import ru.medoedoed.services.concreteCrudServices.OwnerService;
import ru.medoedoed.services.concreteCrudServices.UserService;
import ru.medoedoed.utils.AccessProvider;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Valid
public class UserController {
  private final UserService userService;
  private final OwnerService ownerService;
  private final AccessProvider accessProvider;

  @PostMapping("/set-owner/{id}")
  public void setOwner(@Valid @PathVariable Long id) {
     userService.setOwner(id);
  }

  @GetMapping("/owner")
  public Long getOwner() {
    return userService.getCurrentUser().getOwnerId();
  }

  @PostMapping("/set-admin/{id}")
  public void setAdmin(@PathVariable Long id) {
    accessProvider.checkAdmin();
    userService.setAdmin(id);
  }

  @PutMapping("/owner")
  public void updateOwner(@Valid @RequestBody OwnerDto ownerData) {
    ownerData.setId(userService.getCurrentUser().getOwnerId());
    ownerService.save(ownerData);
  }

  @DeleteMapping("/owner/{ownerId}")
  public void deleteOwner(@Valid @PathVariable Long ownerId) {
    ownerService.delete(ownerId);
  }
}


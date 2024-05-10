package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.dataEntities.OwnerDto;
import ru.medoedoed.services.concreteCrudServices.OwnerService;
import ru.medoedoed.services.concreteCrudServices.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Valid
public class UserController {
  private final UserService userService;
  private final OwnerService ownerService;

  @PostMapping("/set-owner")
  public void setOwner(@Valid Long ownerId) {
     userService.setOwner(ownerId);
  }

  @GetMapping("/owner")
  public Long getOwner() {
    return userService.getCurrentUser().getOwnerId();
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


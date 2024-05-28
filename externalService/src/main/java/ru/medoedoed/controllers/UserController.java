package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.dataModels.OwnerDto;
import ru.medoedoed.rabbitmq.ExternalRabbitProducer;
import ru.medoedoed.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Valid
public class UserController {
  private final UserService userService;
  private final ExternalRabbitProducer producer;

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
    userService.setAdmin(id);
  }

  @PutMapping("/owner")
  public void updateOwner(@Valid @RequestBody OwnerDto ownerData) {
    ownerData.setId(userService.getCurrentUser().getOwnerId());
    producer.saveOwner(ownerData);
  }

  @DeleteMapping("/owner/{ownerId}")
  public void deleteOwner(@Valid @PathVariable Long ownerId) {
    producer.deleteOwner(ownerId);
  }
}


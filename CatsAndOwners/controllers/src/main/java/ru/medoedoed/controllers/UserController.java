package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.services.concreteCrudServices.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Valid
public class UserController {
  private final UserService userService;

  @PostMapping("/set-owner")
  public void setOwner(@Valid  Long ownerId) {
     userService.setOwner(ownerId);
  }
}


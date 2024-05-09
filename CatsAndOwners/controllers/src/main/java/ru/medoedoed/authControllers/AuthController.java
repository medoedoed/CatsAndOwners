package ru.medoedoed.authControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.authEntities.SignInRequest;
import ru.medoedoed.models.authEntities.SignUpRequest;
import ru.medoedoed.services.authServices.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {
  private final AuthenticationService authenticationService;

  @Operation
  @PostMapping("/sign-up")
  public String signUp(@RequestBody @Valid SignUpRequest request) {
    return authenticationService.signUp(request);
  }

  @Operation
  @PostMapping("/sign-in")
  public String signIn(@RequestBody @Valid SignInRequest request) {
    return authenticationService.signIn(request);
  }
}

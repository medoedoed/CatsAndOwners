package ru.medoedoed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.medoedoed.models.authEntities.SignUpRequest;
import ru.medoedoed.services.authServices.AuthenticationService;

@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    var application = SpringApplication.run(Application.class, args);
    var authenticationService = application.getBean(AuthenticationService.class);
    authenticationService.signUpAdmin(
        SignUpRequest.builder().username("admin").password("admin1").build());
  }
}

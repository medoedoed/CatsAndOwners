package ru.medoedoed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {
  public static void main(String[] args) {
    var app = SpringApplication.run(AuthServiceApplication.class, args);
  }
}

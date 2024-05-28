package ru.medoedoed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExternalServiceApplication {
  public static void main(String[] args) {
    var app = SpringApplication.run(ExternalServiceApplication.class, args);
  }
}

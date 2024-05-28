package ru.medoedoed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatServiceApplication {
  public static void main(String[] args) {
    var app = SpringApplication.run(CatServiceApplication.class, args);
  }
}

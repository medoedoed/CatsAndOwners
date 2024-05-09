package ru.medoedoed.utils;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class Response {
  public static ResponseEntity<HashMap<String, Object>> createResponse(String key, Object value) {
    return ResponseEntity.ok(new HashMap<>() {
      {
        put(key, value);
      }
    });
  }
}

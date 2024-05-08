package ru.medoedoed.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.DataEntities.CatColorDto;
import ru.medoedoed.services.concreteServices.ColorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/colors")
@Tag(name = "Аутентификация")
public class ColorController {
  private final ColorService colorService;

  @GetMapping("/{id}")
  @Operation(summary = "Доступен только авторизованным пользователям")
  public CatColorDto getColor(@PathVariable @NonNull Long id) {
    return colorService.getById(id);
  }

  @GetMapping()
  @Operation(summary = "Доступен только авторизованным пользователям")
  public List<CatColorDto> getAll() {
    return colorService.getAll();
  }

  @PostMapping()
  @Operation(summary = "Доступен только авторизованным пользователям")
  public long newColor(@RequestBody @Valid @NonNull CatColorDto colorData) {
    return colorService.save(colorData);
  }

  @PutMapping
  @Operation(summary = "Доступен только авторизованным пользователям")
  public void updateColor(@Valid @NonNull @RequestBody CatColorDto colorData) {
    colorService.update(colorData);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Доступен только авторизованным пользователям")
  public void deleteColor(@NonNull @PathVariable Long id) {
    colorService.delete(id);
  }
}

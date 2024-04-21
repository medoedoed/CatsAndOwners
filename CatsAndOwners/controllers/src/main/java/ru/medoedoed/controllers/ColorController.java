package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.CatColorDto;
import ru.medoedoed.services.concreteServices.ColorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/colors")
public class ColorController {
  private final ColorService colorService;

  @GetMapping("/{id}")
  public CatColorDto getColor(@PathVariable @NonNull Long id) {
    return colorService.getById(id);
  }

  @GetMapping()
  public Iterable<CatColorDto> getAll() {
    return colorService.getAll();
  }

  @PostMapping
  public long newColor(@RequestBody @Valid @NonNull CatColorDto colorData) {
    return colorService.save(colorData);
  }

  @PutMapping
  public void updateColor(@Valid @NonNull @RequestBody CatColorDto colorData) {
    colorService.update(colorData);
  }

  @DeleteMapping("/{id}")
  public void deleteColor(@NonNull @PathVariable Long id) {
    colorService.delete(id);
  }
}

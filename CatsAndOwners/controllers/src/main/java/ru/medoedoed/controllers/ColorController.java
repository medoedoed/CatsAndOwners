package ru.medoedoed.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.dataEntities.CatColorDto;
import ru.medoedoed.services.concreteCrudServices.ColorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/colors")
public class ColorController {
  private final ColorService colorService;

  @GetMapping("/{id}")
  @Operation
  public CatColorDto getColor(@PathVariable @NonNull Long id) {
    return colorService.getById(id);
  }

  @GetMapping()
  @Operation
  public List<CatColorDto> getAll() {
    return colorService.getAll();
  }

  @PostMapping()
  @Operation
  public long newColor(@RequestBody @Valid @NonNull CatColorDto colorData) {
    return colorService.save(colorData);
  }

  @PutMapping
  @Operation
  public void updateColor(@Valid @NonNull @RequestBody CatColorDto colorData) {
    colorService.update(colorData);
  }

  @DeleteMapping("/{id}")
  @Operation
  public void deleteColor(@NonNull @PathVariable Long id) {
    colorService.delete(id);
  }
}

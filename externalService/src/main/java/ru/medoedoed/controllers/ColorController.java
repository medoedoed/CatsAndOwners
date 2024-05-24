package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.dataModels.CatColorDto;
import ru.medoedoed.services.ColorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/colors")
public class ColorController {
  @GetMapping("/{id}")
  public CatColorDto getColor(@PathVariable @NonNull Long id) {
    return colorService.getById(id);
  }

  @GetMapping()
  public List<CatColorDto> getAll() {
    return colorService.getAll();
  }

  @PostMapping()
  public long newColor(@RequestBody @Valid @NonNull CatColorDto colorData) {
//    accessProvider.checkAdmin(); TODO
    return colorService.save(colorData);
  }

  @PutMapping()
  public void updateColor(@Valid @NonNull @RequestBody CatColorDto colorData) {
//    accessProvider.checkAdmin(); TODO
    colorService.update(colorData);
  }

  @DeleteMapping("/{id}")
  public void deleteColor(@NonNull @PathVariable Long id) {
//    accessProvider.checkAdmin(); TODO
    colorService.delete(id);
  }
}

package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.dataModels.CatColorDto;
import ru.medoedoed.rabbitmq.ExternalRabbitProducer;
import ru.medoedoed.utils.AccessProvider;

@RestController
@RequiredArgsConstructor
@RequestMapping("/colors")
public class ColorController {
  private final ExternalRabbitProducer producer;
  private final AccessProvider accessProvider;

  @GetMapping("/{id}")
  public CatColorDto getColor(@PathVariable @NonNull Long id) {
    return producer.getColorById(id);
  }

  @GetMapping("/all")
  public List<CatColorDto> getAll() {
    return producer.getAllColors();
  }

  @PostMapping()
  public long newColor(@RequestBody @Valid @NonNull CatColorDto colorData) {
//    accessProvider.checkAdmin();
    return producer.saveColor(colorData);
  }

  @PutMapping()
  public void updateColor(@Valid @NonNull @RequestBody CatColorDto colorData) {
    accessProvider.checkAdmin();
    producer.updateColor(colorData);
  }

  @DeleteMapping("/{id}")
  public void deleteColor(@NonNull @PathVariable Long id) {
    accessProvider.checkAdmin();
    producer.deleteColorById(id);
  }
}

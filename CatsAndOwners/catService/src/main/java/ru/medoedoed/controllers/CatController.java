package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.dataModels.CatDto;
import ru.medoedoed.services.CatService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
@Valid
public class CatController {
  private final CatService catService;
  private final RabbitTemplate rabbitTemplate;

  // TODO добавить проверку владельца
  @GetMapping("/{id}")
  public CatDto getCat(@PathVariable Long id) {
    var catData = catService.getById(id);
    if (catData == null) {
      throw new IllegalArgumentException("Cat not found with id: " + id);
    }
    return catData;
  }

  @GetMapping("/all")
  public List<CatDto> getAll() {
    return catService.getAll();
  }

  @PostMapping
  public Long newCat(@RequestBody @Valid CatDto catData) {
    return catService.save(catData);
  }

  @PutMapping
  public void updateCat(@Valid @NonNull @RequestBody CatDto catData) {
    catService.update(catData);
  }

  @DeleteMapping("/{id}")
  public void deleteCat(@NonNull @PathVariable Long id) {
    catService.delete(id);
  }
}

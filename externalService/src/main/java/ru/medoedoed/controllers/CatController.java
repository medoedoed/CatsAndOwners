package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Objects;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.dataModels.CatDto;
import ru.medoedoed.rabbitmq.ExternalRabbitProducer;
import ru.medoedoed.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
@Valid
public class CatController {
  private final ExternalRabbitProducer producer;
  private final UserService userService;

  @GetMapping("/{id}")
  public CatDto getCat(@PathVariable Long id) {
    checkCatById(id);
    return producer.getCatById(id);
  }

  @GetMapping("/all")
  public List<CatDto> getAll() {
    Long currentUserId = userService.getCurrentUser().getId();
    var cats = producer.getAllCats();
    cats.removeIf(cat -> !Objects.equals(cat.getOwnerId(), currentUserId));
    return cats;
  }

  @PostMapping
  public Long newCat(@RequestBody @Valid CatDto catData) {
    catData.setOwnerId(userService.getCurrentUser().getId());
    return producer.saveCat(catData);
  }

  @PutMapping
  public void updateCat(@Valid @NonNull @RequestBody CatDto catData) {
    checkCatById(catData.getId());
    producer.updateCat(catData);
  }

  @DeleteMapping("/{id}")
  public void deleteCat(@NonNull @PathVariable Long id) {
    checkCatById(id);
    producer.deleteCatById(id);
  }

  private void checkCatById(Long catId) {
    Long currentUserId = userService.getCurrentUser().getId();
    var checkData = producer.getCatById(catId);

    if (checkData == null) {
      throw new IllegalArgumentException("Cat not found with id: " + catId);
    }

    if (checkData.getOwnerId().equals(currentUserId)) {
      throw new IllegalArgumentException("You do not own this cat");
    }
  }
}

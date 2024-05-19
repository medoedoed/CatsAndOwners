package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Objects;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.dataModels.CatDto;
import ru.medoedoed.rabbitmq.CatRabbitProducer;
import ru.medoedoed.services.CatService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
@Valid
public class CatController {
  private final CatService catService;
  private final CatRabbitProducer producer;

  @GetMapping("/{id}")
  public CatDto getCat(@PathVariable Long id) {
    checkCatById(id);
    return catService.getById(id);
  }

  @GetMapping("/all")
  public List<CatDto> getAll() {
    Long currentUserId = producer.getCurrentUser();
    var cats = catService.getAll();
    cats.removeIf(cat -> !Objects.equals(cat.getOwnerId(), currentUserId));
    return cats;
  }

  @PostMapping
  public Long newCat(@RequestBody @Valid CatDto catData) {
    catData.setOwnerId(producer.getCurrentUser());
    return catService.save(catData);
  }

  @PutMapping
  public void updateCat(@Valid @NonNull @RequestBody CatDto catData) {
    checkCatById(catData.getId());
    catService.update(catData);
  }

  @DeleteMapping("/{id}")
  public void deleteCat(@NonNull @PathVariable Long id) {
    checkCatById(id);
    catService.delete(id);
  }

  private void checkCatById(Long catId) {
    Long currentUserId = producer.getCurrentUser();
    var checkData = catService.getById(catId);

    if (checkData == null) {
      throw new IllegalArgumentException("Cat not found with id: " + catId);
    }

    if (checkData.getOwnerId().equals(currentUserId)) {
      throw new IllegalArgumentException("You do not own this cat");
    }
  }
}

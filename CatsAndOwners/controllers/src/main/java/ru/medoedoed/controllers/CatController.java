package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.dataEntities.CatDto;
import ru.medoedoed.services.concreteCrudServices.CatService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
@Valid
public class CatController {
  private final CatService catService;

  @GetMapping("/{id}")
  public CatDto getCat(@PathVariable @NonNull Long id) {
    return catService.getById(id);
  }

  @GetMapping()
  public List<CatDto> getAll() {
    return catService.getAll();
  }

  @PostMapping
  public Long newCat(@RequestBody @Valid @NonNull CatDto catData) {
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

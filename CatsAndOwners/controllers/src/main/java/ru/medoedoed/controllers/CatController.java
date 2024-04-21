package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.CatDto;
import ru.medoedoed.services.concreteServices.CatService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
@Valid
public class CatController {
  private final CatService catService;

  @GetMapping("/{id}")
  public CatDto getCat(@PathVariable Long id) {
    return catService.getById(id);
  }

  @GetMapping()
  public Iterable<CatDto> getAll() {
    return catService.getAll();
  }

  @PostMapping
  public long newCat(@RequestBody CatDto catData) {
    return catService.save(catData);
  }

  @PutMapping
  public void UpdateCat(@RequestBody CatDto catData) {
    catService.update(catData);
  }

  @DeleteMapping("/{id}")
  public void DeleteCat(@PathVariable Long id) {
    catService.delete(id);
  }
}

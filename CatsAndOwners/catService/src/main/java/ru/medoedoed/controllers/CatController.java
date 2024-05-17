package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.dataEntities.CatDto;
import ru.medoedoed.services.concreteCrudServices.CatService;
import ru.medoedoed.utils.AccessProvider;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
@Valid
public class CatController {
  private final CatService catService;
  private final AccessProvider accessProvider;

  @GetMapping("/{id}")
  public CatDto getCat(@PathVariable Long id) {
    var catData = catService.getById(id);
    if (catData == null) {
      throw new IllegalArgumentException("Cat not found with id: " + id);
    }
    accessProvider.checkAccess(catData.getOwnerId());
    return catData;
  }

  @GetMapping("/all")
  public List<CatDto> getAll() {
    return catService.getAll();
  }

  @PostMapping
  public Long newCat(@RequestBody @Valid CatDto catData) {
    accessProvider.checkAccess(catData.getOwnerId());
    return catService.save(catData);
  }

  @PutMapping
  public void updateCat(@Valid @NonNull @RequestBody CatDto catData) {
    accessProvider.checkAccess(catData.getOwnerId());
    catService.update(catData);
  }

  @DeleteMapping("/{id}")
  public void deleteCat(@NonNull @PathVariable Long id) {
    accessProvider.checkAccess(id);
    catService.delete(id);
  }
}

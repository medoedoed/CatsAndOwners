package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.dataEntities.CatDto;
import ru.medoedoed.services.concreteCrudServices.CatService;
import ru.medoedoed.utils.AccessProvider;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
@Valid
public class CatController {
  private final CatService catService;
  private final AccessProvider accessProvider;

  @GetMapping("/{id}")
  public CatDto getCat(@PathVariable @NonNull Long id) {
    var catData = catService.getById(id);
    accessProvider.checkAccess(catData.getOwnerId());
    return catData;
  }

  @GetMapping()
  public List<CatDto> getAll() {
    return catService.getAll();
  }

  @PostMapping
  public Long newCat(@RequestBody @Valid @NonNull CatDto catData) {
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
    var ownerId = catService.getById(id).getOwnerId();
    accessProvider.checkAccess(ownerId);
    catService.delete(id);
  }
}

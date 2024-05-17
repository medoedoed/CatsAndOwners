package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.dataModels.OwnerDto;
import ru.medoedoed.services.OwnerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {
  private final OwnerService ownerService;

  @GetMapping("/{id}")
  public OwnerDto getOwner(@PathVariable @NonNull Long id) {
//    accessProvider.checkAdmin(); TODO
    return ownerService.getById(id);
  }

  @GetMapping()
  public Iterable<OwnerDto> getAll() {
//    accessProvider.checkAdmin(); TODO
    return ownerService.getAll();
  }

  @PostMapping
  public long newOwner(@RequestBody @Valid @NonNull OwnerDto ownerData) {
    return ownerService.save(ownerData);
  }

  @PutMapping
  public void updateOwner(@Valid @NonNull @RequestBody OwnerDto ownerData) {
//    accessProvider.checkAdmin(); TODO
    ownerService.update(ownerData);
  }

  @DeleteMapping("/{id}")
  public void deleteOwner(@NonNull @PathVariable Long id) {
//    accessProvider.checkAdmin(); TODO
    ownerService.delete(id);
  }
}

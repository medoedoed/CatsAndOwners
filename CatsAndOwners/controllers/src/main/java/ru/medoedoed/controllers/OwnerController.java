package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.DataEntities.OwnerDto;
import ru.medoedoed.services.concreteServices.OwnerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {
  private final OwnerService ownerService;

  @GetMapping("/{id}")
  public OwnerDto getOwner(@PathVariable @NonNull Long id) {
    return ownerService.getById(id);
  }

  @GetMapping()
  public Iterable<OwnerDto> getAll() {
    return ownerService.getAll();
  }

  @PostMapping
  public long newOwner(@RequestBody @Valid @NonNull OwnerDto ownerData) {
    return ownerService.save(ownerData);
  }

  @PutMapping
  public void updateOwner(@Valid @NonNull @RequestBody OwnerDto ownerData) {
    ownerService.update(ownerData);
  }

  @DeleteMapping("/{id}")
  public void deleteOwner(@NonNull @PathVariable Long id) {
    ownerService.delete(id);
  }
}

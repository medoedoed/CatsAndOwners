package ru.medoedoed.controllers;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.dataModels.OwnerDto;
import ru.medoedoed.rabbitmq.ExternalRabbitProducer;
import ru.medoedoed.utils.AccessProvider;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {
  private final ExternalRabbitProducer producer;
  private final AccessProvider accessProvider;

  @GetMapping("/{id}")
  public OwnerDto getOwner(@PathVariable @NonNull Long id) {
//    accessProvider.checkAdmin();
    return producer.getOwnerById(id);
  }

  @GetMapping()
  public Iterable<OwnerDto> getAll() {
//    accessProvider.checkAdmin();
    return producer.getAllUsers();
  }

  @PostMapping
  public Long newOwner(@RequestBody @Valid @NonNull OwnerDto ownerData) {
    return producer.saveOwner(ownerData);
  }

  @PutMapping
  public void updateOwner(@Valid @NonNull @RequestBody OwnerDto ownerData) {
    accessProvider.checkAdmin();
    producer.updateOwner(ownerData);
  }

  @DeleteMapping("/{id}")
  public void deleteOwner(@NonNull @PathVariable Long id) {
    accessProvider.checkAdmin();
    producer.deleteOwner(id);
  }
}

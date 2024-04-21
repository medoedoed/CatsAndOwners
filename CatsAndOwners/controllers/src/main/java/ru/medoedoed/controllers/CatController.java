package ru.medoedoed.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.medoedoed.models.CatDto;
import ru.medoedoed.services.concreteServices.CatService;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
public class CatController {
  private final CatService catService;

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public String return404(NoSuchElementException ex) {
    return ex.getMessage();
  }

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

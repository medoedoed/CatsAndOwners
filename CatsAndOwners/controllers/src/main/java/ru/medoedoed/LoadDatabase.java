package ru.medoedoed;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.medoedoed.models.CatColorDto;
import ru.medoedoed.models.CatDto;
import ru.medoedoed.models.OwnerDto;
import ru.medoedoed.services.concreteServices.CatService;
import ru.medoedoed.services.concreteServices.ColorService;
import ru.medoedoed.services.concreteServices.OwnerService;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(
      CatService catService, ColorService colorService, OwnerService ownerService) {
    var data =
        CatDto.builder()
            .id(0)
            .name("catName")
            .birthDate(LocalDate.of(1, 1, 1))
            .breed("breed")
            .colorId(1)
            .ownerId(1)
            .build();
    return args -> {
      log.info(
          "Preloading {}",
          colorService.save(CatColorDto.builder().id(0).colorName("color1").build()));
      log.info("Preloading {}", colorService.getAll());
      log.info(
          "Preloading {}",
          ownerService.save(
              OwnerDto.builder().id(0).birthDate(LocalDate.of(1, 1, 1)).name("name").build()));
      log.info("Preloading {}", catService.save(data));
    };
  }
}

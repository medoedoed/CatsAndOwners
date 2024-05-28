package ru.medoedoed.rabbitmq;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import ru.medoedoed.models.dataModels.CatColorDto;
import ru.medoedoed.models.dataModels.CatDto;
import ru.medoedoed.models.dataModels.OwnerDto;

@Service
@RequiredArgsConstructor
public class ExternalRabbitProducer {
  private final RabbitTemplate rabbitTemplate;

  public List<CatDto> getAllCats() {
    return (List<CatDto>) rabbitTemplate.convertSendAndReceive("cats.getAll", 1);
  }

  public CatDto getCatById(Long catId) {
    return (CatDto) rabbitTemplate.convertSendAndReceive("cats.getById", catId);
  }

  public Long saveCat(CatDto catData) {
    return (Long) rabbitTemplate.convertSendAndReceive("cats.save", catData);
  }

  public void updateCat(CatDto catData) {
    rabbitTemplate.convertAndSend("cats.update", catData);
  }

  public void deleteCatById(Long id) {
    rabbitTemplate.convertAndSend("cats.delete", id);
  }

  public CatColorDto getColorById(Long id) {
    return (CatColorDto) rabbitTemplate.convertSendAndReceive("colors.getById", id);
  }

  public List<CatColorDto> getAllColors() {
    return (List<CatColorDto>) rabbitTemplate.convertSendAndReceive("colors.getAll", 1);
  }

  public Long saveColor(CatColorDto colorData) {
    return (Long) rabbitTemplate.convertSendAndReceive("colors.save", colorData);
  }

  public void updateColor(CatColorDto colorData) {
    rabbitTemplate.convertAndSend("colors.update", colorData);
  }

  public void deleteColorById(Long id) {
    rabbitTemplate.convertAndSend("colors.delete", id);
  }

  public OwnerDto getOwnerById(Long id) {
    return (OwnerDto) rabbitTemplate.convertSendAndReceive("owners.getById", id);
  }

  public List<OwnerDto> getAllUsers() {
    return (List<OwnerDto>) rabbitTemplate.convertSendAndReceive("owners.getAll", 1);
  }

  public Long saveOwner(OwnerDto ownerData) {
    return (Long) rabbitTemplate.convertSendAndReceive("owners.save", ownerData);
  }

  public void updateOwner(OwnerDto ownerData) {
    rabbitTemplate.convertAndSend("owners.update", ownerData);
  }

  public void deleteOwner(Long id) {
    rabbitTemplate.convertAndSend("owners.delete", id);
  }
}

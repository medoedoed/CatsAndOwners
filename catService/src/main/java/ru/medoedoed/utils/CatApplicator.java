package ru.medoedoed.utils;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.medoedoed.applicators.DataApplicator;
import ru.medoedoed.dao.CatDao;
import ru.medoedoed.dao.ColorDao;
import ru.medoedoed.dao.OwnerDao;
import ru.medoedoed.jpa.CatJpa;
import ru.medoedoed.models.dataModels.CatDto;

@Component
@RequiredArgsConstructor
public class CatApplicator implements DataApplicator<CatDto, CatJpa> {
  private final ColorDao colorDao;
  private final OwnerDao ownerDao;
  private final CatDao catDao;

  @Override
  public CatJpa DataToJpa(@NotNull CatDto data) {
    var cat = new CatJpa();
    cat.setId(data.getId());
    cat.setName(data.getName());
    cat.setBirthDate(data.getBirthDate());
    cat.setBreed(data.getBreed());
    cat.setColor(
            colorDao
                    .findById(data.getColorId())
                    .orElseThrow(
                            () ->
                                    new EntityNotFoundException(
                                            "Color with id " + data.getColorId() + " not found")));
    cat.setOwner(
            ownerDao
                    .findById(data.getOwnerId())
                    .orElseThrow(
                            () ->
                                    new EntityNotFoundException(
                                            "Owner with id " + data.getOwnerId() + " not found")));
    if (data.getFriendsId() != null) {
      cat.setFriends(
              data.getFriendsId().stream()
                      .map(friendId -> catDao.findById(friendId).orElseThrow())
                      .toList());
    }

    return cat;
  }

  @Override
  public CatDto JpaToData(@NotNull CatJpa jpa) {
    return CatDto.builder()
            .id(jpa.getId())
            .name(jpa.getName())
            .birthDate(jpa.getBirthDate())
            .breed(jpa.getBreed())
            .colorId(jpa.getColor().getId())
            .ownerId(jpa.getOwner().getId())
            .friendsId(jpa.getFriends().stream().map(CatJpa::getId).toList())
            .build();
  }
}


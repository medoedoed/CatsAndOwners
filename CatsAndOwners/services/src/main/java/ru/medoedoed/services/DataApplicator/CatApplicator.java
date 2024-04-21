package ru.medoedoed.services.DataApplicator;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import ru.medoedoed.dao.CatDao;
import ru.medoedoed.dao.ColorDao;
import ru.medoedoed.dao.OwnerDao;
import ru.medoedoed.models.Cat;
import ru.medoedoed.models.CatDto;

@RequiredArgsConstructor
public class CatApplicator implements DataApplicator<CatDto, Cat> {
  private final CatDao catDao;
  private final ColorDao colorDao;
  private final OwnerDao ownerDao;

  @Override
  public Cat DataToJpa(@NotNull CatDto data) {
    var cat = new Cat();
    cat.setId(data.getId());
    cat.setName(data.getName());
    cat.setBirthDate(data.getBirthDate());
    cat.setBreed(data.getBreed());
    cat.setColor(colorDao.findById(data.getColorId()).orElseThrow());
    cat.setOwner(ownerDao.findById(data.getOwnerId()).orElseThrow());
    cat.setFriends(
        data.getFriendsId().stream()
            .map(friendId -> catDao.findById(friendId).orElseThrow())
            .toList());
    return cat;
  }

  @Override
  public CatDto JpaToData(@NotNull Cat jpa) {
    return CatDto.builder()
        .id(jpa.getId())
        .name(jpa.getName())
        .birthDate(jpa.getBirthDate())
        .breed(jpa.getBreed())
        .colorId(jpa.getColor().getId())
        .ownerId(jpa.getOwner().getId())
        .friendsId(jpa.getFriends().stream().map(Cat::getId).toList())
        .build();
  }
}
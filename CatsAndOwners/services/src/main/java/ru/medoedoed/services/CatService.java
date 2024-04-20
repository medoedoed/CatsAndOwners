package ru.medoedoed.services;

import java.util.List;
import ru.medoedoed.dao.CatDao;
import ru.medoedoed.dao.ColorDao;
import ru.medoedoed.dao.OwnerDao;
import ru.medoedoed.models.Cat;
import ru.medoedoed.models.CatDto;

public class CatService implements Service<CatDto> {
  private final CatDao catDao;
  private final ColorDao colorDao;
  private final OwnerDao ownerDao;

  public CatService(CatDao catDao, ColorDao colorDao, OwnerDao ownerDao) {
    this.catDao = catDao;
    this.colorDao = colorDao;
    this.ownerDao = ownerDao;
  }

  @Override
  public Long save(CatDto entity) {
    Cat cat = new Cat();
    applyCatData(cat, entity);
    for (var friend : cat.getFriends()) {
      friend.getFriends().add(cat);
    }
    cat.getOwner().getCats().add(cat);

    catDao.save(cat);
    return cat.getId();
  }

  @Override
  public CatDto getById(Long id) {
    Cat cat = catDao.findById(id).orElseThrow();
    return CatDto.builder()
        .id(cat.getId())
        .name(cat.getName())
        .breed(cat.getBreed())
        .birthDate(cat.getBirthDate())
        .colorId(cat.getColor().getId())
        .ownerId(cat.getOwner().getId())
        .friendsId(cat.getFriends().stream().map(Cat::getId).toList())
        .build();
  }

  @Override
  public void update(CatDto entity) {
    var cat = catDao.findById(entity.getId()).orElseThrow();
    for (var friend : cat.getFriends()) {
      friend.getFriends().remove(cat);
    }
    cat.getFriends().clear();

    for (var friendId : entity.getFriendsId()) {
      var friend = catDao.findById(friendId).orElseThrow();
      cat.getFriends().add(friend);
      friend.getFriends().add(cat);
    }

    var owner = cat.getOwner();
    owner.getCats().remove(cat);
    owner.getCats().add(catDao.findById(entity.getId()).orElseThrow());

    applyCatData(cat, entity);
  }

  @Override
  public void delete(Long id) {
    for (var cat : catDao.findAll()) {
      cat.getFriends().removeIf(friend -> friend.getId() == id);
    }

    var cat = catDao.findById(id).orElseThrow();
    cat.getOwner().getCats().remove(cat);

    catDao.deleteById(id);
  }

  @Override
  public List<CatDto> getAll() {
    return catDao.findAll().stream()
        .map(
            cat ->
                CatDto.builder()
                    .id(cat.getId())
                    .name(cat.getName())
                    .birthDate(cat.getBirthDate())
                    .colorId(cat.getColor().getId())
                    .breed(cat.getBreed())
                    .friendsId(cat.getFriends().stream().map(Cat::getId).toList())
                    .ownerId(cat.getOwner().getId())
                    .build())
        .toList();
  }

  private void applyCatData(Cat cat, CatDto data) {
    cat.setName(data.getName());
    cat.setBreed(data.getBreed());
    cat.setBirthDate(data.getBirthDate());
    cat.setColor(colorDao.findById(data.getColorId()).orElseThrow());
    cat.setOwner(ownerDao.findById(data.getOwnerId()).orElseThrow());
    cat.setFriends(
        data.getFriendsId().stream()
            .map(id -> catDao.findById(data.getId()).orElseThrow())
            .toList());
    cat.setOwner(ownerDao.findById(data.getOwnerId()).orElseThrow());
  }
}

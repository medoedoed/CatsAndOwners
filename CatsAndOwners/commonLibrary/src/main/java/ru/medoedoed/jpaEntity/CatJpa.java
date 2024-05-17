package ru.medoedoed.jpaEntity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Cats")
@NoArgsConstructor
public class CatJpa implements JpaEntity {
  @Id
  @Column(name = "CatID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "CatName")
  private String name;

  @Column(name = "CatBirthDate")
  private LocalDate birthDate;

  @Column(name = "CatBreed")
  private String breed;

  @ManyToOne
  private CatColorJpa color;

  @ManyToOne
  @JoinColumn(name = "OwnerID")
  private OwnerJpa ownerJpa;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "CatFriends",
      joinColumns = @JoinColumn(name = "FirstCatId"),
      inverseJoinColumns = @JoinColumn(name = "SecondCatId"))
  @Column(name = "Friends")
  private List<CatJpa> friends;

  @Override
  public long getId() {
    return id;
  }
}

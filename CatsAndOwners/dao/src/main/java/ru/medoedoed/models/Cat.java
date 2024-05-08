package ru.medoedoed.models;

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
public class Cat implements JpaEntity {
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
  private CatColor color;

  @ManyToOne
  @JoinColumn(name = "OwnerID")
  private Owner owner;

  @ManyToMany
  @JoinTable(
      name = "CatFriends",
      joinColumns = @JoinColumn(name = "FirstCatId"),
      inverseJoinColumns = @JoinColumn(name = "SecondCatId"))
  @Column(name = "Friends")
  private List<Cat> friends;

  @Override
  public long getId() {
    return id;
  }
}

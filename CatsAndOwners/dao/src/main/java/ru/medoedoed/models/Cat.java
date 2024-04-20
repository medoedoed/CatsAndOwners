package ru.medoedoed.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Cats")
@NoArgsConstructor
public class Cat {
  @Id
  @Column(name = "CatID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "CatName")
  private String name;

  @Column(name = "CatBirthDate")
  private LocalDate birthDate;

  @Column(name = "CatBreed")
  private String breed;

  @Column(name = "CatColor")
  @ManyToOne
  private CatColor color;

  @Column(name = "Owner")
  @ManyToOne
  private Owner owner;

  @ManyToMany
  @JoinTable(
      name = "CatFriends",
      joinColumns = @JoinColumn(name = "FirstCatId"),
      inverseJoinColumns = @JoinColumn(name = "SecondCatId"))
  @Column(name = "Friends")
  private List<Cat> friends;
}

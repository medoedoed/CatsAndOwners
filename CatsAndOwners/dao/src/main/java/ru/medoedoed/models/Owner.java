package ru.medoedoed.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Owners")
@NoArgsConstructor
public class Owner {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "OwnerID")
  private long id;

  @Column(name = "OwnerName")
  private String name;

  @Column(name = "OwnerBirthDate")
  private LocalDate birthDate;

  @Setter
  @Column(name = "Cats")
  @OneToMany(mappedBy = "Owner")
  private List<Cat> cats;
}

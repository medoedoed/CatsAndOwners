package ru.medoedoed.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Owners")
@NoArgsConstructor
public class Owner implements JpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "OwnerID")
  private Long id;

  @Column(name = "OwnerName")
  private String name;

  @Column(name = "OwnerBirthDate")
  private LocalDate birthDate;

  @Setter
  @Column(name = "Cats")
  @OneToMany()
  private List<Cat> cats;

  @Override
  public long getId() {
    return id;
  }
}

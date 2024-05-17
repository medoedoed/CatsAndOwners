package ru.medoedoed.jpaEntity;

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
public class OwnerJpa implements JpaEntity {
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
  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CatJpa> catJpas;

  @Override
  public long getId() {
    return id;
  }
}

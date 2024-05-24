package ru.medoedoed.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.medoedoed.jpaEntity.JpaEntity;

@Entity
@Getter
@Setter
@Table(name = "CatColors")
@NoArgsConstructor
public class CatColorJpa implements JpaEntity {
  @Id
  @Column(name = "ColorID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "ColorName")
  private String colorName;

  @Override
  public long getId() {
    return id;
  }
}

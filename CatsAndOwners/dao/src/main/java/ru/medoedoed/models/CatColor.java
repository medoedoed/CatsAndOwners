package ru.medoedoed.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "CatColors")
@NoArgsConstructor
public class CatColor implements JpaEntity  {
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

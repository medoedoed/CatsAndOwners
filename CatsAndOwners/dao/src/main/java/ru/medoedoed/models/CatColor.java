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
public class CatColor {
  @Id
  @Column(name = "ColorID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "ColorName")
  private String colorName;
}

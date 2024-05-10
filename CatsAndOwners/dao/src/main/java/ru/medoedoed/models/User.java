package ru.medoedoed.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.medoedoed.utils.Role;

@Entity
@Setter
@Getter
@Table(name = "Users")
@NoArgsConstructor
public class User implements JpaEntity {
  @Id
  @Column(name = "UserId")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private Owner owner;

  @Column(name = "Username", unique = true)
  private String username;

  @Column(name = "Password")
  private String password;

  @Column(name = "Role")
  @Enumerated(EnumType.STRING)
  private Role role;

  @Override
  public long getId() {
    return id;
  }
}

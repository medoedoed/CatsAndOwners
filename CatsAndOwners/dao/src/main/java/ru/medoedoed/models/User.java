package ru.medoedoed.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.medoedoed.utils.UserRole;

import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "Users")
@NoArgsConstructor
public class User implements JpaEntity, UserDetails {
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
  private UserRole role;

  @Override
  public long getId() {
    return id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

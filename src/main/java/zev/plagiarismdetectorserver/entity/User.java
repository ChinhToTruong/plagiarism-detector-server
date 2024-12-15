package zev.plagiarismdetectorserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import zev.plagiarismdetectorserver.entity.enums.Gender;
import zev.plagiarismdetectorserver.entity.enums.Role;

@Getter
@Setter
@Entity(name = "User")
@Table(name = "java_user_001")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends AbstractEntity implements Serializable, UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private String id;

  @Column(name = "email", length = 50, nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "role")
  private Role role;

  @Column(name = "first_name", length = 50)
  private String firstName;

  @Column(name = "last_name", length = 50)
  private String lastName;

  @Column(name = "gender")
  private Gender gender;

  @Column(name = "phone", length = 15, unique = true)
  private String phone;

  @Column(name = "address", length = 50)
  private String address;

  @Column(name = "avatar_url", length = 50)
  private String avatarUrl;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;

  @Column(name = "is_active", nullable = false)
  private boolean isActive;

  @Column(name = "is_identify", nullable = false)
  private boolean isIdentify;

  @ManyToMany
  @JoinTable(
      name = "java_user_classroom_001",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "classroom_id")
  )
  private List<ClassRoom> classRooms;

  @OneToMany(targetEntity = Document.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Column(name = "document_id", nullable = false)
  private List<Document> documents;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
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

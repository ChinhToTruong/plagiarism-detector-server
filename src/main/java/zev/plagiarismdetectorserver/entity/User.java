package zev.plagiarismdetectorserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zev.plagiarismdetectorserver.entity.enums.Role;

import java.time.LocalDate;

@Data
@Entity(name = "user")
@Table(name = "java_user_01")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String password;
    private Role role;
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Profile.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;
}

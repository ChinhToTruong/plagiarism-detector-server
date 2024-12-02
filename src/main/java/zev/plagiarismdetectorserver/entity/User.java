package zev.plagiarismdetectorserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import zev.plagiarismdetectorserver.entity.enums.Gender;
import zev.plagiarismdetectorserver.entity.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name = "User")
@Table(name = "java_user_001")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 10, nullable = false)
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

    @OneToMany(targetEntity = Document.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "document_id", nullable = false)
    private List<Document> documents;
}

package zev.plagiarismdetectorserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import zev.plagiarismdetectorserver.entity.enums.Gender;
import zev.plagiarismdetectorserver.entity.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name = "user")
@Table(name = "java_user_001")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String password;
    private Role role;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phone;
    private String address;
    private String avatarUrl;
    private LocalDate dateOfBirth;
    private boolean isActive;
    private boolean isIdentify;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @ManyToMany
    @JoinTable(
            name = "java_user_classroom_001",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id")
    )
    private List<ClassRoom> classRooms;
}

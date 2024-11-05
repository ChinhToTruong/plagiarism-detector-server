package zev.plagiarismdetectorserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zev.plagiarismdetectorserver.entity.enums.Gender;

import java.time.LocalDate;
import java.util.Set;

@Entity(name = "profile_user")
@Table(name = "java_user_profile_01")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String Address;
    private String phoneNumber;
    private Gender gender;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = ClassRoom.class)
    @JoinTable(
            name = "java_user_class_0001",
            joinColumns = @JoinColumn(name = "profile"),
            inverseJoinColumns = @JoinColumn(name = "class")
    )
    private Set<ClassRoom> classRooms;
}

package zev.plagiarismdetectorserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zev.plagiarismdetectorserver.entity.enums.Gender;

import java.time.LocalDate;

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
}

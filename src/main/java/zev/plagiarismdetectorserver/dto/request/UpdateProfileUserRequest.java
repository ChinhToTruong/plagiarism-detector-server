package zev.plagiarismdetectorserver.dto.request;

import lombok.Data;
import zev.plagiarismdetectorserver.entity.enums.Gender;

import java.time.LocalDate;

@Data
public class UpdateProfileUserRequest {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String Address;
    private String phoneNumber;
    private Gender gender;
}

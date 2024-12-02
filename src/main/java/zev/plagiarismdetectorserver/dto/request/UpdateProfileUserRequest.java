package zev.plagiarismdetectorserver.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import zev.plagiarismdetectorserver.entity.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateProfileUserRequest {
    @NotBlank(message = "first name khong duoc de trong")
    private String firstName;
    @NotBlank(message = "last name khong duoc de trong")
    private String lastName;
    private LocalDate dateOfBirth;
    private String Address;
    @NotBlank(message = "so dien thoai khogn duoc de trong")
    private String phoneNumber;
    private Gender gender;
    private String avatarUrl;
}

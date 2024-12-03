package zev.plagiarismdetectorserver.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import zev.plagiarismdetectorserver.anotations.EnumValue;
import zev.plagiarismdetectorserver.entity.enums.Role;

@Getter
public class AddUserRequest {
    @NotBlank(message = "email không được để trống.")
    @Email(message = "email chưa đúng định dạng. Vui lòng nhập lại.")
    private String email;
    @NotBlank(message = "password không được để trống.")
    private String password;

    @NotBlank(message = "role khong duoc de trong")
    @EnumValue(name="role", enumClass = Role.class, message = "must be student or teacher")
    private String role;
}

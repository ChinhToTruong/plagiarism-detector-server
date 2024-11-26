package zev.plagiarismdetectorserver.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import zev.plagiarismdetectorserver.entity.enums.Role;

@Data
public class AddUserRequest {
    @NotBlank(message = "email không được để trống.")
    @Email(message = "email chưa đúng định dạng. Vui lòng nhập lại.")
    private String email;
    @NotBlank(message = "password không được để trống.")
    private String password;
    private Role role;
}

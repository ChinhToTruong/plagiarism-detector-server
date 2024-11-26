package zev.plagiarismdetectorserver.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "ten dang nhap khong duoc de trong")
    private String username;
    @NotBlank(message = "mat khau khong duoc de trong")
    private String password;
}

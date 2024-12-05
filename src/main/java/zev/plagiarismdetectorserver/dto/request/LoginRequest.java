package zev.plagiarismdetectorserver.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
public class LoginRequest {

  @NotBlank(message = "ten dang nhap khong duoc de trong")
  private String username;

  @NotBlank(message = "mat khau khong duoc de trong")
  private String password;
}

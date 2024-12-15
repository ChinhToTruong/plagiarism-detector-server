package zev.plagiarismdetectorserver.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class SignInRequest implements Serializable {

  @NotBlank(message = "email not blank")
  private String email;

  @NotBlank(message = "password not blank")
  private String password;
}

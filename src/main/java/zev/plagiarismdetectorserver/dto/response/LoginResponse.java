package zev.plagiarismdetectorserver.dto.response;

import java.io.Serializable;
import lombok.*;

@Getter
public class LoginResponse implements Serializable {

  private String userId;

  private String token;
}

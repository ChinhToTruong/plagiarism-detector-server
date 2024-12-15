package zev.plagiarismdetectorserver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TokenResponse {

  private String accessToken;

  private String refreshToken;

  private String userId;
}

package zev.plagiarismdetectorserver.service;

import zev.plagiarismdetectorserver.dto.request.SignInRequest;
import zev.plagiarismdetectorserver.dto.response.TokenResponse;

public interface AuthenticationService {

  TokenResponse login(SignInRequest request);

  void logout();

  TokenResponse refreshToken();
}

package zev.plagiarismdetectorserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import zev.plagiarismdetectorserver.dto.request.SignInRequest;
import zev.plagiarismdetectorserver.dto.response.TokenResponse;
import zev.plagiarismdetectorserver.exception.UserNotFound;
import zev.plagiarismdetectorserver.repository.UserRepository;
import zev.plagiarismdetectorserver.service.AuthenticationService;
import zev.plagiarismdetectorserver.service.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserRepository userRepository;

  private final AuthenticationManager authenticationManager;

  private final JwtService jwtService;

  @Override
  public TokenResponse login(SignInRequest request) {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

    var user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(UserNotFound::new);

    String accessToken = jwtService.generateToken(user);
    String refreshToken = "refresh_token";

    return TokenResponse.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .userId(user.getId())
        .build();
  }

  @Override
  public void logout() {

  }

  @Override
  public TokenResponse refreshToken() {
    return null;
  }
}

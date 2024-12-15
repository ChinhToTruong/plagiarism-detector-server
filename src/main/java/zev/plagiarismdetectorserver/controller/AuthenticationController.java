package zev.plagiarismdetectorserver.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zev.plagiarismdetectorserver.dto.request.SignInRequest;
import zev.plagiarismdetectorserver.dto.response.ResponseData;
import zev.plagiarismdetectorserver.dto.response.TokenResponse;
import zev.plagiarismdetectorserver.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller")
@Slf4j(topic = "Auth")
@Validated
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseData<TokenResponse> login(
      @RequestBody @Valid SignInRequest request) {

    var data = authenticationService.login(request);

    return new ResponseData<>("Login Successful", data);
  }

  @PostMapping("/logout")
  public String logout() {
    return "You have been logged out!";
  }

  @PostMapping("/refresh-token")
  public String refreshToken() {
    return "You have been refreshed!";
  }

  @PostMapping("/forgot-password")
  public String forgotPassword() {
    return "You have been forgotten password!";
  }
}

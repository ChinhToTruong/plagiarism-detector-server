package zev.plagiarismdetectorserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import zev.plagiarismdetectorserver.dto.request.LoginRequest;
import zev.plagiarismdetectorserver.dto.response.LoginResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){

        return LoginResponse.builder()
                .userId("user id")
                .token("token")
                .build();
    }
}

package com.alsselssajob.auth.ui;

import com.alsselssajob.auth.application.AuthService;
import com.alsselssajob.auth.dto.request.LoginRequest;
import com.alsselssajob.auth.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/auth/login")
    public LoginResponse login(@RequestBody final LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}

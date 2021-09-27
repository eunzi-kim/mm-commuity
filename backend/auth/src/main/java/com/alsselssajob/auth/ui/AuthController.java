package com.alsselssajob.auth.ui;

import com.alsselssajob.auth.application.AuthService;
import com.alsselssajob.auth.dto.request.LoginRequest;
import com.alsselssajob.auth.dto.request.LogoutRequest;
import com.alsselssajob.auth.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));

    }

    @PostMapping("/api/auth/logout")
    public ResponseEntity logout(@RequestBody final LogoutRequest logoutRequest) {
        authService.logout(logoutRequest);
        return ResponseEntity.noContent().build();

    }

}

package com.alsselssajob.auth.ui;

import com.alsselssajob.auth.application.AuthService;
import com.alsselssajob.auth.dto.request.LoginRequest;
import com.alsselssajob.auth.dto.request.LogoutRequest;
import com.alsselssajob.auth.dto.request.TokenRequest;
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
        // ok는 상태코드가 항상 200이 뜨게된다.
        // FE는 상태코드가 200이 뜨더라도 body부분을 확인하여 제대로 된 것인지 아닌지 확인하여야 한다.
        // 상태코드로 판별하면 안된다!
        // body부분에 뭐라고 메시지 오는지는 확인해야한다.
        return ResponseEntity.ok(authService.login(loginRequest));

    }

    @PostMapping("/api/auth/logout")
    public ResponseEntity logout(@RequestBody final LogoutRequest logoutRequest) {
        authService.logout(logoutRequest);
        return ResponseEntity.noContent()
                .build();
    }

    @PostMapping("/api/auth/token")
    public ResponseEntity token(@RequestBody final TokenRequest tokenRequest) {

        //200 or 400
        if (authService.validateToken(tokenRequest)) {
            return ResponseEntity.noContent()
                    .build();
        }
        return ResponseEntity.badRequest()
                .build();
    }

}

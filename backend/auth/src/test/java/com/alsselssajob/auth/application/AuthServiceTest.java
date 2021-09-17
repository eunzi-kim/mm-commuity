package com.alsselssajob.auth.application;

import com.alsselssajob.auth.domain.Token;
import com.alsselssajob.auth.domain.TokenRepository;
import com.alsselssajob.auth.dto.request.LoginRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @InjectMocks
    private AuthService authService;
    @Mock
    private TokenRepository tokenRepository;

    @DisplayName("AuthService 클래스 / login 테스트")
    @Test
    void login_test() {

        ReflectionTestUtils.setField(authService, "mattermostUrl", "https://meeting.ssafy.com");

        final LoginRequest loginRequest = LoginRequest.builder()
                .id("kskyu610@gmail.com")
                .password("Skskyu610@gmail.com5")
                .build();
        final Token token = Token.builder()
                .userId("sdfgb")
                .token("hhbfhyrh")
                .isActive(true)
                .build();
        doReturn(token).when(tokenRepository).save(any());

        authService.login(loginRequest);
        verify(tokenRepository, times(1)).save(any());
    }

}
package com.alsselssajob.auth.application;

import com.alsselssajob.auth.domain.Token;
import com.alsselssajob.auth.domain.TokenRepository;
import com.alsselssajob.auth.dto.request.LoginRequest;
import com.alsselssajob.auth.dto.request.LogoutRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//Mock 테스트
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @InjectMocks
    private AuthService authService;
    @Mock
    private TokenRepository tokenRepository;

    @DisplayName("AuthService 클래스 / login 테스트")
    @Test
    void login_test() {
        //given
        ReflectionTestUtils.setField(authService, "mattermostUrl", "https://meeting.ssafy.com");

        // 생성자를 대신해서 명확하게 하기위해서..?
        // 같은 타입의 파라미터를 받을 때 순서가 바뀔 수 있음.
        final LoginRequest loginRequest = LoginRequest.builder()
                .id("kskyu610@gmail.com")
                .password("Skskyu610@gmail.com5")
                .build();
        final Token token = Token.builder()
                .userId("test_userId")
                .token("test_token")
                .isActive(true)
                .build();

        // any() : 파라미터에 값 직접 X
        doReturn(token).when(tokenRepository).save(any());

        //when
        authService.login(loginRequest);

        //then
        verify(tokenRepository, times(1)).save(any());
    }

    @DisplayName("AuthService 클래스 / logout 테스트")
    @Test
    void logout_test() {
        //given : 테스트하기위한 변수 등 필요한 애들을 정의해놓는 부분.
        //when : 테스트할 메서드를 호출한다.
        //then : aseertThat 검증하는 로직.

        //given
        ReflectionTestUtils.setField(authService, "mattermostUrl", "https://meeting.ssafy.com");

        // 생성자를 대신해서 명확하게 하기위해서.
        // 같은 타입의 파라미터를 받을 때 순서가 바뀔 수 있음.
        final LogoutRequest logoutRequest = LogoutRequest.builder()
                .userId("test_userId")
                .build();

        final Token currentToken = Token.builder()
                .userId("test_userId")
                .token("test_token")
                .isActive(true)
                .build();

        doReturn(Optional.of(currentToken)).when(tokenRepository).findByUserIdAndIsActive(any(), any());

        //when
        authService.logout(logoutRequest);

        //then
        verify(tokenRepository, times(1)).findByUserIdAndIsActive(any(), any());

    }

}
package com.alsselssajob.auth.ui;

import com.alsselssajob.auth.application.AuthService;
import com.alsselssajob.auth.dto.request.LoginRequest;
import com.alsselssajob.auth.dto.request.LogoutRequest;
import com.alsselssajob.auth.dto.request.TokenRequest;
import com.alsselssajob.auth.dto.response.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AuthService authService;

    @DisplayName("AuthController 클래스 / loginApi 테스트")
    @Test
    void login_api_test() throws Exception {
        final LoginRequest loginRequest = LoginRequest.builder()
                .id("test_id")
                .password("test_password")
                .build();

        final LoginResponse loginResponse = LoginResponse.builder()
                .userId("test_userId")
                .userName("test_userName")
                .nickName("test_nickName")
                .build();

        doReturn(loginResponse).when(authService).login(any());

        // 실질적 요청 부분
        mockMvc.perform(post("/api/auth/login")
                        .content(objectMapper.writeValueAsString(loginRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("test_userId"))
                .andExpect(jsonPath("$.userName").value("test_userName"))
                .andExpect(jsonPath("$.nickName").value("test_nickName"));

    }

    @DisplayName("AuthController 클래스 / logoutApi 테스트")
    @Test
    void logout_api_test() throws Exception {

        final LogoutRequest logoutRequest = LogoutRequest.builder()
                .userId("test_userId")
                .build();

        doNothing().when(authService).logout(any());

        // 실질적 요청 부분
        mockMvc.perform(post("/api/auth/logout")
                        .content(objectMapper.writeValueAsString(logoutRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @DisplayName("AuthController 클래스 / success_token_api_test 테스트")
    @Test
    void success_token_api_test() throws Exception {

        final TokenRequest tokenRequest = TokenRequest.builder()
                .token("test_token")
                .build();

        // mock객체에 대한 부분. Authservice
        doReturn(true).when(authService).validateToken(any());

        // 실질적 요청 부분
        mockMvc.perform(post("/api/auth/token")
                        .content(objectMapper.writeValueAsString(tokenRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @DisplayName("AuthController 클래스 / fail_token_api_test 테스트")
    @Test
    void fail_token_api_test() throws Exception {

        final TokenRequest tokenRequest = TokenRequest.builder()
                .token("test_token")
                .build();

        // mock객체에 대한 부분. Authservice
        doReturn(false).when(authService).validateToken(any());

        // 실질적 요청 부분
        mockMvc.perform(post("/api/auth/token")
                        .content(objectMapper.writeValueAsString(tokenRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

}
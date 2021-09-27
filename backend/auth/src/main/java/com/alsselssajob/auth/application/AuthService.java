package com.alsselssajob.auth.application;

import com.alsselssajob.auth.domain.Token;
import com.alsselssajob.auth.domain.TokenRepository;
import com.alsselssajob.auth.dto.request.LoginRequest;
import com.alsselssajob.auth.dto.request.LogoutRequest;
import com.alsselssajob.auth.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import net.bis5.mattermost.client4.ApiResponse;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Level;

@RequiredArgsConstructor
@Service
public class AuthService {

    private static final String TOKEN_KEY = "Token";

    private final TokenRepository tokenRepository;

    @Value("${mattermost.url}")
    private String mattermostUrl;

    @Transactional
    public LoginResponse login(final LoginRequest loginRequest) {

        final MattermostClient client = MattermostClient.builder()
                .url(mattermostUrl)
                .logLevel(Level.INFO)
                .ignoreUnknownProperties()
                .build();

        final ApiResponse<User> apiResponse = client.login(loginRequest.id(), loginRequest.password());
        final String tokenValue = (String) apiResponse.getRawResponse()
                .getHeaders()
                .getFirst(TOKEN_KEY);

        final User user = apiResponse.readEntity();
        final String userId = user.getId();
        final String userName = user.getUsername();
        final String nickName = user.getNickname();

        final Token token = Token.builder()
                .userId(userId)
                .token(tokenValue)
                .isActive(true)
                .build();

        tokenRepository.save(token);

        return LoginResponse.builder()
                .userId(userId)
                .nickName(nickName)
                .userName(userName)
                .build();
    }

    @Transactional
    public void logout(final LogoutRequest logoutRequest) {

        Token currentToken = tokenRepository.findByUserIdAndIsActive(logoutRequest.userId(), true)
                .orElseThrow(IllegalArgumentException::new);

        currentToken = currentToken.switchIsActiveToFalse();

    }

}

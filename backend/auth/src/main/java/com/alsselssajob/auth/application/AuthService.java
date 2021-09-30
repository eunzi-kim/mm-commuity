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

        // MM에서의 로그인 정보를 가져와야하니 일단 MM을 불러오자.
        final MattermostClient client = MattermostClient.builder()
                .url(mattermostUrl)
                .logLevel(Level.INFO)
                .ignoreUnknownProperties()
                .build();

        // MM에서 client.login을 하면 여기에 담아야한다고 적혀져있을 것.
        // login인한 유저의 id와 password가져온다.
        final ApiResponse<User> apiResponse = client.login(loginRequest.id(), loginRequest.password());
        // user정보가 들어있는 apiResponse에서 Token을 가져온 것.
        final String tokenValue = (String) apiResponse.getRawResponse()
                .getHeaders()
                .getFirst(TOKEN_KEY);

        // 이것역시 MM의 api문서에 적혀있을 것.
        // apiResponse.readEntity()를 하면 user정보를 가지고 올 수 있음.
        final User user = apiResponse.readEntity();
        final String userId = user.getId();
        final String userName = user.getUsername();
        final String nickName = user.getNickname();

        // Token.java에서 가져온 것.
        final Token token = Token.builder()
                .userId(userId)
                .token(tokenValue)
                .isActive(true)
                .build();

        // TokenRepository가 JpaRepository를 extends하는데,
        // 이럴경우 바로 .save()만 하면 마법같이 저장이된다.
        // 세부 내용은.. 너무 깊이 들어가야해서 생략.
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

        currentToken.switchIsActiveToFalse();

    }

}

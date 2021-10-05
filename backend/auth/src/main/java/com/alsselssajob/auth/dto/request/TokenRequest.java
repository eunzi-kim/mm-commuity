package com.alsselssajob.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TokenRequest {

    @JsonProperty("token")
    private String token;

    @Builder
    public TokenRequest(final String token) {

        this.token = token;
    }

    public String token() {
        return token;
    }

}

package com.alsselssajob.domain.scrap.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RequestScrap {
    private String token;
    private String userId;

    public String token() {
        return token;
    }

    public String userId() {
        return userId;
    }
}

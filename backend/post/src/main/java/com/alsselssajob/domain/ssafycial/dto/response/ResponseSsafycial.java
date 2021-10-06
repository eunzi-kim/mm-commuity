package com.alsselssajob.domain.ssafycial.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

@Getter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ResponseSsafycial {
    @JsonProperty
    private String ssafycial_id;
    @JsonProperty
    private String title;
    @JsonProperty
    private String link;
    @JsonProperty
    private String user_id;
    @JsonProperty
    private String username;
    @JsonProperty
    private String nickname;

    @Builder
    public ResponseSsafycial(final String ssafycial_id, final String title, final String link, final String user_id, final String username, final String nickname) {
        this.ssafycial_id = ssafycial_id;
        this.title = title;
        this.link = link;
        this.user_id = user_id;
        this.username = username;
        this.nickname = nickname;
    }
}

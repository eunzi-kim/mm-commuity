package com.alsselssajob.domain.scrap.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ResponseScrap {
    @JsonProperty
    private String postId;
    @JsonProperty
    private String username;
    @JsonProperty
    private String nickname;
    @JsonProperty
    private String profileImg;
    @JsonProperty
    private String message;
    @JsonProperty
    private boolean isScrapped;
    @JsonProperty
    private String createdDate;

    @Builder
    public ResponseScrap(final String postId, final String message, final String createdDate,
                        final String username, final String nickname, final String profileImg,
                        final String channelName, final String teamName, final boolean isScrapped,
                        final String fileId, final String fileName, final String fileExtension) {
        this.postId = postId;
        this.isScrapped = isScrapped;
        this.message = message;
        this.createdDate = createdDate;
        this.username = username;
        this.nickname = nickname;
        this.profileImg = profileImg;
    }
}

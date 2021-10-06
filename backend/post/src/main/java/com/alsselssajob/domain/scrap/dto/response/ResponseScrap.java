package com.alsselssajob.domain.scrap.dto.response;

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
    private String teamName;
    @JsonProperty
    private String channelName;
    @JsonProperty
    private String username;
    @JsonProperty
    private String nickname;
    @JsonProperty
    private String profileImage;
    @JsonProperty
    private String content;
    @JsonProperty
    private String isScrapped;
    @JsonProperty
    private String createdDate;
    @JsonProperty
    private String fileId;
    @JsonProperty
    private String fileName;
    @JsonProperty
    private String fileExtension;
}

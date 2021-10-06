package com.alsselssajob.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

@Getter @Setter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ResponsePost {
    @JsonProperty
    private String postId;
    @JsonProperty
    private String channelId;
    @JsonProperty
    private String channelName;
    @JsonProperty
    private String teamName;
    @JsonProperty
    private String isScrapped;
    @JsonProperty
    private String rootId;
    @JsonProperty
    private String parentId;
    @JsonProperty
    private String message;
    @JsonProperty
    private String hashTag;
    @JsonProperty
    private String createdAt;
    @JsonProperty
    private String createdDate;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String username;
    @JsonProperty
    private String nickname;
    @JsonProperty
    private String profileImg;
    @JsonProperty
    private String emojiName;
    @JsonProperty
    private String fileId;
    @JsonProperty
    private String fileName;
    @JsonProperty
    private String fileExtension;

    @Builder
    public ResponsePost(final String postId, final String channelId, final String rootId,
                        final String parentId, final String message, final String hashTag,
                        final String createdAt, final String createdDate, final String userId,
                        final String username, final String nickname, final String profileImg,
                        final String channelName, final String teamName, final String isScrapped,
                        final String emojiName, final String fileId, final String fileName, final String fileExtension) {
        this.postId = postId;
        this.channelId = channelId;
        this.channelName = channelName;
        this.teamName = teamName;
        this.isScrapped = isScrapped;
        this.rootId = rootId;
        this.parentId = parentId;
        this.message = message;
        this.hashTag = hashTag;
        this.createdAt = createdAt;
        this.createdDate = createdDate;
        this.userId = userId;
        this.username = username;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.emojiName = emojiName;
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }
}

package com.alsselssajob.student.dto.response;

import com.alsselssajob.student.domain.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@NoArgsConstructor
@Getter
public class ScoreResponse {

    private String userId;
    private String image;
    private String username;
    private int postCount;
    private int reactingCount;
    private int reactedCount;
    private int point;

    @Builder
    public ScoreResponse(final String userId, final String image, final String username,
                         final int postCount, final int reactingCount, final int reactedCount, final int point) {
        this.userId = userId;
        this.image = image;
        this.username = username;
        this.postCount = postCount;
        this.reactingCount = reactingCount;
        this.reactedCount = reactedCount;
        this.point = point;
    }

}
package com.alsselssajob.student.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ScoreRequest {
    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private String id;

    @Builder
    public ScoreRequest(final String type, final String id) {

        this.type = type;
        this.id = id;
    }

    public String type() { return type; }

    public String id() {
        return id;
    }
}
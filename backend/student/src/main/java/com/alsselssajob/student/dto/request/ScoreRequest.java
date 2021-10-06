package com.alsselssajob.student.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ScoreRequest {
    @JsonProperty("filter")
    private String filter;
    @JsonProperty("id")
    private String id;

    @Builder
    public ScoreRequest(final String filter, final String id) {

        this.filter = filter;
        this.id = id;
    }

    public String filter() { return filter; }

    public String id() {
        return id;
    }
}
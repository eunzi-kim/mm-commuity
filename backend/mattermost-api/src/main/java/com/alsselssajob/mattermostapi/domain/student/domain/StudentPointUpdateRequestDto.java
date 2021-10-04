package com.alsselssajob.mattermostapi.domain.student.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StudentPointUpdateRequestDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("postCountForUpdate")
    private Integer postCountForUpdate;

    @JsonProperty("reactedCountForUpdate")
    private Integer reactedCountForUpdate;

    @JsonProperty("reactingCountForUpdate")
    private Integer reactingCountForUpdate;

    @Builder
    public StudentPointUpdateRequestDto(final String id, final Integer postCountForUpdate,
                                        final Integer reactedCountForUpdate, final Integer reactingCountForUpdate) {
        this.id = id;
        this.postCountForUpdate = postCountForUpdate;
        this.reactedCountForUpdate = reactedCountForUpdate;
        this.reactingCountForUpdate = reactingCountForUpdate;
    }

    public String id() {
        return id;
    }

    public Integer postCountForUpdate() {
        return postCountForUpdate;
    }

    public Integer reactedCountForUpdate() {
        return reactedCountForUpdate;
    }

    public Integer reactingCountForUpdate() {
        return reactingCountForUpdate;
    }
}

package com.alsselssajob.mattermostapi.domain.studentPoint.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
public class StudentPointUpdateRequest {

    @JsonProperty("id")
    private String id;

    @JsonProperty("postCountForUpdate")
    private Integer postCountForUpdate;

    @JsonProperty("reactedCountForUpdate")
    private Integer reactedCountForUpdate;

    @JsonProperty("reactingCountForUpdate")
    private Integer reactingCountForUpdate;

    @Builder
    public StudentPointUpdateRequest(final String id, final Integer postCountForUpdate,
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentPointUpdateRequest that = (StudentPointUpdateRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void updateReactingCount(int reactingCountForUpdate) {
        this.reactingCountForUpdate = reactingCountForUpdate;
    }
}

package com.alsselssajob.mattermostapi.domain.studentPoint.dto.request;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
public class StudentPointUpdateRequest {

    private final static int INITIAL_POINT = 0;

    private String id;
    private int postCountForUpdate;
    private int reactedCountForUpdate;
    private int reactingCountForUpdate;
    private int point;

    @Builder
    public StudentPointUpdateRequest(final String id, final int postCountForUpdate,
                                     final int reactedCountForUpdate, final int reactingCountForUpdate) {
        this.id = id;
        this.postCountForUpdate = postCountForUpdate;
        this.reactedCountForUpdate = reactedCountForUpdate;
        this.reactingCountForUpdate = reactingCountForUpdate;
        point = INITIAL_POINT;
    }

    public String id() {
        return id;
    }

    public int postCountForUpdate() {
        return postCountForUpdate;
    }

    public int reactedCountForUpdate() {
        return reactedCountForUpdate;
    }

    public int reactingCountForUpdate() {
        return reactingCountForUpdate;
    }

    public int point() {
        return point;
    }

    public void updateReactingCount(int reactingCountForUpdate) {
        this.reactingCountForUpdate = reactingCountForUpdate;
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
}

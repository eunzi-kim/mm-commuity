package com.alsselssajob.mattermostapi.domain.studentPoint.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class StudentPoint {

    private final static int INITIAL_POINT = 0;
    private final static int POST_COUNT_WEIGHT = 3;
    private final static int REACTION_COUNT_WEIGHT = 1;

    @Id
    private String userId;

    private int postCount;
    private int reactedCount;
    private int reactingCount;
    private int point;

    @Builder
    public StudentPoint(final String userId, final int postCount, final int reactedCount, final int reactingCount) {
        this.userId = userId;
        this.postCount = postCount;
        this.reactedCount = reactedCount;
        this.reactingCount = reactingCount;
        point = INITIAL_POINT;
    }

    public String userId() {
        return userId;
    }

    public int postCount() {
        return postCount;
    }

    public int reactedCount() {
        return reactedCount;
    }

    public int reactingCount() {
        return reactingCount;
    }

    public int point() {
        return point;
    }

    public void updateReactingCount(int reactingCount) {
        this.reactingCount = reactingCount;
    }

    public void calculatePoint() {
        point += postCount * POST_COUNT_WEIGHT
                + reactedCount * REACTION_COUNT_WEIGHT
                + reactingCount * REACTION_COUNT_WEIGHT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentPoint that = (StudentPoint) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}

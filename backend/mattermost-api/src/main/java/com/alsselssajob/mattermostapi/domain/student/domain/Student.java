package com.alsselssajob.mattermostapi.domain.student.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class Student {

    private final static int INITIAL_POINT = 0;
    private final static int POST_COUNT_WEIGHT = 3;
    private final static int REACTION_COUNT_WEIGHT = 1;

    //멤버 변수
    @Id
    @Column(length = 30)
    private String userId;
    private String image;
    private String username;
    private int postCount;
    private int reactingCount;
    private int reactedCount;
    private int point;


    @Builder
    public Student(final String userId, final String image, final String username,
                   final int postCount, final int reactingCount, final int reactedCount, final int point) {
        this.userId = userId;
        this.image = image;
        this.username = username;
        this.postCount = postCount;
        this.reactingCount = reactingCount;
        this.reactedCount = reactedCount;
        this.point = point;
    }

    public String userId() { return userId; }

    public String image() { return image; }

    public String username() { return username; }

    public int postCount() { return postCount; }

    public int reactingCount() { return reactingCount; }

    public int reactedCount() { return reactedCount; }

    public int point() { return point; }


    public void updatePostAndReactedCount(final int postCount, final int reactedCount) {
        this.postCount += postCount;
        this.reactedCount += reactedCount;
    }

    public void updateReactingCount(final int reactingCount) {
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
        Student that = (Student) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}

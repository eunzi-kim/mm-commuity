package com.alsselssajob.student.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Student {
    //멤버 변수
    @Id
    @Column(name = "id", length = 30)
    private String userId;

    @Column(name = "image")
    private String image;

    @Column(name="username")
    private String username;

    @Column(name="postCount")
    private int postCount;
    @Column(name="reactingCount")
    private int reactingCount;
    @Column(name="reactedCount")
    private int reactedCount;
    @Column(name="point")
    private int point;


    @Builder
    public Student(final String userId, final String image, final String username, final int postCount, final int reactingCount, final int reactedCount, final int point) {
        this.userId = userId;
        this.image = image;
        this.username = username;
        this.postCount = postCount;
        this.reactingCount = reactingCount;
        this.reactedCount = reactedCount;
        this.point = point;

    }

    //Getters
    public String userId() { return userId; }

    public String image() { return image; }

    public String username() { return username; }

    public int postCount() { return postCount; }

    public int reactingCount() { return reactingCount; }

    public int reactedCount() { return reactedCount; }

    public int point() { return point; }

}
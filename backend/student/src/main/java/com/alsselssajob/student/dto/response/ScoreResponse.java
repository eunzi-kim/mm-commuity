package com.alsselssajob.student.dto.response;

import com.alsselssajob.student.domain.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ScoreResponse {
    @JsonProperty("list")
    private List<Student> list;

    @Builder
    public ScoreResponse(final List<Student> list) {
        this.list = list;
    }

    public List<Student> list() { return list; }

}
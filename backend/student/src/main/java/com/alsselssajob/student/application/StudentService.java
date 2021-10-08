package com.alsselssajob.student.application;

import com.alsselssajob.student.domain.Student;
import com.alsselssajob.student.domain.StudentRepository;
import com.alsselssajob.student.dto.request.ScoreRequest;
import com.alsselssajob.student.dto.response.ScoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.logging.Level;

@RequiredArgsConstructor
@Service
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;

    @Transactional
    public List<ScoreResponse> bestUploader() {

        //calculate score
        //DB colulmn에 맞게 고쳐
        final List<Student> ranking = studentRepository.findAll(Sort.by(Sort.Direction.DESC, "postCount"));
        System.out.println(ranking.get(0));
        final List<ScoreResponse> result = new ArrayList<ScoreResponse>();
        for(Student s : ranking) {
            result.add(ScoreResponse.builder()
                    .userId(s.userId())
                    .username(s.username())
                    .image(s.image())
                    .postCount(s.postCount())
                    .reactedCount(s.reactedCount())
                    .reactingCount(s.reactingCount())
                    .point(s.point())
                    .build());
        }
        return result;
    }

    @Transactional
    public List<ScoreResponse> bestReaction() {

        final List<Student> ranking = studentRepository.findAll(Sort.by(Sort.Direction.DESC, "reactingCount"));

        final List<ScoreResponse> result = new ArrayList<ScoreResponse>();
        for(Student s : ranking) {
            result.add(ScoreResponse.builder()
                    .userId(s.userId())
                    .username(s.username())
                    .image(s.image())
                    .postCount(s.postCount())
                    .reactedCount(s.reactedCount())
                    .reactingCount(s.reactingCount())
                    .point(s.point())
                    .build());
        }

        return result;
    }

    @Transactional
    public List<ScoreResponse> mostReaction() {

        final List<Student> ranking = studentRepository.findAll(Sort.by(Sort.Direction.DESC, "reactedCount"));

        final List<ScoreResponse> result = new ArrayList<ScoreResponse>();
        for(Student s : ranking) {
            result.add(ScoreResponse.builder()
                    .userId(s.userId())
                    .username(s.username())
                    .image(s.image())
                    .postCount(s.postCount())
                    .reactedCount(s.reactedCount())
                    .reactingCount(s.reactingCount())
                    .point(s.point())
                    .build());
        }
        return result;
    }

    @Transactional
    public List<ScoreResponse> bestStudents() {

        final List<Student> ranking = studentRepository.findAll(Sort.by(Sort.Direction.DESC, "point"));

        final List<ScoreResponse> result = new ArrayList<ScoreResponse>();
        for(Student s : ranking) {
            result.add(ScoreResponse.builder()
                    .userId(s.userId())
                    .username(s.username())
                    .image(s.image())
                    .postCount(s.postCount())
                    .reactedCount(s.reactedCount())
                    .reactingCount(s.reactingCount())
                    .point(s.point())
                    .build());
        }

        return result;
    }

    @Transactional
    public ScoreResponse bestStudent(final ScoreRequest scoreRequest) {

        final Student r = studentRepository.findById(scoreRequest.id()).get();

        return ScoreResponse.builder()
                .userId(r.userId())
                .username(r.username())
                .image(r.image())
                .postCount(r.postCount())
                .reactedCount(r.reactedCount())
                .reactingCount(r.reactingCount())
                .point(r.point())
                .build();

    }

}
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

    @Value("${mattermost.url}")
    private String mattermostUrl;

    @Transactional
    public ScoreResponse bestUploader(final ScoreRequest scoreRequest) {

        //calculate score
        //DB colulmn에 맞게 고쳐

        final List<Student> ranking = studentRepository.findAll(Sort.by(Sort.Direction.DESC, "postCount"));

        return ScoreResponse.builder()
                .list(ranking)
                .build();
    }

    @Transactional
    public ScoreResponse bestReaction(final ScoreRequest scoreRequest) {

        final List<Student> ranking = studentRepository.findAll(Sort.by(Sort.Direction.DESC, "reactingCount"));

        return ScoreResponse.builder()
                .list(ranking)
                .build();
    }

    @Transactional
    public ScoreResponse mostReaction(final ScoreRequest scoreRequest) {

        //calculate score
        //DB colulmn에 맞게 고쳐
//        final Student student = Student.builder()
//                .userId()
//                .point()
//                .type()
//                .build();

        final List<Student> ranking = studentRepository.findAll(Sort.by(Sort.Direction.DESC, "reactedCount"));

        return ScoreResponse.builder()
                .list(ranking)
                .build();
    }

    @Transactional
    public ScoreResponse bestStudents(final ScoreRequest scoreRequest) {

        final List<Student> ranking = studentRepository.findAll(Sort.by(Sort.Direction.DESC, "point"));

        return ScoreResponse.builder()
                .list(ranking)
                .build();
    }

    @Transactional
    public ScoreResponse bestStudent(final ScoreRequest scoreRequest) {

//        final MattermostClient client = MattermostClient.builder()
//                .url(mattermostUrl)
//                .longLevel(Level.INFO)
//                .ignoreUnknownProperties()
//                .build();

        //calculate score
        //DB colulmn에 맞게 고쳐
//        final Student student = Student.builder()
//                .userId()
//                .point()
//                .type()
//                .build();

        final Optional<Student> ranking = studentRepository.findById(scoreRequest.id());

        return ScoreResponse.builder()
                .list(new ArrayList<Student>())
                .build();
    }

}
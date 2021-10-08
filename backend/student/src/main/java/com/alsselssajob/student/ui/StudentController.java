package com.alsselssajob.student.ui;

import com.alsselssajob.student.application.StudentService;
import com.alsselssajob.student.dto.request.ScoreRequest;
import com.alsselssajob.student.dto.response.ScoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(originPatterns = "*")
public class StudentController {
//  우수 교육생, 현재 사용자에 대한 점수. 게시글 없을 경우 점수 처리, 메소드 이름 수정
    private final StudentService studentService;

    @GetMapping("/api/students/best-uploader")
    public ResponseEntity<List<ScoreResponse>> getBestUploader() {
        return ResponseEntity.ok().body(studentService.bestUploader());
    }

    @GetMapping("/api/students/best-reaction")
    public ResponseEntity<List<ScoreResponse>> getBestReaction() {
        return ResponseEntity.ok(studentService.bestReaction());
    }

    @GetMapping("/api/students/most-reaction")
    public ResponseEntity<List<ScoreResponse>> getMostReaction() {
        return ResponseEntity.ok(studentService.mostReaction());
    }

    @PostMapping("/api/edu-pro/best-students")
    public ResponseEntity<List<ScoreResponse>> getBestStudents() {
        return ResponseEntity.ok(studentService.bestStudents());
    }

    @GetMapping("/api/edu-pro/best-students")
    public ResponseEntity<ScoreResponse> getBestStudent(@RequestParam final String id) {
        ScoreRequest scoreRequest = ScoreRequest.builder().id(id).build();
        return ResponseEntity.ok(studentService.bestStudent(scoreRequest));
    }
}
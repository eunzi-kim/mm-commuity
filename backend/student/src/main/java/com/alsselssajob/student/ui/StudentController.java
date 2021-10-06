package com.alsselssajob.student.ui;

import com.alsselssajob.student.application.StudentService;
import com.alsselssajob.student.dto.request.ScoreRequest;
import com.alsselssajob.student.dto.response.ScoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StudentController {
//  우수 교육생, 현재 사용자에 대한 점수. 게시글 없을 경우 점수 처리, 메소드 이름 수정
    private final StudentService studentService;

    @GetMapping("/api/students/?filter=best-uploader")
    public ResponseEntity<ScoreResponse> getBestUploader(@RequestBody final ScoreRequest scoreRequest) {
        return ResponseEntity.ok(studentService.bestUploader(scoreRequest));
    }

    @GetMapping("/api/students/?filter=best-reaction")
    public ResponseEntity<ScoreResponse> getBestReaction(@RequestBody final ScoreRequest scoreRequest) {
        return ResponseEntity.ok(studentService.bestUploader(scoreRequest));
    }

    @GetMapping("/api/students/?filter=")
    public ResponseEntity<ScoreResponse> getMostReaction(@RequestBody final ScoreRequest scoreRequest) {
        return ResponseEntity.ok(studentService.bestUploader(scoreRequest));
    }

    @PostMapping("/api/edu-pro/best-students")
    public ResponseEntity<ScoreResponse> getBestStudents(@RequestBody final ScoreRequest scoreRequest) {
        return ResponseEntity.ok(studentService.bestUploader(scoreRequest));
    }

    @GetMapping("/api/edu-pro/best-students")
    public ResponseEntity<ScoreResponse> getBestStudent(@RequestBody final ScoreRequest scoreRequest) {
        return ResponseEntity.ok(studentService.bestUploader(scoreRequest));
    }
}
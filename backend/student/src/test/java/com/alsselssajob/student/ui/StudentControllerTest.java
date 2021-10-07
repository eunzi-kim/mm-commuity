package com.alsselssajob.student.ui;

import com.alsselssajob.student.application.StudentService;
import com.alsselssajob.student.domain.Student;
import com.alsselssajob.student.dto.request.ScoreRequest;
import com.alsselssajob.student.dto.response.ScoreResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private StudentService studentService;

    @DisplayName("StudentController 클래스 / bestUploaderApi 테스트")
    @Test
    void bestUploader_api_test() throws Exception {
        final ScoreRequest scoreRequest = ScoreRequest.builder()
                .id("test_userId")
                .type("type")
                .build();
        List<Student> ranking = new ArrayList<Student>();
        final ScoreResponse scoreResponse = ScoreResponse.builder()
                .list(ranking)
                .build();

        doReturn(scoreResponse).when(studentService).bestUploader(any());

        // 실질적 요청 부분
        mockMvc.perform(get("/api/students/?filter=best-uploader")
                        .content(objectMapper.writeValueAsString(scoreRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("test_userId"));

    }

    @DisplayName("StudentController 클래스 / bestReaction 테스트")
    @Test
    void bestReaction_api_test() throws Exception {

        final ScoreRequest scoreRequest = ScoreRequest.builder()
                .id("test_userId")
                .type("type")
                .build();
        List<Student> ranking = new ArrayList<Student>();
        final ScoreResponse scoreResponse = ScoreResponse.builder()
                .list(ranking)
                .build();

        doReturn(scoreResponse).when(studentService).bestReaction(any());

        // 실질적 요청 부분
        mockMvc.perform(get("/api/students/?filter=best-reaction")
                        .content(objectMapper.writeValueAsString(scoreRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
                //.andExpect(status().isNoContent());

    }

    @DisplayName("StudentController 클래스 / mostReaction 테스트")
    @Test
    void mostReaction_api_test() throws Exception {

        final ScoreRequest scoreRequest = ScoreRequest.builder()
                .id("test_userId")
                .type("type")
                .build();
        List<Student> ranking = new ArrayList<Student>();
        final ScoreResponse scoreResponse = ScoreResponse.builder()
                .list(ranking)
                .build();

        doReturn(scoreResponse).when(studentService).bestUploader(any());

        // 실질적 요청 부분
        mockMvc.perform(get("/api/students/?filter=")
                        .content(objectMapper.writeValueAsString(scoreRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("test_userId"));

    }

    @DisplayName("StudentController 클래스 / bestStudents_api_test 테스트")
    @Test
    void bestStudents_api_test() throws Exception {

        final ScoreRequest scoreRequest = ScoreRequest.builder()
                .id("test_userId")
                .type("type")
                .build();
        List<Student> ranking = new ArrayList<Student>();
        final ScoreResponse scoreResponse = ScoreResponse.builder()
                .list(ranking)
                .build();

        doReturn(scoreResponse).when(studentService).bestUploader(any());

        // 실질적 요청 부분
        mockMvc.perform(post("/api/edu-pro/best-students")
                        .content(objectMapper.writeValueAsString(scoreRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("test_userId"));

    }

    @DisplayName("StudentController 클래스 / bestStudent 테스트")
    @Test
    void bestStudent_api_test() throws Exception {

        final ScoreRequest scoreRequest = ScoreRequest.builder()
                .id("test_userId")
                .type("type")
                .build();
        List<Student> ranking = new ArrayList<Student>();
        final ScoreResponse scoreResponse = ScoreResponse.builder()
                .list(ranking)
                .build();

        doReturn(scoreResponse).when(studentService).bestUploader(any());

        // 실질적 요청 부분
        mockMvc.perform(get("/api/edu-pro/best-students")
                        .content(objectMapper.writeValueAsString(scoreRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("test_userId"));

    }
}

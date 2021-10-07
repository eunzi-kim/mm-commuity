package com.alsselssajob.student.application;

import com.alsselssajob.student.domain.Student;
import com.alsselssajob.student.domain.StudentRepository;
import com.alsselssajob.student.dto.request.ScoreRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//Mock 테스트
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;

    @DisplayName("StudentService 클래스 / bestUploader 테스트")
    @Test
    void bestUploader_test() {
        //given
        ReflectionTestUtils.setField(studentService, "mattermostUrl", "https://meeting.ssafy.com");

        // 생성자를 대신해서 명확하게 하기위해서..?
        // 같은 타입의 파라미터를 받을 때 순서가 바뀔 수 있음.
        final ScoreRequest scoreRequest = ScoreRequest.builder()
                .id("test@gmail.com")
                .build();
        final Student student = Student.builder()
                .userId("test_userId")
                .image("test.jpg")
                .username("test_name")
                .postCount(1)
                .reactingCount(2)
                .reactedCount(3)
                .point(6)
                .build();

        // any() : 파라미터에 값 직접 X
        final List<Student> ranking = new ArrayList<Student>();
        ranking.add(student);

        doReturn(ranking).when(studentRepository).findAll();

        //when
        studentService.bestUploader(scoreRequest);

        //then
        verify(studentRepository, times(1)).save(any());
    }

    @DisplayName("StudentService 클래스 / bestReaction 테스트")
    @Test
    void bestReaction_test() {
        //given : 테스트하기위한 변수 등 필요한 애들을 정의해놓는 부분.
        //when : 테스트할 메서드를 호출한다.
        //then : aseertThat 검증하는 로직.

        //given
        ReflectionTestUtils.setField(studentService, "mattermostUrl", "https://meeting.ssafy.com");

        // 생성자를 대신해서 명확하게 하기위해서.
        // 같은 타입의 파라미터를 받을 때 순서가 바뀔 수 있음.
        final ScoreRequest studentRequest = ScoreRequest.builder()
                .id("test@gmail.com")
                .build();
        final Student student = Student.builder()
                .userId("test_userId")
                .image("test.jpg")
                .username("test_name")
                .postCount(1)
                .reactingCount(2)
                .reactedCount(3)
                .point(6)
                .build();

        final List<Student> ranking = new ArrayList<Student>();
        ranking.add(student);

        doReturn(ranking).when(studentRepository).findAll();

        //when
        studentService.bestReaction(studentRequest);

        //then
        verify(studentRepository, times(1)).findAll();

    }

    @DisplayName("StudentService 클래스 / mostReaction 테스트")
    @Test
    void mostReaction() {

        final ScoreRequest scoreRequest = ScoreRequest.builder()
                .id("test@gmail.com")
                .build();
        final Student student = Student.builder()
                .userId("test_userId")
                .image("test.jpg")
                .username("test_name")
                .postCount(1)
                .reactingCount(2)
                .reactedCount(3)
                .point(6)
                .build();

        final List<Student> ranking = new ArrayList<Student>();
        ranking.add(student);

        doReturn(ranking).when(studentRepository).findAll();

        //when
        studentService.mostReaction(scoreRequest);

        //then
        verify(studentRepository, times(1)).findAll();

    }

    @DisplayName("StudentService 클래스 / bestStudents 테스트")
    @Test
    void bestStudents_test() {

        final ScoreRequest scoreRequest = ScoreRequest.builder()
                .id("test@gmail.com")
                .build();
        final Student student = Student.builder()
                .userId("test_userId")
                .image("test.jpg")
                .username("test_name")
                .postCount(1)
                .reactingCount(2)
                .reactedCount(3)
                .point(6)
                .build();
        final List<Student> ranking = new ArrayList<Student>();
        ranking.add(student);

        doReturn(ranking).when(studentRepository).findAll();

        //when
        studentService.mostReaction(scoreRequest);

        //then
        verify(studentRepository, times(1)).findAll();

    }

    @DisplayName("StudentService 클래스 / bestStudent 테스트")
    @Test
    void bestStudent_test() {

        final ScoreRequest scoreRequest = ScoreRequest.builder()
                .id("test@gmail.com")
                .build();
        final Student student = Student.builder()
                .userId("test_userId")
                .image("test.jpg")
                .username("test_name")
                .postCount(1)
                .reactingCount(2)
                .reactedCount(3)
                .point(6)
                .build();

        doReturn(Optional.of(student)).when(studentRepository).findById(any());

        //when
        studentService.mostReaction(scoreRequest);

        //then
        verify(studentRepository, times(1)).findById(any());

    }
}

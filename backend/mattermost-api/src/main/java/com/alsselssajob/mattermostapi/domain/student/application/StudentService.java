package com.alsselssajob.mattermostapi.domain.student.application;

import com.alsselssajob.mattermostapi.common.infra.MattermostUser;
import com.alsselssajob.mattermostapi.domain.student.domain.Student;
import com.alsselssajob.mattermostapi.domain.student.domain.StudentRepository;
import lombok.RequiredArgsConstructor;
import net.bis5.mattermost.model.Post;
import net.bis5.mattermost.model.PostMetadata;
import net.bis5.mattermost.model.Reaction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final static String IMAGE_ENCODING_FORMAT = "data:image/png;base64,";

    private final StudentRepository studentRepository;

    @Transactional
    public void updateStudentPoint(final MattermostUser mattermostUser) {
        final List<Post> posts = mattermostUser.getPostsForToday();
        final List<Student> students = new ArrayList<>();

        setPostAndReactingCounts(students, posts, mattermostUser);
        setReactedCounts(students, posts, mattermostUser);
        students.stream()
                .forEach(Student::calculatePoint);

        studentRepository.saveAll(students);
    }

    private void setPostAndReactingCounts(final List<Student> students, final List<Post> posts,
                                          final MattermostUser mattermostUser) {
        final Map<String, List<Post>> postsGroupByUserId = posts.stream()
                .collect(groupingBy(Post::getUserId));

        postsGroupByUserId.keySet()
                .forEach(userId -> {
                    final List<Post> postsGroup = postsGroupByUserId.get(userId);
                    final int reactedCount = (int) postsGroup.stream()
                            .map(Post::getMetadata)
                            .map(PostMetadata::getReactions)
                            .filter(Objects::nonNull)
                            .flatMap(Arrays::stream)
                            .count();

                    final Student student = studentRepository.findById(userId)
                            .orElse(Student.builder()
                                    .userId(userId)
                                    .image(getProfileImage(mattermostUser, userId))
                                    .build());
                    student.updatePostAndReactedCount(postsGroup.size(), reactedCount);
                    students.add(student);
                });
    }

    private String getProfileImage(MattermostUser mattermostUser, String userId) {
        return IMAGE_ENCODING_FORMAT.concat(Base64.getEncoder()
                .encodeToString(mattermostUser.client()
                        .getProfileImage(userId)
                        .readEntity()));
    }

    private void setReactedCounts(final List<Student> students, final List<Post> posts,
                                  final MattermostUser mattermostUser) {
        final Map<String, List<Reaction>> reactionsGroupByUserId = posts.stream()
                .map(Post::getMetadata)
                .map(PostMetadata::getReactions)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .collect(groupingBy(Reaction::getUserId));

        reactionsGroupByUserId.keySet()
                .forEach(userId -> {
                    final int reactingCount = reactionsGroupByUserId.get(userId).size();
                    final Student student = studentRepository.findById(userId)
                            .orElse(Student.builder()
                                    .userId(userId)
                                    .image(getProfileImage(mattermostUser, userId))
                                    .build());

                    if (students.contains(student)) {
                        final Student studentToUpdate = students.get(students.indexOf(student));
                        studentToUpdate.updateReactingCount(reactingCount);
                    } else {
                        student.updateReactingCount(reactingCount);
                        students.add(student);
                    }
                });
    }
}

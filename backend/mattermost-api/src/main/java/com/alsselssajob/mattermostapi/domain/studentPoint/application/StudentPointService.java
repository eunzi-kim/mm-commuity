package com.alsselssajob.mattermostapi.domain.studentPoint.application;

import com.alsselssajob.mattermostapi.common.infra.MattermostUser;
import com.alsselssajob.mattermostapi.domain.studentPoint.domain.StudentPoint;
import com.alsselssajob.mattermostapi.domain.studentPoint.domain.StudentPointRepository;
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
public class StudentPointService {

    private final static int INITIAL_COUNT = 0;

    private final StudentPointRepository studentPointRepository;

    @Transactional
    public void updateStudentPoint(final MattermostUser mattermostUser) {
        final List<Post> posts = mattermostUser.getPostsForToday();
        final List<StudentPoint> studentPoints = new ArrayList<>();

        setPostAndReactingCounts(studentPoints, posts);
        setReactedCounts(studentPoints, posts);
        studentPoints.stream()
                .forEach(StudentPoint::calculatePoint);

        studentPointRepository.saveAll(studentPoints);
    }

    private void setPostAndReactingCounts(final List<StudentPoint> studentPoints, final List<Post> posts) {
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
                    final StudentPoint studentPoint = StudentPoint.builder()
                            .userId(userId)
                            .postCount(postsGroup.size())
                            .reactedCount(reactedCount)
                            .reactingCount(INITIAL_COUNT)
                            .build();
                    studentPoints.add(studentPoint);
                });
    }

    private void setReactedCounts(final List<StudentPoint> studentPoints, final List<Post> posts) {
        final Map<String, List<Reaction>> reactionsGroupByUserId = posts.stream()
                .map(Post::getMetadata)
                .map(PostMetadata::getReactions)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .collect(groupingBy(Reaction::getUserId));

        reactionsGroupByUserId.keySet()
                .forEach(userId -> {
                    final int reactingCount = reactionsGroupByUserId.get(userId).size();
                    final StudentPoint studentPoint = StudentPoint.builder()
                            .userId(userId)
                            .postCount(INITIAL_COUNT)
                            .reactedCount(INITIAL_COUNT)
                            .reactingCount(reactingCount)
                            .build();

                    if (studentPoints.contains(studentPoint)) {
                        final StudentPoint studentPointToUpdate = studentPoints.get(studentPoints.indexOf(studentPoint));
                        studentPointToUpdate.updateReactingCount(reactingCount);
                    } else {
                        studentPoints.add(studentPoint);
                    }
                });
    }
}

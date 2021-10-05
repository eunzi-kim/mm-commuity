package com.alsselssajob.mattermostapi.domain.studentPoint.application;

import com.alsselssajob.mattermostapi.domain.post.ui.PostController;
import com.alsselssajob.mattermostapi.domain.studentPoint.domain.StudentPoint;
import lombok.RequiredArgsConstructor;
import net.bis5.mattermost.model.Post;
import net.bis5.mattermost.model.PostMetadata;
import net.bis5.mattermost.model.Reaction;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Component
@RequiredArgsConstructor
public class StudentPointService {

    private final static int INITIAL_COUNT = 0;

    private final PostController postController;

    public List<StudentPoint> updateStudentPoint() {
        final List<Post> posts = postController.getPosts();
        final List<StudentPoint> studentPoints = new ArrayList<>();

        addRequestsAboutPost(studentPoints, posts);
        addRequestsAboutReaction(studentPoints, posts);
        studentPoints.stream()
                .forEach(StudentPoint::calculatePoint);

        return studentPoints;
    }

    private void addRequestsAboutPost(final List<StudentPoint> studentPoints, final List<Post> posts) {
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

    private void addRequestsAboutReaction(final List<StudentPoint> studentPoints, final List<Post> posts) {
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

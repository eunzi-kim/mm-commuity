package com.alsselssajob.mattermostapi.domain.studentPoint.application;

import com.alsselssajob.mattermostapi.domain.post.ui.PostController;
import com.alsselssajob.mattermostapi.domain.studentPoint.dto.request.StudentPointUpdateRequest;
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

    private final PostController postController;

    public List<StudentPointUpdateRequest> updateStudentPoint() {
        final List<Post> posts = postController.getPosts();
        final List<StudentPointUpdateRequest> requests = new ArrayList<>();

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
                    final StudentPointUpdateRequest request = StudentPointUpdateRequest.builder()
                            .id(userId)
                            .postCountForUpdate(postsGroup.size())
                            .reactedCountForUpdate(reactedCount)
                            .reactingCountForUpdate(0)
                            .build();
                    requests.add(request);
                });

        final Map<String, List<Reaction>> reactionsGroupByUserId = posts.stream()
                .map(Post::getMetadata)
                .map(PostMetadata::getReactions)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .collect(groupingBy(Reaction::getUserId));

        reactionsGroupByUserId.keySet()
                .forEach(userId -> {
                    final int reactingCount = reactionsGroupByUserId.get(userId).size();
                    final StudentPointUpdateRequest request = StudentPointUpdateRequest.builder()
                            .id(userId)
                            .postCountForUpdate(0)
                            .reactedCountForUpdate(0)
                            .reactingCountForUpdate(reactingCount)
                            .build();

                    if (requests.contains(request)) {
                        final StudentPointUpdateRequest updatedRequest = requests.get(requests.indexOf(request));
                        updatedRequest.updateReactingCount(reactingCount);
                    } else {
                        requests.add(request);
                    }
                });

        return requests;
    }
}

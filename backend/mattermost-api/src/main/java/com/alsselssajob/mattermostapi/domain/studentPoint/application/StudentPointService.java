package com.alsselssajob.mattermostapi.domain.studentPoint.application;

import com.alsselssajob.mattermostapi.domain.post.ui.PostController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentPointService {

    private final PostController postController;
}

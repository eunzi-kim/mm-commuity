package com.alsselssajob.mattermostapi.domain.ssafycial.applicaion;

import com.alsselssajob.mattermostapi.domain.ssafycial.repository.SsafycialRepository;
import lombok.RequiredArgsConstructor;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.Post;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SsafycialService {

    private final SsafycialRepository ssafycialRepository;

    public void saveSsafycials(final MattermostClient client, final List<Post> ssafycials) throws IOException {
        ssafycialRepository.saveSsafycials(client, ssafycials);
    }
}

package com.alsselssajob.mattermostapi.domain.ssafycial.applicaion;

import com.alsselssajob.mattermostapi.domain.ssafycial.domain.Ssafycial;
import com.alsselssajob.mattermostapi.domain.ssafycial.repository.SsafycialRepository;
import lombok.RequiredArgsConstructor;
import net.bis5.mattermost.model.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SsafycialService {

    private final SsafycialRepository ssafycialRepository;

    public void saveSsafycials(final User user, final List<Ssafycial> ssafycials) throws IOException {
        ssafycialRepository.saveSsafycials(user, ssafycials);
    }
}

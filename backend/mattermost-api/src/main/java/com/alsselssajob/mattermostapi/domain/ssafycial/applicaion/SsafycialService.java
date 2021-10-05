package com.alsselssajob.mattermostapi.domain.ssafycial.applicaion;

import com.alsselssajob.mattermostapi.domain.ssafycial.repository.SsafycialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SsafycialService {

    private final SsafycialRepository ssafycialRepository;
}

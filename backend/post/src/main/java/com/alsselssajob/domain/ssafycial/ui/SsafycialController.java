package com.alsselssajob.ssafycial.ui;

import com.alsselssajob.ssafycial.application.SsafycialService;
import com.alsselssajob.ssafycial.dto.response.ResponseSsafycial;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class SsafycialController {

    private final SsafycialService ssafycialService;

    @GetMapping("/api/ssafycials")
    public List<ResponseSsafycial> getSsafycialPosts() throws IOException {
        return ssafycialService.getSsafycialPosts();
    }
}

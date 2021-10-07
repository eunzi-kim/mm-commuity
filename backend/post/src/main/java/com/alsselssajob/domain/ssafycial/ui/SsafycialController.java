package com.alsselssajob.domain.ssafycial.ui;

import com.alsselssajob.domain.ssafycial.application.SsafycialService;
import com.alsselssajob.domain.ssafycial.dto.response.ResponseSsafycial;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@RestController
public class SsafycialController {

    private final SsafycialService ssafycialService;

    @GetMapping("/api/ssafycials")
    public List<ResponseSsafycial> getSsafycialPosts() throws IOException {
        return ssafycialService.getSsafycialPosts();
    }
}

package com.alsselssajob.domain.scrap.ui;

import com.alsselssajob.domain.scrap.application.ScrapService;
import com.alsselssajob.domain.scrap.dto.request.RequestScrap;
import com.alsselssajob.domain.scrap.dto.response.ResponseScrap;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(originPatterns = "*")
@RestController
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapService scrapService;

    @PostMapping("/api/posts/scrap")
    public List<ResponseScrap> getScrapPosts(final @RequestBody RequestScrap requestScrap) {
        return scrapService.getScrapPosts(requestScrap);
    }
}

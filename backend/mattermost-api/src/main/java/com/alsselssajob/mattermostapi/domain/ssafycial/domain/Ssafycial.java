package com.alsselssajob.mattermostapi.domain.ssafycial.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class Ssafycial {

    private String title;
    private String link;

    @Builder
    public Ssafycial(final String title, final String link) {
        this.title = title;
        this.link = link;
    }

    public String title() {
        return title;
    }

    public String link() {
        return link;
    }
}

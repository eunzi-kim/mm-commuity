package com.alsselssajob.mattermostapi.domain.ssafycial.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class Ssafycial {

    private String id;
    private String title;
    private String link;

    @Builder
    public Ssafycial(final String id, final String title, final String link) {
        this.id = id;
        this.title = title;
        this.link = link;
    }

    public String id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String link() {
        return link;
    }
}

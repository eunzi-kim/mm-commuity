package com.alsselssajob.mattermostapi.domain.ssafycial.common;

import com.alsselssajob.mattermostapi.domain.ssafycial.domain.Ssafycial;
import net.bis5.mattermost.model.Post;
import org.springframework.stereotype.Component;

@Component
public class SsafycialUtil {

    private final static String HTTP_PREFIX = "http";
    private final static int TITLE_INDEX = 0;
    private final static int LINK_INDEX = 1;

    public static Ssafycial parsePostToSsafycial(final Post post) {
        final String[] titleAndLink = post.getMessage().split(HTTP_PREFIX);

        return Ssafycial.builder()
                .title(titleAndLink[TITLE_INDEX].trim())
                .link(HTTP_PREFIX.concat(titleAndLink[LINK_INDEX]).trim())
                .build();
    }
}

package com.alsselssajob.mattermostapi.domain.ssafycial.common;

import com.alsselssajob.mattermostapi.domain.ssafycial.domain.Ssafycial;
import net.bis5.mattermost.model.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

@Component
public class SsafycialUtil {

    private final static String HTTP_PREFIX = "http";
    private final static int TITLE_INDEX = 0;
    private final static int LINK_INDEX_WITHOUT_TITLE = 0;
    private final static int LINK_INDEX = 1;
    private final static int PAIR_SIZE = 2;

    public static List<Ssafycial> parsePostToSsafycial(final Post post) {
        final StringTokenizer messageTokenizer = new StringTokenizer(post.getMessage(), "\n");

        final List<Ssafycial> ssafycials = new ArrayList<>();
        Optional<String> title = Optional.empty();

        while (messageTokenizer.hasMoreTokens()) {
            final String message = messageTokenizer.nextToken();
            final String[] titleAndLink = message.startsWith(HTTP_PREFIX)
                    ? new String[]{message.split(HTTP_PREFIX)[LINK_INDEX]}
                    : message.split(HTTP_PREFIX);

            if (isPair(titleAndLink)) {
                ssafycials.add(Ssafycial.builder()
                        .title(titleAndLink[TITLE_INDEX].trim())
                        .link(HTTP_PREFIX.concat(titleAndLink[LINK_INDEX]).trim())
                        .build());
            } else if (title.isEmpty()) {
                title = Optional.of(titleAndLink[TITLE_INDEX].trim());
            } else if (title.isPresent()) {
                ssafycials.add(Ssafycial.builder()
                        .title(title.get())
                        .link(HTTP_PREFIX.concat(titleAndLink[LINK_INDEX_WITHOUT_TITLE]).trim())
                        .build());
                title = Optional.empty();
            }
        }

        return ssafycials;
    }

    private static boolean isPair(final String[] titleAndLink) {
        return titleAndLink.length == PAIR_SIZE;
    }
}

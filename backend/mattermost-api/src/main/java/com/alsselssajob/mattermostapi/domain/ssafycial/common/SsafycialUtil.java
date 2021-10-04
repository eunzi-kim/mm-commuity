package com.alsselssajob.mattermostapi.domain.ssafycial.common;

import net.bis5.mattermost.model.Post;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class SsafycialUtil {

    private final static String HTTP_PREFIX = "http";
    private final static String TITLE_KEY = "title";
    private final static String LINK_KEY = "link";

    private final static int TITLE_INDEX = 0;
    private final static int LINK_INDEX = 1;

    public static Map<String, String> parsePostToSsafycial(final Post post) {
        final Map<String, String> ssafycial = new HashMap<>();
        final String[] titleAndLink = post.getMessage().split(HTTP_PREFIX);

        ssafycial.put(TITLE_KEY, titleAndLink[TITLE_INDEX].trim());
        ssafycial.put(LINK_KEY, HTTP_PREFIX.concat(titleAndLink[LINK_INDEX]).trim());

        return ssafycial;
    }
}

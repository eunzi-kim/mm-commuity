package com.alsselssajob.mattermostapi.domain.mattermostuser.domain;

import com.alsselssajob.mattermostapi.domain.ssafycial.common.SsafycialUtil;
import com.alsselssajob.mattermostapi.domain.ssafycial.domain.Ssafycial;
import lombok.Builder;
import lombok.NoArgsConstructor;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
@NoArgsConstructor
public class MattermostUser {

    private final static int SSAFYCIAL_CHANNEL_INDEX = 0;
    private final static int ONE_DAY = 1;
    private final static long TWO_WEEKS = 2;

    @Value("${presscorps.team.id}")
    private String pressCorpsTeamId;

    @Value("${ssafycial.channel.id}")
    private String ssafycialChannelId;

    private MattermostClient client;
    private User user;

    @Builder
    public MattermostUser(final MattermostClient client, final User user) {
        this.client = client;
        this.user = user;
    }

    public List<Post> getPostsForToday() {
        return getTeamsForUser().stream()
                .map(this::getPublicChannelsForTeam)
                .flatMap(List::stream)
                .map(this::getPostsForChannelSinceYesterday)
                .map(PostList::getPosts)
                .filter(Objects::nonNull)
                .map(Map::values)
                .flatMap(Collection::stream)
                .collect(toList());
    }

    private List<Team> getTeamsForUser() {
        return client.getTeamsForUser(user.getId()).readEntity();
    }

    private List<Channel> getPublicChannelsForTeam(final Team team) {
        return client.getPublicChannelsForTeam(team.getId()).readEntity();
    }

    private PostList getPostsForChannelSinceYesterday(final Channel channel) {
        return client.getPostsSince(channel.getId(), LocalDateTime.now()
                .minusDays(ONE_DAY)
                .atZone(ZoneId.systemDefault()))
                .readEntity();
    }

    public List<Ssafycial> getSsafycialsForLastTwoWeeks() {
        return Stream.of(getSsafycialsForChannelSinceLastTwoWeeks(getSsafycialChannel()))
                .map(PostList::getPosts)
                .filter(Objects::nonNull)
                .map(Map::values)
                .flatMap(Collection::stream)
                .map(SsafycialUtil::parsePostToSsafycial)
                .flatMap(List::stream)
                .collect(toList());
    }

    private Channel getSsafycialChannel() {
        return client.getPublicChannelsByIdsForTeam(pressCorpsTeamId, ssafycialChannelId)
                .readEntity()
                .get(SSAFYCIAL_CHANNEL_INDEX);
    }

    private PostList getSsafycialsForChannelSinceLastTwoWeeks(final Channel channel) {
        return client.getPostsSince(channel.getId(), LocalDateTime.now()
                .minusWeeks(TWO_WEEKS)
                .atZone(ZoneId.systemDefault()))
                .readEntity();
    }
}

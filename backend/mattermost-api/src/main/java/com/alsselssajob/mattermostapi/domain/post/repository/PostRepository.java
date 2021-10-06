package com.alsselssajob.mattermostapi.domain.post.repository;

import com.alsselssajob.mattermostapi.common.vo.ColumnFamily;
import com.alsselssajob.mattermostapi.common.vo.qualifier.*;
import net.bis5.mattermost.model.FileInfo;
import net.bis5.mattermost.model.Post;
import net.bis5.mattermost.model.Reaction;
import net.bis5.mattermost.model.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class PostRepository {

    private final static TableName POST_TABLE_NAME = TableName.valueOf("posts");
    private final static String CONFIGURATION_FILE_PATH = "/usr/local/hbase-2.3.6/conf/hbase-site.xml";
    private final static String EMPTY = "";
    private final static String DASH = "-";
    private final static String SPACE = " ";
    private final static String COLON = ":";

    private final Configuration configuration;

    public PostRepository() {
        configuration = HBaseConfiguration.create();
        configuration.addResource(CONFIGURATION_FILE_PATH);
    }

    public void savePosts(final User user, final Map<String, List<Map<String, List<Post>>>> teams) throws IOException {
        final Connection connection = ConnectionFactory.createConnection(configuration);
        final Admin admin = connection.getAdmin();

        createTableIfNotExists(admin);

        final Table postTable = connection.getTable(POST_TABLE_NAME);
        teams.keySet()
                .forEach(teamName ->
                        teams.get(teamName)
                                .stream()
                                .forEach(channels ->
                                        channels.keySet()
                                                .forEach(channelName ->
                                                        channels.get(channelName)
                                                                .stream()
                                                                .forEach(post -> {
                                                                    final Put row = new Put(post.getId().getBytes());

                                                                    addTeamColumnFamily(teamName, row);
                                                                    addChannelColumnFamily(channelName, row);
                                                                    addPostColumnFamily(post, row);
                                                                    addUserColumnFamily(user, row);
                                                                    addEmojiColumnFamily(post.getMetadata().getReactions(), row);
                                                                    addFileColumnFamily(post.getMetadata().getFiles(), row);

                                                                    try {
                                                                        postTable.put(row);
                                                                    } catch (IOException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                })
                                                )
                                )
                );

        connection.close();
    }

    private void createTableIfNotExists(final Admin admin) throws IOException {
        if (!admin.tableExists(POST_TABLE_NAME)) {
            final TableDescriptorBuilder table = TableDescriptorBuilder.newBuilder(POST_TABLE_NAME);

            table.setColumnFamily(ColumnFamilyDescriptorBuilder.of(ColumnFamily.team.name()));
            table.setColumnFamily(ColumnFamilyDescriptorBuilder.of(ColumnFamily.channel.name()));
            table.setColumnFamily(ColumnFamilyDescriptorBuilder.of(ColumnFamily.post.name()));
            table.setColumnFamily(ColumnFamilyDescriptorBuilder.of(ColumnFamily.user.name()));
            table.setColumnFamily(ColumnFamilyDescriptorBuilder.of(ColumnFamily.emoji.name()));
            table.setColumnFamily(ColumnFamilyDescriptorBuilder.of(ColumnFamily.file.name()));
            table.setColumnFamily(ColumnFamilyDescriptorBuilder.of(ColumnFamily.team.name()));
            table.setColumnFamily(ColumnFamilyDescriptorBuilder.of(ColumnFamily.channel.name()));

            admin.createTable(table.build());
        }
    }

    private void addChannelColumnFamily(final String channelName, Put row) {
        row.addColumn(ColumnFamily.channel.name().getBytes(),
                ChannelQualifier.channel_name.name().getBytes(),
                channelName.getBytes());
    }

    private void addTeamColumnFamily(final String teamName, final Put row) {
        row.addColumn(ColumnFamily.team.name().getBytes(),
                TeamQualifier.team_name.name().getBytes(),
                teamName.getBytes());
    }

    private void addPostColumnFamily(final Post post, final Put row) {
        row.addColumn(ColumnFamily.post.name().getBytes(),
                PostQualifier.channel_id.name().getBytes(),
                post.getChannelId().getBytes());
        row.addColumn(ColumnFamily.post.name().getBytes(),
                PostQualifier.root_id.name().getBytes(),
                post.getRootId().getBytes());
        row.addColumn(ColumnFamily.post.name().getBytes(),
                PostQualifier.created_at.name().getBytes(),
                Bytes.toBytes(post.getCreateAt()));
        row.addColumn(ColumnFamily.post.name().getBytes(),
                PostQualifier.created_date.name().getBytes(),
                calculateCreatedDate(post).getBytes());
        row.addColumn(ColumnFamily.post.name().getBytes(),
                PostQualifier.parent_id.name().getBytes(),
                post.getParentId().getBytes());
        row.addColumn(ColumnFamily.post.name().getBytes(),
                PostQualifier.message.name().getBytes(),
                post.getMessage().getBytes());
        row.addColumn(ColumnFamily.post.name().getBytes(),
                PostQualifier.is_scrapped.name().getBytes(),
                Bytes.toBytes(false));
        row.addColumn(ColumnFamily.post.name().getBytes(),
                PostQualifier.hashTag.name().getBytes(),
                post.getHashtags().getBytes());
    }

    private String calculateCreatedDate(final Post post) {
        final LocalDateTime createdDate = Instant.ofEpochMilli(post.getCreateAt())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return new StringBuilder().append(createdDate.getYear())
                .append(DASH)
                .append(createdDate.getMonthValue())
                .append(DASH)
                .append(createdDate.getDayOfMonth())
                .append(SPACE)
                .append(createdDate.getHour())
                .append(COLON)
                .append(createdDate.getMinute())
                .append(COLON)
                .append(createdDate.getSecond())
                .toString();
    }

    private void addUserColumnFamily(final User user, final Put row) {
        row.addColumn(ColumnFamily.user.name().getBytes(),
                UserQualifier.user_id.name().getBytes(),
                user.getId().getBytes());
        row.addColumn(ColumnFamily.user.name().getBytes(),
                UserQualifier.username.name().getBytes(),
                user.getUsername().getBytes());
        row.addColumn(ColumnFamily.user.name().getBytes(),
                UserQualifier.nickname.name().getBytes(),
                user.getNickname().getBytes());
    }

    private void addEmojiColumnFamily(final Reaction[] reactions, final Put row) {
        if (Objects.nonNull(reactions)) {
            Arrays.stream(reactions)
                    .forEach(reaction -> row.addColumn(ColumnFamily.emoji.name().getBytes(),
                            EmojiQualifier.name.name().getBytes(),
                            reaction.getEmojiName().getBytes())
                    );
        } else {
            row.addColumn(ColumnFamily.emoji.name().getBytes(),
                    EmojiQualifier.name.name().getBytes(),
                    EMPTY.getBytes());
        }
    }

    private void addFileColumnFamily(FileInfo[] files, Put row) {
        if (Objects.nonNull(files)) {
            Arrays.stream(files)
                    .forEach(file -> {
                        row.addColumn(ColumnFamily.file.name().getBytes(),
                                FileQualifier.file_id.name().getBytes(),
                                file.getId().getBytes());
                        row.addColumn(ColumnFamily.file.name().getBytes(),
                                FileQualifier.name.name().getBytes(),
                                file.getName().getBytes());
                        row.addColumn(ColumnFamily.file.name().getBytes(),
                                FileQualifier.extension.name().getBytes(),
                                file.getExtension().getBytes());
                    });
        } else {
            row.addColumn(ColumnFamily.file.name().getBytes(),
                    FileQualifier.file_id.name().getBytes(),
                    EMPTY.getBytes());
            row.addColumn(ColumnFamily.file.name().getBytes(),
                    FileQualifier.name.name().getBytes(),
                    EMPTY.getBytes());
            row.addColumn(ColumnFamily.file.name().getBytes(),
                    FileQualifier.extension.name().getBytes(),
                    EMPTY.getBytes());
        }
    }
}

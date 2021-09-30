package com.alsselssajob.mattermostapi.post.repository;

import com.alsselssajob.mattermostapi.mattermostuser.domain.MattermostUser;
import com.alsselssajob.mattermostapi.post.domain.vo.ColumnFamily;
import com.alsselssajob.mattermostapi.post.domain.vo.qualifier.EmojiQualifier;
import com.alsselssajob.mattermostapi.post.domain.vo.qualifier.FileQualifier;
import com.alsselssajob.mattermostapi.post.domain.vo.qualifier.PostQualifier;
import com.alsselssajob.mattermostapi.post.domain.vo.qualifier.UserQualifier;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.FileInfo;
import net.bis5.mattermost.model.Post;
import net.bis5.mattermost.model.Reaction;
import net.bis5.mattermost.model.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class PostRepository {

    private final static String CONFIGURATION_FILE_PATH = "/usr/local/hbase-2.3.6/conf/hbase-site.xml";
    private final static TableName POST_TABLE_NAME = TableName.valueOf("posts");
    private final static String POST_COLUMN_FAMILY_NAME = "post";
    private final static String USER_COLUMN_FAMILY_NAME = "user";
    private final static String EMOJI_COLUMN_FAMILY_NAME = "emoji";
    private final static String FILE_COLUMN_FAMILY_NAME = "file";
    private final static String EMPTY = "";

    private final Configuration configuration;

    public PostRepository() throws IOException {
        configuration = HBaseConfiguration.create();
        configuration.addResource(CONFIGURATION_FILE_PATH);
    }

    public static void main(String[] args) throws IOException {
        PostRepository postRepository = new PostRepository();
        MattermostClient client = MattermostClient.builder()
                .url("https://meeting.ssafy.com")
                .logLevel(Level.INFO)
                .ignoreUnknownProperties()
                .build();
        User user = client.login("kskyu610@gmail.com", "Skskyu610@gmail.com5").readEntity();

        MattermostUser mattermostUser = new MattermostUser(client, user);
        List<Post> temp = mattermostUser.getPostsForToday();
        List<Post> posts = temp.subList(0, 1);
        postRepository.savePosts(user, posts);
    }

    public void savePosts(final User user, final List<Post> posts) throws IOException {
        final Connection connection = ConnectionFactory.createConnection(configuration);
        final Admin admin = connection.getAdmin();

        createTableIfNotExists(admin);

        final Table postTable = connection.getTable(POST_TABLE_NAME);
        posts.stream()
                .forEach(post -> {
                    final Put row = new Put(post.getId().getBytes());

                    addPostColumnFamily(post, row);
                    addUserColumnFamily(user, row);
                    addEmojiColumnFamily(post.getMetadata().getReactions(), row);
                    addFileColumnFamily(post.getMetadata().getFiles(), row);

                    try {
                        postTable.put(row);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        connection.close();
    }

    private void createTableIfNotExists(final Admin admin) throws IOException {
        if (!admin.tableExists(POST_TABLE_NAME)) {
            final TableDescriptorBuilder table = TableDescriptorBuilder.newBuilder(POST_TABLE_NAME);

            table.setColumnFamily(ColumnFamilyDescriptorBuilder.of(POST_COLUMN_FAMILY_NAME));
            table.setColumnFamily(ColumnFamilyDescriptorBuilder.of(USER_COLUMN_FAMILY_NAME));
            table.setColumnFamily(ColumnFamilyDescriptorBuilder.of(EMOJI_COLUMN_FAMILY_NAME));
            table.setColumnFamily(ColumnFamilyDescriptorBuilder.of(FILE_COLUMN_FAMILY_NAME));

            admin.createTable(table.build());
        }
    }

    private void addPostColumnFamily(final Post post, final Put row) {
        row.addColumn(ColumnFamily.post.name().getBytes(),
                PostQualifier.channel_id.name().getBytes(),
                post.getChannelId().getBytes());
        row.addColumn(ColumnFamily.post.name().getBytes(),
                PostQualifier.root_id.name().getBytes(),
                post.getRootId().getBytes());
        row.addColumn(ColumnFamily.post.name().getBytes(),
                PostQualifier.parent_id.name().getBytes(),
                post.getParentId().getBytes());
        row.addColumn(ColumnFamily.post.name().getBytes(),
                PostQualifier.message.name().getBytes(),
                post.getMessage().getBytes());
        row.addColumn(ColumnFamily.post.name().getBytes(),
                PostQualifier.hashTag.name().getBytes(),
                post.getHashtags().getBytes());
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

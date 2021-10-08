package com.alsselssajob.domain.post.application;

import com.alsselssajob.domain.post.dto.response.ResponsePost;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

@Service
public class PostService {
    private final static String CONF_URL = "/usr/local/hbase-2.3.6/conf/hbase-site.xml";
    private final static TableName TABLE_NAME = TableName.valueOf("posts");

    private final Configuration configuration;

    public PostService() {
        configuration = HBaseConfiguration.create();
        configuration.addResource(CONF_URL);
    }

    @Resource(name = "redisPostTemplate")
    RedisTemplate<String, String> redisPostTemplate;


    public String getProfileImage(String userId) {
        MattermostClient client = MattermostClient.builder()
                .url("https://meeting.ssafy.com")
                .logLevel(Level.INFO)
                .ignoreUnknownProperties()
                .build();

        User user = client.login("kskyu610@gmail.com", "Skskyu610@gmail.com5").readEntity();
        String encoded = "data:image/png;base64," + Base64.getEncoder().encodeToString(client.getProfileImage(userId).readEntity());
        return encoded;
    }



    public List<ResponsePost> getPostsWithRedis(String todayDate) throws IOException {

        // hbase
        final Connection connection = ConnectionFactory.createConnection();
        final Table table = connection.getTable(TABLE_NAME);
        ResultScanner results = table.getScanner(new Scan());

        byte[] posts = Bytes.toBytes("post");
        byte[] teams = Bytes.toBytes("team");
        byte[] channels = Bytes.toBytes("channel");
        byte[] users = Bytes.toBytes("user");
        byte[] emojis = Bytes.toBytes("emoji");
        byte[] files = Bytes.toBytes("file");

        List<ResponsePost> responsePostList = new ArrayList<>();
        SetOperations<String, String> setOperations = redisPostTemplate.opsForSet();

        // check key in redis
        ValueOperations<String, String> stringValueOperations = redisPostTemplate.opsForValue();
        if (stringValueOperations.get(todayDate) == null) {
            stringValueOperations.set(todayDate, "true");
            // put in redis
            for (Result result : results) {
                setOperations.add(
                        new String(result.getValue(posts, Bytes.toBytes("created_date"))),
                        "post_id: " + new String(result.getRow()),
                        "channel_id: " + new String(result.getValue(posts, Bytes.toBytes("channel_id"))),
                        "channel_name: " + new String(result.getValue(channels, Bytes.toBytes("channel_name"))),
                        "team_name: " + new String(result.getValue(teams, Bytes.toBytes("team_name"))),
                        "is_scrapped: " + new String(result.getValue(posts, Bytes.toBytes("is_scrapped"))),
                        "root_id: " + new String(result.getValue(posts, Bytes.toBytes("root_id"))),
                        "parent_id: " + new String(result.getValue(posts, Bytes.toBytes("parent_id"))),
                        "message: " + new String(result.getValue(posts, Bytes.toBytes("message"))),
                        "hashTag: " + new String(result.getValue(posts, Bytes.toBytes("hashTag"))),
                        "created_at: " + new String(result.getValue(posts, Bytes.toBytes("created_at"))),
                        "user_id: " + new String(result.getValue(users, Bytes.toBytes("user_id"))),
                        "username: " + new String(result.getValue(users, Bytes.toBytes("username"))),
                        "nickname: " + new String(result.getValue(users, Bytes.toBytes("nickname"))),
                        "emoji_name: " + new String(result.getValue(emojis, Bytes.toBytes("name"))),
                        "file_id: " + new String(result.getValue(files, Bytes.toBytes("file_id"))),
                        "file_name: " + new String(result.getValue(files, Bytes.toBytes("name"))),
                        "extension: " + new String(result.getValue(files, Bytes.toBytes("extension")))
                );
                System.out.println(new String(result.getRow()));
            }
        }
        RedisConnection redisConnection = redisPostTemplate.getConnectionFactory().getConnection();;

        // bring from redis
        String find_date = todayDate + "*";
        ScanOptions options = ScanOptions.scanOptions().match(find_date).count(30).build();
        Cursor<byte[]> cursor = redisConnection.scan(options);

        redisConnection.scan(options);

        List<String> today_post_keys = new ArrayList<>();
        while (cursor.hasNext()) {
            today_post_keys.add(new String(cursor.next()));
        }

        for (String s : today_post_keys) {
            if (s.length() > 9) {
                Set<String> members = setOperations.members(s);
                ResponsePost responsePost = new ResponsePost();
                String userId = "";
                for (String member : members) {
                    String[] result = member.split(":");
                    if (result[0].equals("created_date")) {
                        responsePost.setCreatedDate(result[1]);
                    } else if (result[0].equals("post_id")) {
                        responsePost.setPostId(result[1]);
                    } else if (result[0].equals("channel_id")) {
                        responsePost.setChannelId(result[1]);
                    } else if (result[0].equals("channel_name")) {
                        responsePost.setChannelName(result[1]);
                    } else if (result[0].equals("team_name")) {
                        responsePost.setTeamName(result[1]);
                    } else if (result[0].equals("is_scrapped")) {
                        responsePost.setIsScrapped(result[1]);
                    } else if (result[0].equals("root_id")) {
                        responsePost.setRootId(result[1]);
                    } else if (result[0].equals("parent_id")) {
                        responsePost.setParentId(result[1]);
                    } else if (result[0].equals("message")) {
                        responsePost.setMessage(result[1]);
                    } else if (result[0].equals("hashTag")) {
                        responsePost.setHashTag(result[1]);
                    } else if (result[0].equals("created_at")) {
                        responsePost.setCreatedAt(result[1]);
                    } else if (result[0].equals("user_id")) {
                        userId = result[0];
                        responsePost.setUserId(result[1]);
                    } else if (result[0].equals("username")) {
                        responsePost.setUsername(result[1]);
                    } else if (result[0].equals("nickname")) {
                        responsePost.setNickname(result[1]);
                    } else if (result[0].equals("emoji_name")) {
                        responsePost.setEmojiName(result[1]);
                    } else if (result[0].equals("file_id")) {
                        responsePost.setFileId(result[1]);
                    } else if (result[0].equals("file_name")) {
                        responsePost.setFileName(result[1]);
                    } else if (result[0].equals("file_extension")) {
                        responsePost.setFileExtension(result[1]);
                    }
                }
                responsePost.setProfileImg(getProfileImage(userId));
                responsePostList.add(responsePost);
            }
        }
        return responsePostList;
    }
}



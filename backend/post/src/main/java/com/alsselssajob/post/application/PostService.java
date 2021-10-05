package com.alsselssajob.post.application;

import com.alsselssajob.post.dto.response.ResponsePost;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
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

    //    작성해놓은 redisPostTemplate 주입
    @Resource(name = "redisPostTemplate")
    RedisTemplate<String, String> redisPostTemplate;


    public boolean keyCheckInRedis(String key) {
        return redisPostTemplate.hasKey(key);
    }

    public List<ResponsePost> getPosts(String today_date) throws IOException {

        List<ResponsePost> responsePostList = new ArrayList<>();
        final Connection connection = ConnectionFactory.createConnection();
        final Table table = connection.getTable(TABLE_NAME);

        byte[] posts = Bytes.toBytes("post");
        byte[] users = Bytes.toBytes("user");
        byte[] emojis = Bytes.toBytes("emoji");
        byte[] files = Bytes.toBytes("file");
        byte[] created_date = Bytes.toBytes("created_date");

        ResultScanner results = table.getScanner(new Scan());

        // row
        for (Result result : results) {

            if (new String(result.getValue(posts, created_date)).contains(today_date)) {
                ResponsePost responsePost = ResponsePost.builder()
                        .post_id(new String(result.getRow()))
                        .channel_id(new String(result.getValue(posts, Bytes.toBytes("channel_id"))))
                        .root_id(new String(result.getValue(posts, Bytes.toBytes("root_id"))))
                        .parent_id(new String(result.getValue(posts, Bytes.toBytes("parent_id"))))
                        .message(new String(result.getValue(posts, Bytes.toBytes("message"))))
                        .hashTag(new String(result.getValue(posts, Bytes.toBytes("hashTag"))))
                        .created_at(new String(result.getValue(posts, Bytes.toBytes("created_at"))))
                        .created_date(new String(result.getValue(posts, Bytes.toBytes("created_date"))))
                        .user_id(new String(result.getValue(users, Bytes.toBytes("user_id"))))
                        .username(new String(result.getValue(users, Bytes.toBytes("username"))))
                        .nickname(new String(result.getValue(users, Bytes.toBytes("nickname"))))
                        .profile_img(getProfileImage(new String(result.getValue(users, Bytes.toBytes("user_id")))))
                        .emoji_name(new String(result.getValue(emojis, Bytes.toBytes("name"))))
                        .file_id(new String(result.getValue(files, Bytes.toBytes("file_id"))))
                        .file_name(new String(result.getValue(files, Bytes.toBytes("name"))))
                        .file_extension(new String(result.getValue(files, Bytes.toBytes("extension"))))
                        .build();
                responsePostList.add(responsePost);
            }
        }
        return responsePostList;
    }

    public String getProfileImage(String user_id) {
        MattermostClient client = MattermostClient.builder()
                .url("https://meeting.ssafy.com")
                .logLevel(Level.INFO)
                .ignoreUnknownProperties()
                .build();

        User user = client.login("kskyu610@gmail.com", "Skskyu610@gmail.com5").readEntity();
        String encoded = "data:image/png;base64," + Base64.getEncoder().encodeToString(client.getProfileImage(user_id).readEntity());
        return encoded;
    }








    public void getPostsWithRedis(String today_date) throws IOException {

        final Connection connection = ConnectionFactory.createConnection();
        final Table table = connection.getTable(TABLE_NAME);
        ResultScanner results = table.getScanner(new Scan());

        byte[] posts = Bytes.toBytes("post");
        byte[] users = Bytes.toBytes("user");
        byte[] emojis = Bytes.toBytes("emoji");
        byte[] files = Bytes.toBytes("file");
        byte[] created_date = Bytes.toBytes("created_date");


        List<ResponsePost> responsePostList = new ArrayList<>();
        SetOperations<String, String> setOperations = redisPostTemplate.opsForSet();


        // not in redis
        if (!keyCheckInRedis(today_date)) {
            for (Result result : results) {
                setOperations.add(
                        today_date,
                        new String(result.getRow()),
                        new String(result.getValue(posts, Bytes.toBytes("channel_id"))),
                        new String(result.getValue(posts, Bytes.toBytes("root_id"))),
                        new String(result.getValue(posts, Bytes.toBytes("parent_id"))),
                        new String(result.getValue(posts, Bytes.toBytes("message"))),
                        new String(result.getValue(posts, Bytes.toBytes("hashTag"))),
                        new String(result.getValue(posts, Bytes.toBytes("created_at"))),
                        new String(result.getValue(posts, Bytes.toBytes("created_date"))),
                        new String(result.getValue(users, Bytes.toBytes("user_id"))),
                        new String(result.getValue(users, Bytes.toBytes("username"))),
                        new String(result.getValue(users, Bytes.toBytes("nickname"))),
                        new String(result.getValue(emojis, Bytes.toBytes("name"))),
                        new String(result.getValue(files, Bytes.toBytes("file_id"))),
                        new String(result.getValue(files, Bytes.toBytes("name"))),
                        new String(result.getValue(files, Bytes.toBytes("extension")))
                );

            }
        }
        Set<String> today_posts = setOperations.members(today_date);


        for (String post : today_posts) {
            System.out.println(post);
        }


        return;
    }
}



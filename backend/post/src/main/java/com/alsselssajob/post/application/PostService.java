package com.alsselssajob.post.application;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final static String CONF_URL = "/usr/local/hbase-2.3.6/conf/hbase-site.xml";
    private final static TableName TABLE_NAME = TableName.valueOf("postData");

    private final Configuration configuration;

    public PostService() {
        configuration = HBaseConfiguration.create();
        configuration.addResource(CONF_URL);
    }
//    작성해놓은 redisPostTemplate 주입
    @Resource(name="redisPostTemplate")
    RedisTemplate<String, String> redisPostTemplate;

//    게시글 조회
    public void setPost() {
//        날짜 해당하는 게시물이 redis에 있는지 확인 (key로 확인)
//        redis에 있으면 가져와서 리턴
//        없으면 HBase에서 가져온 뒤, redis에 넣고 리턴
        ListOperations<String, String> stringStringListOperations = redisPostTemplate.opsForList();
        stringStringListOperations.rightPushAll("post2", "date", "post_id", "user_id");
        stringStringListOperations.rightPushAll("post2", "date2", "post_id2", "user_id2");

    }

    public void setPostWithHBase() throws IOException {
        final Connection connection = ConnectionFactory.createConnection();
        final Table table = connection.getTable(TABLE_NAME);
        List<Get> gets = new ArrayList<Get>(); //Get 인스턴스들을 담을 List를 준비한다.

        byte[] posts = Bytes.toBytes("post");
        byte[] dates = Bytes.toBytes("date");
        byte[] message = Bytes.toBytes("message");
        byte[] users = Bytes.toBytes("user");
        byte[] files = Bytes.toBytes("file");
        byte[] emojis = Bytes.toBytes("emoji");
        byte[] row1 = Bytes.toBytes("1");
        byte[] row2 = Bytes.toBytes("2");


//        Get get1 = new Get(row1); //Get 인스턴스를 추가해준다.
////        get1.addFamily(posts, users, files, emojis);
//        get1.addFamily(posts);
//        gets.add(get1);
//        Result[] results = table.get(gets);

        ResultScanner results = table.getScanner(new Scan());
        String hbaseRow1 = "1";
        String date = "1001";
        for(Result result:results){
            if (new String(result.getValue(posts, dates)).equals(date)) {

                for(Cell cell:result.rawCells()){
//                    System.out.println("getFamilyArray: " + new String(cell.getFamilyArray()));
//                    System.out.println("getQualifierArray: " + new String(cell.getQualifierArray()));
                    System.out.println("getValueArray: " + new String(cell.getValueArray()));
                    System.out.println("getValueArray: " + new String(cell.getValueArray()).trim());
//                           1postdate  |<,�;0930
                }

            }
//            if (new String(result.getRow()).equals(hbaseRow1)){
//
//                for(Cell cell:result.listCells()){
//                    System.out.println("cell: " + cell);
//                    System.out.println("getRowArray: " + new String(cell.getRowArray()));
//                    System.out.println("getFamilyArray: " + new String(cell.getFamilyArray()));
//                    System.out.println("getValueArray: " + new String(cell.getValueArray()));
////                           1postdate  |<,�;0930
//                }
//            }
//                columnList.add(
//                        new String(keyValue.getFamily()) + ":" +
//                                new String(keyValue.getQualifier())
//                );
            }

    }
}

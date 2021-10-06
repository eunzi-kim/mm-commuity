package com.alsselssajob.domain.ssafycial.application;

import com.alsselssajob.domain.ssafycial.dto.response.ResponseSsafycial;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SsafycialService {
    private final static TableName SSAFYCIAL_TABLE_NAME = TableName.valueOf("ssafycials");
    private final static String CONFIGURATION_FILE_PATH = "/usr/local/hbase-2.3.6/conf/hbase-site.xml";

    private final Configuration configuration;

    public SsafycialService() {
        configuration = HBaseConfiguration.create();
        configuration.addResource(CONFIGURATION_FILE_PATH);
    }

    public List<ResponseSsafycial> getSsafycialPosts() throws IOException {

        List<ResponseSsafycial> responseSsafycialList = new ArrayList<>();
        final Connection connection = ConnectionFactory.createConnection();
        final Table table = connection.getTable(SSAFYCIAL_TABLE_NAME);

        byte[] posts = Bytes.toBytes("ssafycial");
        byte[] users = Bytes.toBytes("user");

        ResultScanner results = table.getScanner(new Scan());

        // row
        for (Result result : results) {
            ResponseSsafycial responseSsafycial = ResponseSsafycial.builder()
                    .ssafycial_id(new String(result.getRow()))
                    .title(new String(result.getValue(posts, Bytes.toBytes("title"))))
                    .link(new String(result.getValue(posts, Bytes.toBytes("link"))))
                    .user_id(new String(result.getValue(users, Bytes.toBytes("user_id"))))
                    .username(new String(result.getValue(users, Bytes.toBytes("username"))))
                    .nickname(new String(result.getValue(users, Bytes.toBytes("nickname"))))
                    .build();
            responseSsafycialList.add(responseSsafycial);

        }
        return responseSsafycialList;
    }
}

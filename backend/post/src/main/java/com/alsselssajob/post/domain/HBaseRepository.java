package com.alsselssajob.post.domain;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HBaseRepository {

    private final static String CONF_URL = "/usr/local/hbase-2.3.6/conf/hbase-site.xml";
    private final static TableName TABLE_NAME = TableName.valueOf("mj2");

    private final Configuration configuration;

    public HBaseRepository() {
        configuration = HBaseConfiguration.create();
        configuration.addResource(CONF_URL);
    }

    public static void main(String[] args) throws IOException {
        HBaseRepository postRepository = new HBaseRepository();
        postRepository.relateHBase();
    }



    public void relateHBase() throws IOException {
        final Connection connection = ConnectionFactory.createConnection();
        Admin admin = connection.getAdmin();

//        create table
//        final TableDescriptorBuilder table = TableDescriptorBuilder.newBuilder(TABLE_NAME);
//        table.setColumnFamily(ColumnFamilyDescriptorBuilder.of("myInfo"));
//        admin.createTable(table.build());

//        put data
        Put put = new Put(Bytes.toBytes("row1"));
        final Table table = connection.getTable(TABLE_NAME);
        put.addColumn(Bytes.toBytes("myInfo"),Bytes.toBytes("age"),Bytes.toBytes("26"));
        put.addColumn(Bytes.toBytes("myInfo"),Bytes.toBytes("loc"),Bytes.toBytes("Yongin"));
        table.put(put);

        connection.close();


    }
}

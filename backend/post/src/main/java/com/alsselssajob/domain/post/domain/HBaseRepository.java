package com.alsselssajob.domain.post.domain;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HBaseRepository {

    private final static String CONF_URL = "/usr/local/hbase-2.3.6/conf/hbase-site.xml";
    private final static TableName TABLE_NAME = TableName.valueOf("postData");

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
//        table.setColumnFamily(ColumnFamilyDescriptorBuilder.of("post"));
//        table.setColumnFamily(ColumnFamilyDescriptorBuilder.of("user"));
//        table.setColumnFamily(ColumnFamilyDescriptorBuilder.of("emoji"));
//        table.setColumnFamily(ColumnFamilyDescriptorBuilder.of("file"));
//        admin.createTable(table.build());

//        put data
//        Put put = new Put(Bytes.toBytes("1"));
//        final Table table = connection.getTable(TABLE_NAME);
//        put.addColumn(Bytes.toBytes("post"),Bytes.toBytes("chnnel_id"),Bytes.toBytes("1"));
//        put.addColumn(Bytes.toBytes("post"),Bytes.toBytes("root_id"), Bytes.toBytes("1"));
//        put.addColumn(Bytes.toBytes("post"),Bytes.toBytes("parent_id"),Bytes.toBytes("1"));
//        put.addColumn(Bytes.toBytes("post"),Bytes.toBytes("message"),Bytes.toBytes("hi1"));
//        put.addColumn(Bytes.toBytes("post"),Bytes.toBytes("hashtag"), Bytes.toBytes("hi1"));
//        put.addColumn(Bytes.toBytes("post"),Bytes.toBytes("date"),Bytes.toBytes("0930"));
//
//        put.addColumn(Bytes.toBytes("user"),Bytes.toBytes("user_id"),Bytes.toBytes("1"));
//        put.addColumn(Bytes.toBytes("user"),Bytes.toBytes("username"),Bytes.toBytes("mj1"));
//        put.addColumn(Bytes.toBytes("user"),Bytes.toBytes("nickname"),Bytes.toBytes("mj1"));
//
//        put.addColumn(Bytes.toBytes("emoji"),Bytes.toBytes("name"),Bytes.toBytes("happy_emoji"));
//
//        put.addColumn(Bytes.toBytes("file"),Bytes.toBytes("file_id"),Bytes.toBytes("1"));
//        put.addColumn(Bytes.toBytes("file"),Bytes.toBytes("name"),Bytes.toBytes("mj1"));
//        put.addColumn(Bytes.toBytes("file"),Bytes.toBytes("extension"),Bytes.toBytes("mj1"));
//        table.put(put);
//
//        Put put2 = new Put(Bytes.toBytes("2"));
//        final Table table2 = connection.getTable(TABLE_NAME);
//        put2.addColumn(Bytes.toBytes("post"),Bytes.toBytes("chnnel_id"),Bytes.toBytes("2"));
//        put2.addColumn(Bytes.toBytes("post"),Bytes.toBytes("root_id"), Bytes.toBytes("2"));
//        put2.addColumn(Bytes.toBytes("post"),Bytes.toBytes("parent_id"),Bytes.toBytes("2"));
//        put2.addColumn(Bytes.toBytes("post"),Bytes.toBytes("message"),Bytes.toBytes("hi2"));
//        put2.addColumn(Bytes.toBytes("post"),Bytes.toBytes("hashtag"), Bytes.toBytes("hi2"));
//        put2.addColumn(Bytes.toBytes("post"),Bytes.toBytes("date"),Bytes.toBytes("0931"));
//
//        put2.addColumn(Bytes.toBytes("user"),Bytes.toBytes("user_id"),Bytes.toBytes("2"));
//        put2.addColumn(Bytes.toBytes("user"),Bytes.toBytes("username"),Bytes.toBytes("mj2"));
//        put2.addColumn(Bytes.toBytes("user"),Bytes.toBytes("nickname"),Bytes.toBytes("mj2"));
//
//        put2.addColumn(Bytes.toBytes("emoji"),Bytes.toBytes("name"),Bytes.toBytes("happy_emoji2"));
//
//        put2.addColumn(Bytes.toBytes("file"),Bytes.toBytes("file_id"),Bytes.toBytes("2"));
//        put2.addColumn(Bytes.toBytes("file"),Bytes.toBytes("name"),Bytes.toBytes("mj2"));
//        put2.addColumn(Bytes.toBytes("file"),Bytes.toBytes("extension"),Bytes.toBytes("mj2"));
//        table2.put(put2);

        Put put2 = new Put(Bytes.toBytes("3"));
        final Table table2 = connection.getTable(TABLE_NAME);
        put2.addColumn(Bytes.toBytes("post"),Bytes.toBytes("chnnel_id"),Bytes.toBytes("3"));
        put2.addColumn(Bytes.toBytes("post"),Bytes.toBytes("root_id"), Bytes.toBytes("3"));
        put2.addColumn(Bytes.toBytes("post"),Bytes.toBytes("parent_id"),Bytes.toBytes("3"));
        put2.addColumn(Bytes.toBytes("post"),Bytes.toBytes("message"),Bytes.toBytes("hi3"));
        put2.addColumn(Bytes.toBytes("post"),Bytes.toBytes("hashtag"), Bytes.toBytes("hi3"));
        put2.addColumn(Bytes.toBytes("post"),Bytes.toBytes("date"),Bytes.toBytes("1001"));

        put2.addColumn(Bytes.toBytes("user"),Bytes.toBytes("user_id"),Bytes.toBytes("3"));
        put2.addColumn(Bytes.toBytes("user"),Bytes.toBytes("username"),Bytes.toBytes("mj3"));
        put2.addColumn(Bytes.toBytes("user"),Bytes.toBytes("nickname"),Bytes.toBytes("mj3"));

        put2.addColumn(Bytes.toBytes("emoji"),Bytes.toBytes("name"),Bytes.toBytes("happy_emoji3"));

        put2.addColumn(Bytes.toBytes("file"),Bytes.toBytes("file_id"),Bytes.toBytes("3"));
        put2.addColumn(Bytes.toBytes("file"),Bytes.toBytes("name"),Bytes.toBytes("mj3"));
        put2.addColumn(Bytes.toBytes("file"),Bytes.toBytes("extension"),Bytes.toBytes("mj3"));
        table2.put(put2);

        connection.close();


    }
}

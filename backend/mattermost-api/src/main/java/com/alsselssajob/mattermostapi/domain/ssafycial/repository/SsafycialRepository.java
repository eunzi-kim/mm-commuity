package com.alsselssajob.mattermostapi.domain.ssafycial.repository;

import com.alsselssajob.mattermostapi.common.vo.ColumnFamily;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SsafycialRepository {

    private final static TableName SSAFYCIAL_TABLE_NAME = TableName.valueOf("ssafycials");
    private final static String CONFIGURATION_FILE_PATH = "/usr/local/hbase-2.3.6/conf/hbase-site.xml";

    private final Configuration configuration;

    public SsafycialRepository() {
        configuration = HBaseConfiguration.create();
        configuration.addResource(CONFIGURATION_FILE_PATH);
    }


    private void createTableIfNotExists(final Admin admin) throws IOException {
        if (!admin.tableExists(SSAFYCIAL_TABLE_NAME)) {
            final TableDescriptorBuilder table = TableDescriptorBuilder.newBuilder(SSAFYCIAL_TABLE_NAME);

            table.setColumnFamily(ColumnFamilyDescriptorBuilder.of(ColumnFamily.ssafycial.name()));
            table.setColumnFamily(ColumnFamilyDescriptorBuilder.of(ColumnFamily.user.name()));

            admin.createTable(table.build());
        }
    }

}

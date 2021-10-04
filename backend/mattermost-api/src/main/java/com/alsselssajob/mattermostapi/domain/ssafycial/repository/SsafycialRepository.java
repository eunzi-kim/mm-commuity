package com.alsselssajob.mattermostapi.domain.ssafycial.repository;

import com.alsselssajob.mattermostapi.common.vo.ColumnFamily;
import com.alsselssajob.mattermostapi.common.vo.qualifier.SsafycialQualifier;
import com.alsselssajob.mattermostapi.common.vo.qualifier.UserQualifier;
import com.alsselssajob.mattermostapi.domain.ssafycial.domain.Ssafycial;
import net.bis5.mattermost.model.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Put;
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

    private void addSsafycialColumnFamily(final Ssafycial ssafycial, final Put row) {
        row.addColumn(ColumnFamily.ssafycial.name().getBytes(),
                SsafycialQualifier.id.name().getBytes(),
                ssafycial.id().getBytes());
        row.addColumn(ColumnFamily.ssafycial.name().getBytes(),
                SsafycialQualifier.title.name().getBytes(),
                ssafycial.title().getBytes());
        row.addColumn(ColumnFamily.ssafycial.name().getBytes(),
                SsafycialQualifier.link.name().getBytes(),
                ssafycial.link().getBytes());
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
}

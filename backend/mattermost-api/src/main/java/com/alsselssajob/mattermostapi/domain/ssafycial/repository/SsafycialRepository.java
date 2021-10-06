package com.alsselssajob.mattermostapi.domain.ssafycial.repository;

import com.alsselssajob.mattermostapi.common.infra.MattermostUser;
import com.alsselssajob.mattermostapi.common.vo.ColumnFamily;
import com.alsselssajob.mattermostapi.common.vo.qualifier.SsafycialQualifier;
import com.alsselssajob.mattermostapi.common.vo.qualifier.UserQualifier;
import com.alsselssajob.mattermostapi.domain.ssafycial.domain.Ssafycial;
import net.bis5.mattermost.client4.MattermostClient;
import net.bis5.mattermost.model.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.System;
import java.util.List;
import java.util.logging.Level;

@Component
public class SsafycialRepository {

    private final static TableName SSAFYCIAL_TABLE_NAME = TableName.valueOf("ssafycials");
    private final static String CONFIGURATION_FILE_PATH = "/usr/local/hbase-2.3.6/conf/hbase-site.xml";

    private final Configuration configuration;

    public SsafycialRepository() {
        configuration = HBaseConfiguration.create();
        configuration.addResource(CONFIGURATION_FILE_PATH);
    }
    public static void main(String[] args) throws IOException {
        SsafycialRepository ssafycialRepository = new SsafycialRepository();
        MattermostClient client = MattermostClient.builder()
                .url("https://meeting.ssafy.com")
                .logLevel(Level.INFO)
                .ignoreUnknownProperties()
                .build();
        User user = client.login("mymysuzy0627@gmail.com", "Qwer1234!!").readEntity();
        List<Team> teams = client.getTeamsForUser(user.getId()).readEntity();
        teams.stream().forEach(team -> System.out.println(team.getDisplayName() + " " + team.getId()));
        Channel channel = client.getPublicChannelsByIdsForTeam("jnai78zewj87dfjwxtj8qmuydr", "9yxif5ehwirt7eo4wyz34af67e")
                .readEntity().get(0);
        System.out.println(channel.getDisplayName());
/*
        MattermostUser mattermostUser = new MattermostUser(client, user);
        List<Ssafycial> temp = mattermostUser.getSsafycialsForLastTwoWeeks();
        List<Ssafycial> ssafycials = temp.subList(0, 1);
        ssafycialRepository.saveSsafycials(user, ssafycials);

 */
    }

    public void saveSsafycials(final User user, final List<Ssafycial> ssafycials) throws IOException {
        final Connection connection = ConnectionFactory.createConnection(configuration);
        final Admin admin = connection.getAdmin();

        createTableIfNotExists(admin);

        final Table ssafycialTable = connection.getTable(SSAFYCIAL_TABLE_NAME);
        ssafycials.stream()
                .forEach(ssafycial -> {
                    final Put row = new Put(ssafycial.id().getBytes());

                    addSsafycialColumnFamily(ssafycial, row);
                    addUserColumnFamily(user, row);

                    try {
                        ssafycialTable.put(row);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        connection.close();
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

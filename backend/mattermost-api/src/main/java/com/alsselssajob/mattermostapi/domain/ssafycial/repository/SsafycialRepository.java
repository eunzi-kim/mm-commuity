package com.alsselssajob.mattermostapi.domain.ssafycial.repository;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.springframework.stereotype.Component;

@Component
public class SsafycialRepository {

    private final static TableName SSAFYCIAL_TABLE_NAME = TableName.valueOf("ssafycials");
    private final static String CONFIGURATION_FILE_PATH = "/usr/local/hbase-2.3.6/conf/hbase-site.xml";

    private final Configuration configuration;

    public SsafycialRepository() {
        configuration = HBaseConfiguration.create();
        configuration.addResource(CONFIGURATION_FILE_PATH);
    }
}

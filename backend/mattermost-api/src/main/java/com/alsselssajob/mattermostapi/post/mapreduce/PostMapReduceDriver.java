package com.alsselssajob.mattermostapi.post.mapreduce;

import com.alsselssajob.mattermostapi.post.domain.vo.Table;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

public class PostMapReduceDriver {

    private final static int CACHE_SIZE = 500;
    private final static int REDUCER_COUNT = 2;

    public static void main(String[] args) throws Exception {
        final Configuration configuration = HBaseConfiguration.create();
        final Job job = Job.getInstance(configuration);
        job.setJarByClass(PostMapReduceDriver.class);

        final Scan scan = new Scan();
        scan.setCaching(CACHE_SIZE);

        scan.setCacheBlocks(false);
        scan.readAllVersions();

        TableMapReduceUtil.initTableMapperJob(Table.posts.name(), scan, PostMapper.class, Text.class, LongWritable.class, job);
        TableMapReduceUtil.initTableReducerJob(Table.posts.name(), PostReducer.class, job);

        job.setNumReduceTasks(REDUCER_COUNT);
        job.waitForCompletion(true);
    }
}

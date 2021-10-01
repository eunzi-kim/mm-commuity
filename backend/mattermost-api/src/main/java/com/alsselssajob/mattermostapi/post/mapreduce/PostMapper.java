package com.alsselssajob.mattermostapi.post.mapreduce;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class PostMapper extends TableMapper<Text, Text> {

    private Text postId;
    private Text postedDate;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        postId = new Text();
        postedDate = new Text();
    }

}

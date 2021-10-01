package com.alsselssajob.mattermostapi.post.mapreduce;

import com.alsselssajob.mattermostapi.post.domain.vo.ColumnFamily;
import com.alsselssajob.mattermostapi.post.domain.vo.qualifier.PostQualifier;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class PostMapper extends TableMapper<Text, LongWritable> {

    private Text postId;
    private LongWritable createdAt;

    @Override
    protected void setup(Context context) {
        postId = new Text();
        createdAt = new LongWritable();
    }

    @Override
    protected void map(final ImmutableBytesWritable key, final Result post, final Context context)
            throws IOException, InterruptedException {
        final String postIdValue = post.getRow().toString();
        final long createdAtValue = Long.valueOf(post.getValue(ColumnFamily.post.name().getBytes(),
                PostQualifier.created_at.name().getBytes()).toString());

        postId.set(postIdValue);
        createdAt.set(createdAtValue);

        context.write(postId, createdAt);
    }
}

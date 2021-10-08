package com.alsselssajob.mattermostapi.domain.post.mapreduce;

import com.alsselssajob.mattermostapi.common.vo.ColumnFamily;
import com.alsselssajob.mattermostapi.common.vo.qualifier.PostQualifier;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class PostReducer extends TableReducer<Text, LongWritable, ImmutableBytesWritable> {

    private final static String DASH = "-";
    private final static String SPACE = " ";
    private final static String COLON = ":";

    @Override
    protected void reduce(final Text postId, final Iterable<LongWritable> createdAts, final Context context)
            throws IOException, InterruptedException {
        final Put createdAtColumn = new Put(postId.getBytes());
        final LongWritable createdAt = createdAts.iterator()
                .next();

        final LocalDateTime createdTime = convertMillisecondsToLocalDateTime(createdAt);
        final StringBuilder formattedCreatedTime = setCreatedTimeFormat(createdTime);

        createdAtColumn.addColumn(ColumnFamily.post.name().getBytes(),
                PostQualifier.created_date.name().getBytes(),
                formattedCreatedTime.toString().getBytes());

        context.write(null, createdAtColumn);
    }

    private LocalDateTime convertMillisecondsToLocalDateTime(final LongWritable createdAt) {
        return Instant.ofEpochMilli(createdAt.get()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private StringBuilder setCreatedTimeFormat(final LocalDateTime createdTime) {
        return new StringBuilder().append(createdTime.getYear())
                .append(DASH)
                .append(createdTime.getMonthValue())
                .append(DASH)
                .append(createdTime.getDayOfMonth())
                .append(SPACE)
                .append(createdTime.getHour())
                .append(COLON)
                .append(createdTime.getMinute())
                .append(COLON)
                .append(createdTime.getSecond());
    }
}

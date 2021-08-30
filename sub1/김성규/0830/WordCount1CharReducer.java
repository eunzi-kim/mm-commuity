package ssafy;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCount1CharReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    public void reduce(final Text key, final Iterable<IntWritable> values, final Context context) throws IOException, InterruptedException {
        final IntWritable result = new IntWritable();
        int sum = 0;

        for (IntWritable val : values) {
            sum += val.get();
        }

        result.set(sum);
        context.write(key, result);
    }
}

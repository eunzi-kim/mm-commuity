package ssafy;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MatrixAdditionReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable result;

    @Override
    protected void setup(final Context context) {
        result = new IntWritable();
    }

    public void reduce(final Text key, final Iterable<IntWritable> values, final Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        result.set(sum);
        context.write(key, result);
    }
}


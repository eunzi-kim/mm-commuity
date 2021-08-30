package ssafy;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCount1CharMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable ONE = new IntWritable(1);
    private final static int START_INDEX = 0;
    private final static int END_INDEX = 1;

    public void map(final Object key, final Text value, final Context context) throws IOException, InterruptedException {
        final Text firstCharOfText = new Text();
        firstCharOfText.set(getFirstCharOfText(value));
        context.write(firstCharOfText, ONE);
    }

    private final String getFirstCharOfText(final Text value) {
        return value.toString().substring(START_INDEX, END_INDEX);
    }
}

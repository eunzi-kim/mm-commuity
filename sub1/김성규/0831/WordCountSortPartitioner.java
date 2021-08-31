package ssafy;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class WordCountSortPartitioner extends Partitioner<Text, IntWritable> {

    private final static char CRITERION_FOR_PARTITION = 'a';
    private final static int FIRST_INDEX = 0;
    private final static int FIRST_REDUCER_NUMBER = 0;
    private final static int SECOND_REDUCER_NUMBER = 1;

    @Override
    public int getPartition(final Text text, final IntWritable intWritable, final int numPartitions) {
        if(text.toString().charAt(FIRST_INDEX) < CRITERION_FOR_PARTITION) {
            return FIRST_REDUCER_NUMBER;
        }
        return SECOND_REDUCER_NUMBER;
    }
}

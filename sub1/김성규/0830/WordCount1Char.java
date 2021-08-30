package ssafy;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;


public class WordCount1Char {

    private final static String ERROR_MESSAGE = "Usage: <in> <out>";
    private final static boolean IS_VERBOSE = true;
    private final static int VALID_REMAINING_ARGS_LENGTH = 2;
    private final static int ERROR_CODE = 2;
    private final static int ABNORMAL_FINISH_CODE = 1;
    private final static int NORMAL_FINISH_CODE = 0;
    private final static int NUMBER_OF_REDUCERS = 2;
    private final static int INPUT_FILE_INDEX = 0;
    private final static int OUTPUT_FILE_INDEX = 1;

    public static void main(String[] args) throws Exception {
        final Configuration configuration = new Configuration();
        final String[] remainingArgs = new GenericOptionsParser(configuration, args).getRemainingArgs();

        if (remainingArgs.length != VALID_REMAINING_ARGS_LENGTH) {
            System.err.println(ERROR_MESSAGE);
            System.exit(ERROR_CODE);
        }

        final Job job = Job.getInstance(configuration);
        setJob(job);

        setInputAndOutputPath(job, remainingArgs);

        System.exit(job.waitForCompletion(IS_VERBOSE) ? NORMAL_FINISH_CODE : ABNORMAL_FINISH_CODE);
    }

    private static void setJob(final Job job) {
        job.setJarByClass(WordCount1Char.class);

        job.setMapperClass(WordCount1CharMapper.class);
        job.setReducerClass(WordCount1CharReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setNumReduceTasks(NUMBER_OF_REDUCERS);
    }

    private static void setInputAndOutputPath(final Job job, final String[] remainingArgs) throws IOException {
        FileInputFormat.addInputPath(job, new Path(remainingArgs[INPUT_FILE_INDEX]));
        FileOutputFormat.setOutputPath(job, new Path(remainingArgs[OUTPUT_FILE_INDEX]));
    }
}



package invertexindex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

public class InvertedIndex {

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

        deleteOutputIfExists(configuration, remainingArgs[OUTPUT_FILE_INDEX]);

        final Job job = Job.getInstance(configuration);
        setJob(job);

        setInputAndOutputPath(job, remainingArgs);

        System.exit(job.waitForCompletion(IS_VERBOSE) ? NORMAL_FINISH_CODE : ABNORMAL_FINISH_CODE);
    }

    private static void deleteOutputIfExists(final Configuration configuration, final String outputFileName) throws IOException {
        final FileSystem hdfs = FileSystem.get(configuration);
        final Path output = new Path(outputFileName);

        if (hdfs.exists(output)) {
            hdfs.delete(output, true);
        }
    }

    private static void setJob(final Job job) {
        job.setJarByClass(InvertedIndex.class);

        job.setMapperClass(InvertedIndexMapper.class);
        job.setReducerClass(InvertedIndexReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        job.setNumReduceTasks(NUMBER_OF_REDUCERS);
    }

    private static void setInputAndOutputPath(final Job job, final String[] remainingArgs) throws IOException {
        FileInputFormat.addInputPath(job, new Path(remainingArgs[INPUT_FILE_INDEX]));
        FileOutputFormat.setOutputPath(job, new Path(remainingArgs[OUTPUT_FILE_INDEX]));
    }
}


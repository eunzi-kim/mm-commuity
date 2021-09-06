package ssafy;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MatrixAdditionMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final static String TAB = "\t";
    private final static int ROW_INDEX = 1;
    private final static int COLUMN_INDEX = 2;
    private final static int VALUE_INDEX = 3;

    private Text resultKey;
    private IntWritable resultValue;

    @Override
    protected void setup(final Context context) {
        resultKey = new Text();
        resultValue = new IntWritable();
    }

    public void map(final Object key, final Text matrix, final Context context) throws IOException, InterruptedException {
        final String[] matrixSplitByTab = matrix.toString()
                .split(TAB);
        final String resultKeyValue = matrixSplitByTab[ROW_INDEX].concat(TAB)
                .concat(matrixSplitByTab[COLUMN_INDEX]);

        resultKey.set(resultKeyValue);
        resultValue.set(Integer.valueOf(matrixSplitByTab[VALUE_INDEX]));

        context.write(resultKey, resultValue);
    }
}

package ssafy;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {

    private final static String COLON_WITH_TAB = ":\t";
    private final static String COMMA_WITH_SPACE = ", ";

    private Text value;

    @Override
    protected void setup(final Context context) {
        value = new Text();
    }

    @Override
    protected void reduce(final Text inputKey, final Iterable<Text> inputValues, final Context context) throws IOException, InterruptedException {
        final StringBuilder fileNameAndPositions = new StringBuilder();
        boolean isFirstReducing = true;
        
        for (Text inputValue : inputValues) {
            if (isFirstReducing) {
                fileNameAndPositions.append(COLON_WITH_TAB).append(inputValue.toString());
                isFirstReducing = false;
            } else {
                fileNameAndPositions.append(COMMA_WITH_SPACE).append(inputValue.toString());
            }
        }

        value.set(fileNameAndPositions.toString());
        context.write(inputKey, value);
    }
}


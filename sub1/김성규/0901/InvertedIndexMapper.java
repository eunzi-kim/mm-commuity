package invertexindex;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;
import java.util.StringTokenizer;

public class InvertedIndexMapper extends Mapper<Object, Text, Text, Text> {

    private final static String COLON_WITH_SPACE = ": ";
    private final static String DELIMITER = " ";
    private final static boolean IS_RETURNING_DELIMITERS = true;
    
    private String fileName;
    private Text word;
    private Text fileNameAndPosition;

    @Override
    protected void setup(final Context context) {
        fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
        word = new Text();
        fileNameAndPosition = new Text();
    }

    @Override
    protected void map(final Object inputKey, final Text inputValue, final Context context) throws IOException, InterruptedException {
        final StringTokenizer stringTokenizer = new StringTokenizer(inputValue.toString(), DELIMITER, IS_RETURNING_DELIMITERS);
        Long positionOfWord = ((LongWritable) inputKey).get();

        while (stringTokenizer.hasMoreTokens()) {
            final String wordValue = extractWordWithoutSpecialCharacters(stringTokenizer.nextToken());

            if(!wordValue.isEmpty()) {
                final String fileNameAndPositionValue = fileName.concat(COLON_WITH_SPACE)
                        .concat(String.valueOf(positionOfWord));

                word.set(wordValue);
                fileNameAndPosition.set(fileNameAndPositionValue);

                context.write(word, fileNameAndPosition);
            }

            positionOfWord = Long.sum(positionOfWord, wordValue.length());
        }
    }

    private final String extractWordWithoutSpecialCharacters(String word) {
        while (true) {
            final char lastCharacter = word.charAt(word.length() - 1);

            if (!(('a' <= lastCharacter && lastCharacter <= 'z') || ('A' <= lastCharacter && lastCharacter <= 'Z'))) {
                word = word.substring(0, word.length() - 1);
            } else {
                break;
            }
        }

        return word;
    }
}

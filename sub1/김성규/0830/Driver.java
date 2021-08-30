package ssafy;

import org.apache.hadoop.util.ProgramDriver;

public class Driver {

    private final static String MAP_REDUCE_NAME = "wordCount1Char";
    private final static String MAP_REDUCE_DESCRIPTION = "A map/reduce program that performs word counting 1 character.";
    private final static int ERROR_CODE = -1;
    private final static int NORMAL_FINISH_CODE = 0;

    public static void main(String[] args) {
        final ProgramDriver programDriver = new ProgramDriver();
        try {
            programDriver.addClass(MAP_REDUCE_NAME, Wordcount1Char.class, MAP_REDUCE_DESCRIPTION);
            programDriver.driver(args);
            
            System.exit(NORMAL_FINISH_CODE);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        System.exit(ERROR_CODE);
    }
}


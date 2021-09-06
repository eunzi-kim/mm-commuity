package ssafy;

import org.apache.hadoop.util.ProgramDriver;

public class Driver {
	public static void main(String[] args) {
		int exitCode = -1;
		ProgramDriver pgd = new ProgramDriver();
		try {
			pgd.addClass("matadd", MatrixAdd.class, "test4");
			pgd.addClass("inverted", InvertedIndex.class, "task3");
			pgd.addClass("wordcountsort", Wordcountsort.class, "task2");
			pgd.addClass("wordcount1char", Wordcount1char.class, "task1");
			pgd.addClass("wordcount", Wordcount.class, "A map/reduce program that performs word counting.");		
      			pgd.driver(args);
			exitCode = 0;
		}
		catch(Throwable e) {
			e.printStackTrace();
		}

		System.exit(exitCode);
	}
}

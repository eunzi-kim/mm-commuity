package ssafy;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Wordcount {
	/* 
	Object, Text : input key-value pair type (always same (to get a line of input file))
	Text, IntWritable : output key-value pair type
	*/
	public static class TokenizerMapper
			extends Mapper<Object,Text,Text,IntWritable> {

		// variable declairations
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		// map function (Context -> fixed parameter)
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {

			// NOTE by sohyun
			// StringTokenizer :: breaking a string into a char 
			// basically according to a space between words.
			// e.g) Hello Geeks how are you =>> Hello, Geeks, how, are, you
			// For now, value is  Text type. 
			// value type should be changed into a string due to StringTokenizer. 	
			// That's why we need a toString method.
			StringTokenizer itr = new StringTokenizer(value.toString());
			while ( itr.hasMoreTokens() ) {
				//NOTE by sohyun
				//keep setting on word unitl there is no chars left.
				word.set(itr.nextToken());
				
				// NOTE by sohyun
				// emit a key-value pair by a map method.
				context.write(word,one);
			}
		}
	}

	/*
	Text, IntWritable : input key type and the value type of input value list
	Text, IntWritable : output key-value pair type
	*/
	public static class IntSumReducer
			extends Reducer<Text,IntWritable,Text,IntWritable> {

		// variables
		private IntWritable result = new IntWritable();

		// key : a disticnt word
		// values :  Iterable type (data list)
		public void reduce(Text key, Iterable<IntWritable> values, Context context) 
				throws IOException, InterruptedException {
			
			//NOTE by sohyun
			// .get :: getting a data in val and returning into a IntWrtiable type.
			int sum = 0;
			for ( IntWritable val : values ) {
				sum += val.get();
			}
			result.set(sum);
			//NOTE by sohyun
			//emit a result of reduce method.
			context.write(key,result);
		}
	}


	/* Main function */
	public static void main(String[] args) throws Exception {

		//NOTE by sohyun
		//initialization to perform  MapReduce process
		Configuration conf = new Configuration();

		String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();

		if ( otherArgs.length != 2 ) {
			System.err.println("Usage: <in> <out>");
			System.exit(2);
		}
		Job job = new Job(conf,"word count");
		
		//NOTE by sohyun
		//class declaration which is perform "job"
		job.setJarByClass(Wordcount.class);

		// let hadoop know my map and reduce classes
		job.setMapperClass(TokenizerMapper.class);
		//job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);

		//NOTE by sohyun
		//declaration of output key type and output value type about reduce method.
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		//NOTE by sohyun
		//Use lines below 
		//when map method's result, output key type and output value type, is different with reduce method.
		//job.setMapOutputKeyClass(Text.class);
		//job.setMapOutputValueClass(IntWritable.class);

		// set number of reduces
		job.setNumReduceTasks(2);

		// set input and output directories
		FileInputFormat.addInputPath(job,new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job,new Path(otherArgs[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1 );
	}
}


# 0831 TIL - MapReduce Phase 코드 분석
## 1. ProgramDriver

ProgramDriver는 구현한 MapReduce Phase를 `addClass`메서드를 이용해 jar파일에 등록하는 역할을 하고, `driver`메서드를 통해 해당 클래스의 `main`메서드를 실행한다.

---

### 1.1) addClass(String, Class<?>, String)

각각의 parameter는 **name, mainClass, description** 순으로 사용되며 다음과 같이 정의된다.

- **name** : CLI 환경에서 사용될 jar 파일에 등록할 mainClass의 alias
- **mainClass** : Map, Reduce 함수 등 jar 파일에 등록할 MapReduce Phase를 정의한 class
- **description** : MapReduce Phase에 대한 설명

---

### 1.2) driver(String[])

parameter 배열의 첫번째 문자열은 hadoop에서 실행할 program의 이름에 해당한다. 즉, `addClass`메서드의 name parameter와 동일해야 한다. 만약 해당 program이 존재한다면, 배열의 남은 문자열들을 parameter로 하여 program의 `main`메서드를 실행한다.

```java
import org.apache.hadoop.util.ProgramDriver;

public class Driver {
    
    public static void main(String[] args) {
        
        int exitCode = -1;
        ProgramDriver programDriver = new ProgramDriver();
        try {
            programDriver.addClass("mapReducePhase", MapReducePhase.class, 
								"A map/reduce program");
            programDriver.driver(args);
            exitCode = 0;
        } catch (Throwable e) {
            e.printStackTrace();
        }

        System.exit(exitCode);
    }
}
```

---

## 2. MapReduce Phase

### 2.1) main 메서드

MapReduce Phase의 **Main 함수**에 해당하며 설정을 담당한다.

- main 코드 예시

```java
public static void main(String[] args) throws Exception {
    Configuration configuration = new Configuration();
    String[] remainingArgs = new GenericOptionsParser(configuration, args).getRemainingArgs();
        
    if (remainingArgs.length != 2) {
        System.err.println("Usage: <in> <out>");
        System.exit(2);
    }
    deleteOutputIfExists(configuration, remainingArgs[1]);

    Job job = Job.getInstance(configuration, "MapReduce Phase");
    setJob(job);
    setInputAndOutputPath(job, remainingArgs);

    System.exit(job.waitForCompletion(true) ? 0 : 1);
}

private final static void deleteOutputIfExists(final Configuration configuration, final String outputPath) {
    FileSystem hdfs = FileSystem.get(configuration);
    Path output = new Path(outputPath);
    if (hdfs.exists(output)) {
        hdfs.delete(output, false);
    }
}

private final static void setJob(final Job job) {
    // Jar파일에 등록할 MapReduce 클래스 설정(Main 함수를 포함한 클래스)
    job.setJarByClass(MapReducePhase.class);

    // Mapper, Combiner, Partitioner, Reducer를 담당할 클래스 설정
    job.setMapperClass(MyMapper.class);
    job.setCombinerClass(MyReducer.class);
    job.setPartitionerClass(MyPartitioner.class);
    job.setReducerClass(MyReducer.class);

    // Reducer의 결과로 출력할 (Key, Value) 타입 설정
    // Map과 Reducer의 결과 타입이 같을 경우 Map은 결과 타입 설정 생략 가능
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);

    // Reducer로 동작할 Machine 수 설정
    job.setNumReduceTasks(2);
}

private final static void setInputAndOutputPath(final Job job, final String[] remainingArgs) throws IOException {
    FileInputFormat.addInputPath(job, new Path(remainingArgs[0]));
    FileOutputFormat.setOutputPath(job, new Path(remainingArgs[1]));
}
```

- `deleteOutputIfExists`메서드

    매번 jar파일을 생성할 때마다 이전에 생성했던 output HDFS 파일을 삭제해야 하므로, 이 코드를 통해 이전에 생성되었던 output HDFS 파일을 삭제한다. 제공된 코드에서는 파일을 삭제하는 코드가 `hdfs.delete(output);`이지만 해당 메서드는 Deprecated되었으므로 Javadoc을 참고하여 위의 메서드로 변경하였다.

    Signature의 `boolean`값인 recursive가 의미하는 바가 무엇인지 명확하지 않아 확인해 보았더니 **"path가 디렉토리이고 `true`로 설정된 경우 디렉토리가 삭제되지 않으면 예외가 발생합니다. 파일의 경우 recursive는 `true` 또는 `false`로 설정할 수 있습니다."**라고 한다. 
    (디렉토리를 삭제할 경우 recursive가 영향을 주지만, 파일을 삭제하는 경우 영향을 주지 않는 듯 하다.

- `setJob`메서드

    Job을 생성하는 코드도 주어진 코드에서는
    `Job job = new Job(...);`였지만 Job의 생성자가 Deprecated되었기 때문에 Javadoc에 따라 팩토리 메서드인 `getInstance(...);`를 사용했다.

- `setInputAndOutputPath`메서드

    1.2)의 `driver`메서드에서 CLI 환경에서 입력받은 첫번째 Parameter는 실행할 program의 이름이라고 했다. 따라서 CLI 환경의 두번째 parameter로 입력받은 input HDFS 파일명과 두번째 Parameter로 입력받은 output HDFS 파일명을 입출력 파일로 설정한다.

---

### 2.2) Mapper<keyIn, valueIn, keyOut, valueOut>

MapReduce의 Map 함수를 수행하는 class로, `import org.apache.hadoop.mapreduce.Mapper`를 상속받아 `map`메서드를 `@Override`해야 한다.

```java
public static class MyMapper extends Mapper<Object, Text, Text, IntWritable> {

    private Text resultKey = new Text();
    private IntWritable resultValue = new IntWritable();
		
    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

    String stringValue = value.toString();
    // 알고리즘 수행 결과 : String result;
    resultKey.set(result);
    context.write(resultKey, resultValue);

    }
}
```

---

### 2.3) Reducer<keyIn, valueIn, keyOut, valueOut>

MapReduce의 Reduce 함수를 수행하는 class로, `import org.apache.hadoop.mapreduce.Reducer`를 상속받아 `reduce`메서드를 `@Override`해야 한다. 

```java
public static class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable resultValue = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

    int result = 0;
    for ( IntWritable value : values ) {
        int realValue = value.get();
        // 알고리즘 수행
    }

    resultValue.set(result);
    context.write(key, resultValue);
    }
}
```

📢 `Reducer`는 Combine 함수에 사용될 수 있다.

---

### 2.4) Partitioner<key, value>

Partitioner는 Map 함수의 output에 대해 Reduce 함수를 수행할 Machine을 특정 조건에 따라 결정하는 class로, `import org.apache.hadoop.mapreduce.Partitioner`를 상속받아 `getPartition`메서드를 `@Override`해야 한다.
(아래의 코드는 key값의 첫 글자가 ASCII 코드의 'a'보다 작을 경우 0번 Machine으로, 클 경우 1번 Machine으로 데이터를 Partition한다)

```java
public static class MyPartitioner extends Partitioner<Text, IntWritable> {

    @Override
    public int getPartition(Text key, IntWritable value, int numPartitions) {
        if(key.toString().charAt(0) < 'a') {
            return 0;
        }
        return 1;
    }
}
```

---

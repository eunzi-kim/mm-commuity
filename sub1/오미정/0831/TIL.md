## 특화PJT - 광주 1반 3팀 빅데이터#2(분산처리)

## 하둡이란?

하둡이란, 더그 커팅이 대용량의 비정형 데이터를 기존의 RDM 기술로 처리가 힘들다는 것을 깨닫고 새로운 기술을 찾는 중 구글에서 발표한 GFS와 MapReduce 관련 논문을 참고하여 개발된 프레임워크이다.

하나의 성능 좋은 컴퓨터를 이용하여 데이터를 처리하는 대신, 적당한 성능의 범용 컴퓨터 여러 대를 클러스터화하고 큰 크기의 데이터를 클러스터에서 병렬로 동시에 처리하여 처리 속도를 높이는 것을 목적으로 하는 분산처리를 위한 오픈소스 프레임워크이다.

하둡은 분산시스템인 HDFS에 데이터를 저장하고, 맵리듀스를 이용해 데이터를 처리한다.





## HDFS(Hadoop Distributed File System)

빅데이터 파일을 여러 대의 서버에 분산 저장하기 위한 파일시스템이다.

여러 대의 서버에 데이터를 저장하고, 저장된 각 서버에서 동시에 데이터를 처리하는 방식이다.



## 맵리듀스란? (Map + Reduce)

각 서버에서 데이터를 분산처리하는 분산병렬처리를 위한 분석시스템이다.

Map: 흩어져 있는 데이터를 (key, value) 형태로 연관성 있는 데이터 분류로 묶는 작업

Reduce: Filtering과 Sorting을 거쳐서 데이터를 추출하고 Map화한 작업 중 중복 데이터를 제거하고 원하는 데이터를 추출하는 작업





## Wordcount1char 과제

1. cd /home/Project/src에 들어가서 `Wordcount.java` 파일을 복사하여서 `Wordcount1char.java` 파일로 복제한다.

   ```
   cp Wordcount.java Wordcount1char.java
   ```

2. class 이름을 `Wordcount1char1` 로 변경 후, 단어에서 첫 번째 문자만 뽑아서 key로 만들어주도록 TokenizerMapper 클래스를 변경한다.

   ```java
   public static class TokenizerMapper
               extends Mapper<Object, Text, Text, IntWritable> {
   
           private final static IntWritable one = new IntWritable(1);
           private Text word = new Text();
   
           public void map(Object key, Text value, Context context)
                   throws IOException, InterruptedException {
   
               StringTokenizer itr = new StringTokenizer(value.toString());
               while (itr.hasMoreTokens()) {
                   word.set(itr.nextToken().substring(0, 1));
                   context.write(word, one);
               }
           }
       }
   ```

3. main 함수에서 `setJarByClass` 안에 변수를 `Wordcount1char`로 변경해준다.

   ```java
    job.setJarByClass(Wordcount1char.class);
   ```

4. `Driver.java` 파일에 새로 코딩한 자바 프로그램을 등록해준다.

   ```java
   pgd.addClass("wordcount1char", Wordcount1char.class, "1st");
   ```

5. /home/Project 경로에서 `ant` 명령어로 컴파일 해준다.

6. 테스트 데이터를 HDFS에 넣는다.

   ```
   cd /home/hadoop/Project
   hdfs dfs -mkdir wordcount_test
   hdfs dfs -put data/wordcount-data.txt wordcount_test
   
   # 결과를 저장할 디렉토리가 있을 경우 삭제한 후 프로그램을 실행한다.
   hdfs dfs -rm -r wordcount_test_out
   
   # 실행
   hadoop jar ssafy.jar wordcount wordcount_test wordcount_test_out
   
   # 결과보기 (reduct 개수에 따라 00000~ 에 대한 개수가 달라짐)
   hdfs dfs -cat wordcount_test_out/part-r-00000 | more
   ```

   



# Result

- Wordcount1char

  ![result_00000](../img/result_00000.PNG)

  ![result_00001](../img/result_00001.PNG)
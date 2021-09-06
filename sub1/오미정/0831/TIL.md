## 특화PJT - 광주 1반 3팀 빅데이터#2(분산처리)

## 하둡이란?

하둡이란, 더그 커팅이 대용량의 비정형 데이터를 기존의 RDM 기술로 처리가 힘들다는 것을 깨닫고 새로운 기술을 찾는 중 구글에서 발표한 GFS와 MapReduce 관련 논문을 참고하여 개발된 프레임워크이다.

하나의 성능 좋은 컴퓨터를 이용하여 데이터를 처리하는 대신, 적당한 성능의 범용 컴퓨터 여러 대를 클러스터화하고 큰 크기의 데이터를 클러스터에서 병렬로 동시에 처리하여 처리 속도를 높이는 것을 목적으로 하는 분산처리를 위한 오픈소스 프레임워크이다.

- 주요 구성 요소
  - HDFS: 데이터를 분산하여 저장한다.
  - MapReduce: 소프트웨어의 수행을 분산한다.(데이터처리)

- 한 개의 Namenode 와 여러 개의 Datanode
  - Namenode
    - 파일 시스템을 관리하고 클라이언트가 파일에 접근할 수 있도록 한다.
  - Datanode
    - 컴퓨터에 들어있는 데이터를 접근할 수 있도록 한다.





## HDFS(Hadoop Distributed File System)

- 빅 데이터 파일을 여러 대의 컴퓨터에 나누어서 저장한다.
- 각 파일은 여러 개의 순차적인 블록으로 저장한다.
- 하나의 파일의 각각 블록은 `Fault Toerance`를 위해서 여러 개로 복사되어 여러 머신에 저장된다.
  - `Fault Toerance`: 시스템을 구성하는 부품의 일부에서 결함 or 고장이 발생하여서 정상적 혹은 부분적으로 기능을 수행할 수 있는 것





## 맵리듀스란? (Map + Reduce)

- 데이터 중심 프로세싱
  - 한 대의 컴퓨터의 능력으로 처리가 어렵다.
  - 근본적으로 수 십대, 수 백대 혹은 수 천대 컴퓨터를 묶어서 처리해야한다.
  - 맵리듀스 프레임워크가 하는 것이 바로 이것이다.



맵리듀스는 각 서버에서 데이터를 분산처리하는 분산병렬처리를 위한 분석시스템이다.

- 값싼 컴퓨터들을 모아서 클러스터를 만들고 빅데이터를 처리하기 위한 스케일러블 병렬 소프트웨어의 구현을 쉽게 할 수 있도록 도와주는 간단한 프로그래밍 모델이다.

  - `scalable` 하다 라는 말은 사용자 수 or 데이터가 급증해도 프로그램이 멈추거나 성능이 크게 떨어지지 않는 다는 뜻이다.

- 드라이버에 해당하는 `main` 함수가 `Map` 함수와 `Reduce` 함수를 호출해서 처리한다.

  - Map: 흩어져 있는 데이터를 (key, value) 형태로 연관성 있는 데이터 분류로 묶는 작업

  - Reduce: Filtering과 Sorting을 거쳐서 데이터를 추출하고 Map화한 작업 중 중복 데이터를 제거하고 원하는 데이터를 추출하는 작업



### MapReduce Phase(3단계)

- **Map Phase**
  - 제일 먼저 수행되며 데이터의 여러 파티션에 병렬 분산으로 호출되어 수행된다.
  - 각 머신마다 수행된 Mapper는 맵 함수가 입력 데이터의 한 줄 마다 Map 함수를 호출한다.
  - Map 함수는 (Key, Value) 쌍 형태로 결과를 출력하고 여러 머신에 나누어 보내며, 같은 Key를 가진 (key, value) 쌍은 같은 머신으로 보낸다.
- **Shuffling Phase**
  - 모든 머신에서 `Map Phase`가 다 끝나면 시작된다.
  - `Map Phase` 에서 각각의 머신으로 보내진 (key, value) 쌍을  key를 이용해서 sorting 한 후에 각각의 key마다 같은 key를 가진 (key, value)쌍을 모아서 `value-list`를 만든 다음, (key, value) 형태로 key에 따라 여러 머신에 분산해서 보낸다.
- **Reduce Phase**
  - 모든 머신에서 `Shuffling phase`가 끝나면 각 머신마다 `Reduce phase`가 시작된다.
  - 각각의 머신에서 `Shuffling phase`에서 해당 머신으로 보내진 각각의 (key, value-list) 쌍 마다 reduce 함수가 호출되며 하나의 reduce 함수가 끝날 경우 다음 (key, value-list) 쌍에 reduce 함수가 호출된다.
  - 출력은 (key, value) 쌍 형태로 출력한다.





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

   - `StringTokenizer(String str)`: 전달된 str을 기본 delim으로 분리한다. 기본 delimiter는 공백 문자들인 "\t\n\r\t"이다. 
   - `Mapper<Object,Text,Text,Text>` : <입력 key 타입, 입력 value 타입, 출력 key 타입, 출력 value 타입>

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
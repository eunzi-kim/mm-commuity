## 특화PJT - 광주 1반 3팀 빅데이터#2(분산처리)

# 빅데이터(분석) 사전학습 1강

## 목표

- 병렬 분산 알고리즘 구현이 가능한 MapReduce 프레임워크를 이해한다.
- MapReduce  프레임워크를 사용할 수 있는 Hadoop설치 및 MapReduce  알고리즘 코드를 실행한다.
- Hadoop을 이용해 빅데이터 분석 및 처리용 MapReduce  알고리즘을 구현하는데 필요한 지식과 코딩 능력을 배양한다.

------

### Scale-out이 Scale-up보다 더 중요하다.

> Scale-out : 저렴한 서버들을 많이 이용하는 것.

> Scale-up : 고가의 서버들을 조금 이용하는 것.

데이터 중심(data-intensive) 어플리케이션 분야에서는 Scale-out을 선호함.

2배의 성능을 가진 비싼 컴퓨터 가격이 일반 컴퓨터 2대의 가격보다 훨씬 비싸기 때문!

## MapReduce 프레임워크

> 값싼 컴퓨터들로 클러스터를 이루고 여기에 빅데이터 처리를 위한 스케일어블(Scaleable) 병렬 소프트웨어의 구현을 쉽게할 수 있도록 도와주는 프로그래밍 모델.

- 데이터 중심 프로세싱(Data-intensive Processing)은 수 백대 혹은 수 천대의 컴퓨터를 묶어 처리해야 하는데, 이를 도와주는 프레임워크다.
- 빅데이터 응용분야에서 최근에 주목받고 있는 프레임워크이며, 빅데이터를 이용한 효율적인 계산이 가능한 첫 번째 프로그래밍 모델이다.
- 구글의 MapReduce 혹은 오픈소스인 Hadoop은 MapReduce 프레임워크의 우수한 구현의 형태.
- 드라이버에 해당하는 **Main 함수가 map함수와 reduce 함수를 호출해 처리.**

```shell
[참고] scaleable하다
사용자나 데이터가 급증해도 프로그램이 멈추거나 성능이 떨어지는 일이 없다는 뜻.
```

### MapReduce Programming Model

> 함수형 프로그래밍 언어의 형태

유저는 아래 3가지 함수를 구현해 제공해야 한다.

```plaintext
* Main함수
* Map함수: (key1, val1) → [(key2, val2)]
* Reduce함수: (key2, [val2]) → [(key3, val3)]
```

- MapReduce 프레임워크에서는 레코드를 키-밸류(key, value)쌍으로 표현한다.

- `Main함수`는 한 개의 `master machine`에서 수행된다.

  이 머신은 **Map함수를 수행하기 전 전처리 혹은 Reduce함수의 결과를 후처리 하는데 사용**될 수 있다.

- Map → Reduce MapReduce phase는 Map을 먼저 호출하고 그 다음 Reduce함수를 호출.

- Map → Combine → Reduce 때에 따라서는 Map함수 이후 combine함수를 중간에 수행할 수 있다.

- 드라이버에 해당하는 Main프로그램에서 MapReduce Phase를 수행시킨다.

#### Map Phase

- 제일 먼저 수행된다.
- 데이터의 여러 파티션에 병렬 분산으로 호출되어 수행된다.
- Mapper가 수행되어 입력 데이터 마다 Map함수를 호출한다.
- Map함수는 key, value  형태로 결과를 출력한다.
- **여러 머신에 나누어 결과를 보내며** **같은 key를 가진 key, value쌍은 같은 머신으로 보내진다.**

(Combine)

#### Shuffling Phase

- 모든 머신에서 Map Phase가 끝나면 시작된다.
- Map Phase에서 머신으로 보내진 key, value쌍의 key를 이용해 **정렬**한다. 그 후같은 key를 가진 key, value쌍을 모아`value-list`를 만들며 key, value-list형태로 **key에 따라 여러 머신에 분산해 보낸다.**

#### Reduce Phase

- 모든 머신에서 Shuffling Phase가 끝나면 각 머신마다 Reduce Phase가 시작된다.

- Shuffling에서 해당 머신으로 보내진 각 key, value-list쌍이 각 머신에서 Reduce 함수가 호출된다.

- Mapper가 Map함수를 호출했듯이,

  하나의 Reduce함수가 끝나면 다음 key, value-list쌍의 Reduce함수가 Reducer에 의해 호출된다.

- **출력이 있다면 key, value쌍 형태로 출력**한다.

## MapReduce 함수

- Map함수
  - org.apache.hadoop.mapreduce 패키지의 Mapper클래스를 상속받아 Map method를 수정.
  - 입력 텍스트 파일에서 라인 단위로 호출되고 입력은 key, value형태로 된다.
  - key는 입력 텍스트 파일에서 맨 앞 문자를 기준으로 Map함수가 호출된 해당 라인의 첫 번째 문자까지의 offset
  - value는 **텍스트의 해당 라인 전체**가 들어있다.
- Combine함수
  - 필요에의해 사용. 사용하면 **Map 함수의 결과 크기를 줄여주기 때문에 Suffling비용을 줄이고 Network Traffic을 감소시킨다.**
  - 즉, 각 머신의 Map Phase에서 Map함수 출력 크기를 줄여 Shuffling Phase와 Reduce Phase의 비용을 줄여주는데 사용된다.
  - 결과적으로 **Combine함수를 사용하게 되면 더욱 빠르게 MapReduce 알고리즘이 돌아가게 된다.**
  - 각 머신에서 Reduce함수가 하는 일을 부분적으로 수행해준다.
- Reduce함수
  - org.apache.hadoop.mapreduce 패키지의 Reducer클래스를 상속받아 reduce method를 수정.
  - Shuffling Phase의 출력을 key, value형태의 입력으로 받는다.
  - value-list는 Map함수의 출력에서 **key를 가지는 key, value쌍들의 value list**

#### Mapper와 Reducer는 필요에의해 setup()과 cleanup()을 수행한다.

- setup() : Map함수나 Reduce함수가 호출되기 전 수행된다.
  - 모든 Map 함수에게 Broadcast해서 **전달해야할 파라미터 정보를 Main함수에서 받아온다.**
  - 모든 Map함수들이 공유하는 **자료구조를 초기화**한다.
- cleanup() : Map 함수나 Reduce 함수가 끝나고 나면 수행된다.
  - 모든 Map함수들이 공유하는 **자료구조의 결과를 출력**하는데 사용.

#### MapReduce 입출력에 사용가능한 Default클래스

- MapReduce 입출력에 사용하는 타입들은 Suffling Phase에서 정렬하는데 필요한 비교함수 등 여러 함수로 이미 정의되어 있다.
  - Hadoop의 MapReduce의 Map함수, Reduce함수, Combine함수 등에서 입출력에 사용할 수 있는 클래스와 해당되는 자바 타입
    - Test : string
    - IntWritable : int
    - LongWritable : long
    - FloatWritable : float
    - DoubleWritable : double
- 만일 새로운 클래스를 정의하여 입출력에 사용하고 싶다면 필요한 여러 함수도 함께 정의해주어야 한다.

## Hadoop

> Apache 프로젝트의 오픈소스 형태의 MapReduce 프레임워크

빅데이터를 수천대의 값싼 컴퓨터에 병렬처리하기 위해 분산처리.

Java로 MapReduce 알고리즘 구현.

#### Hadoop의 주요 구성요소

- MapReduce - 소프트웨어의 수행을 분산
- `HDFS` - 데이터를 분산

#### 하둡분산파일시스템(HDFS; Hadoop Distributed File System)

- 빅데이터 파일을 여러 대의 컴퓨터에 나누어 저장함.
- 각 파일은 여러 개의 순차적 블록으로 저장함.
- 파일의 블록은 fault tolerance를 위해 같은 블록이라도 여러개로 복사되어 다양한 머신에 저장됨.

```shell
[참고] fault tolerance
시스템을 구성하는 부품의 일부에서 결함 또는 고장이 발생해도 정상적 혹은 부분적으로 기능을 수행할 수 있는 것.
```

#### 한 개의 Namenode(master)와 여러 개의 Datanode(slaves)

- Namenode: 파일 시스템을 관리하고 client가 파일에 접근할 수 있게 함.
- Dantanode: 컴퓨터의 데이터에 접근할 수 있게 함.

[![img](/mymysuzy0627/bootcamp2/uploads/557cf247b226dd4338cb1e791a9a8d1c/image.png)]()

------

## 필요한 프로그램 설치

### [VMware 설치](https://www.vmware.com/products/workstation-player.html)

> Window OS에서 UNIX OS를 사용하기 위해 VMware설치

> UNIX OS 중 오픈소스인 Linux OS를 사용할 것임.

> 즉, Window OS에서 Linux OS를 Virtual하게 돌리기 위해 VMware를 설치!

```shell
[참고] UNIX와 Linux
UNIX OS : 하이앤드머신에 쓰던 OS
Linux OS : 사람들이 사용할 수 있도록 오픈소스로 만든 UNIX버전.
```

### [VMware에 Ubuntu설치](https://ubuntu.com/download/desktop)

> Linux의 많은 버전 중 가장 대중적인 Ubuntu를 설치하자.

**[참고]**

- [VMware에 Ubuntu 설치 에러 # Your host does not meet minimum requirements to run VMware Player with Hyper-V or Device/Credential Guard enabled.](https://pythontoomuchinformation.tistory.com/426)
- [ssh localhost 오류 # ssh: connect to host localhost port 22: Connection refused](https://pythontoomuchinformation.tistory.com/427)

------

## Linux와 HDFS

> 데이터 생성이나 코딩은 Linux에서 하고 MapReduce 코드와 입력 데이터는 HDFS에 옮겨 MapReduce알고리즘을 수행한다.

### Linux 디렉토리 구성

- ```
  src
  ```

  / 디렉토리 (MapReduce코드)

  - **Driver.java**(MapReduce코드 컴파일 위해 코드 추가해줘야하는 파일)
  - **Wordcount.java**

- `template`/ 디렉토리 (과제를 위한 template)

- `datagen`/ 디렉토리 (과제 데이터를 생성하기 위한 코드)

- ```
  data
  ```

  / 디렉토리 (과제를 위한 데이터)

  - wordcount-data.txt (wordcount에 쓰일 데이터 파일)

- `build.xml` 파일 (MapReduce코드 컴파일 해주는 파일)

```shell
MapReduce코드를 짜려면 template 디렉토리의 template을 가져다 코딩한다.
코딩한 것은 src디렉토리에 넣어두어 컴파일을 수행한다.
```

### Hadoop 디렉토리 구성

> Hadoop 코드 즉 MapReduce를 실행시키려면 HDFS에 옮겨야 한다.

- wordcount_test/ (MapReduce코드 실행을 위한 데이터 디렉토리)
  - Linux디렉토리의 wordcount-data.txt파일을 가져다 넣은 다음 MapReduce실행하게됨.
- wordcount_test_out/ (MapReduce코드 실행 결과를 저장하는 디렉토리)

```shell
wordcount_test 디렉토리에 wordcount-data.txt을 가져다 넣은 다음 MapReduce 코드를 실행.
코드의 결과는 wordcount_test_out 디렉토리에 써진다.
```

### Project/src/Driver.java 파일 수정

- **pgd.addClass("`wordcount`", Wordcount.class, "A map/reduce program that perform word counting.");**
- src 디렉토리에 새로운 코드를 만들 때 마다 src 디렉토리에 있는 Driver.java파일에 pgd.addClass를 새로 하나 넣어 주어야 한다.
- **Driver.java 파일이 바뀌면 컴파일을 다시 해야하기 때문에 반드시 `ant`를 다시 수행해야 한다.**
- wordcount MapReduce알고리즘을 실행할 때 pgd.addClass에서 설정해준 이름(wordcount)으로 실행해줘야한다. **hadoop jar ssafy.jar `wordcount` wordcount_test wordcount_test_out**

```shell
[참고] ant
 - Unix의 make과 같은 것.
 - Java 개발 환경에서의 표준 빌드 도구.
 - 자바 파일을 컴파일 하는 Javac도 있지만, ant는 여러 dependency를 고려해 소스파일을 컴파일.
 - src 디렉토리에 있는 것을 모두 모아 컴파일 한 후 ssafy.jar를 생성.
 - Project 디렉토리에 있는 build.xml파일에 정의한 대로 수행된다.
```

### 테스트 데이터를 HDFS에 넣어야 함

- **$ cd /home/hadoop/Project**

- **$ hdfs dfs -ls ** hadoop 계정의 HDFS상에 아무 디렉토리나 파일이 없음을 확인하자.

- **$ hdfs dfs -mkdir /user** 없음을 확인하고 HDFS상의 최상위 폴더인 user를 생성해주자.

- **$ hdfs dfs -mkdir /user/hadoop** HDFS의 user폴더 안에 hadoop폴더를 생성해주자.

  이제 hadoop의 폴더에 새로운 디렉토리를 생성할 준비가 되었다.

- **$ hdfs dfs -mkdir wordcount_test** 하둡의 HDFS에 wordcount_test라는 디렉토리를 생성하라는 명렁어.

- **$ hdfs dfs -put data/wordcount-data.txt wordcount_test** Linux의 data디렉토리에 있는 wordcount_data.txt파일을 하둡 HDFS의 wordcount_test디렉토리에 보냄.

- 반드시 MapReduce프로그램결과를 저장할 디렉터리를 삭제한 후 프로그램을 실행해야한다.

  **$ hdfs dfs -rm -r wordcount_test_out**

### Wordcount MapReduce 알고리즘 코드 실행

- **$ cd /home/hadoop/Project/**
- **$ hadoop jar ssafy.jar wordcount wordcount_test wordcount_test_out**
  - Driver.java에 wordcount라는 단어로 실행시키겠다고 지정해두었으니 wordcount를 통해 MapReduce를 수행.
  - ssafy라는 이름의 jar로 만들 것이다.
  - Wordcount_test 디렉토리에 든 모든 파일을 맵 함수의 입력으로 사용
- Hadoop의 실행 방법 $ hadoop jar [jar file] [program name] <input arguments ...>

### 결과 확인

> reducer 개수를 2개 사용하면 2개의 출력파일이 생성됨

- $ hdfs dfs -cat wordcount_test_out/part-r-0000`0`|more
  - 0번 reducer가 출력한 파일의 내용을 보여줌
- $ hdfs dfs -cat wordcount_test_out/part-r-0000`1`|more
  - 1번 reducer가 출력한 파일의 내용을 보여줌

```shell
cat : 파일을 찍어보는 명령어.
more : 파일 전체를 보여주는 것이 아니라 하나에 찰 수 있는 일부 화면만 보여달라.
```

# 정리) 새로운 MapReduce코드를 컴파일하는 방법

- 소스코드파일을 Project/src/ 디렉토리에 넣는다.
- Project/scr 디렉토리의 Driver.java파일에 아래양식으로 라인을 추가해준다. pgd.addClass("program name", class name, "description");
- Project 디렉토리에서 ant 명령어를 반드시 수행하여 컴파일 시켜주자.
- 이전에 이미 MapReduce를 돌려서 wordcount_test_out파일이 생성된 상태라면 삭제해주자. **$ hdfs dfs -rm -r wordcount_test_out**
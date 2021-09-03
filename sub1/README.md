## Sub1 개인 프로젝트 산출물 실행 방법

---

### 1. SSAFY에서 제공한 방식으로 ubuntu 및 하둡 설치

- 에듀싸피 특화 프로젝트 도메인 별 사전학습 1강 참고
- 이 과정에서 hadoop 디렉토리 생성까지 완료되어야 함.

---

### 2. 팀원별 브랜치 및 날짜 디렉토리로 이동

- 팀원 - 브랜치명

    김성규 - kskyu610

    김은지 - rladmswlek

    김정욱 - jukim10101

    박소현 - mymysuzy0627

    오미정 - ks03102

    황성안 - sjd0051

![Untitled](https://lab.ssafy.com/s05-bigdata-dist/S05P21C103/-/blob/develop/sub1/images/%EB%B8%8C%EB%9E%9C%EC%B9%98_%EB%B0%8F_%EB%94%94%EB%A0%89%ED%86%A0%EB%A6%AC_%EC%9D%B4%EB%8F%99.png)

### 3. 우분투에 java 파일 다운로드 후 ssafy에서 제공한 Project의 src 폴더로 이동

![Untitled](https://lab.ssafy.com/s05-bigdata-dist/S05P21C103/-/blob/develop/sub1/images/%ED%8C%8C%EC%9D%BC_%ED%99%95%EC%9D%B8.png)

---

### 4. 테스트 데이터 설정

```bash
$ cd /home/hadoop/Project

// wordCount1Char, wordCountSort, InvertedIndex 테스트 데이터 설정 명령어
$ hdfs dfs -mkdir wordcount_test
$ hdfs dfs -put data/wordcount-data.txt wordcount_test

// MatrixAddition 테스트 데이터 설정 명령어
$ hdfs dfs -mkdir matadd_test
$ hdfs dfs -put data/matadd-data-2x2.txt matadd_test
```

---

### 5. Driver.java에서 실행할 MapReduce의 별칭 확인

터미널에서 `vi Driver.java`혹은 파일 탐색기에서 Driver.java를 더블클릭하여 `addClass`메서드의 첫번째 파라미터 확인 (이후 CLI에서 사용)

![Untitled](https://lab.ssafy.com/s05-bigdata-dist/S05P21C103/-/blob/develop/sub1/images/Driver_java.png)

---

### 6. MapReduce 알고리즘 실행

```bash
$ cd /home/hadoop/Project

// wordCount1Char, wordCountSort, InvertedIndex 알고리즘 실행
$ hdfs dfs -rm -r wordcount_test_out
$ hadoop jar ssafy.jar [5.에서 확인한 MapReduce의 별칭] wordcount_test wordcount_test_out

// matrixAddition 알고리즘 실행
$ hdfs dfs -rm -r matadd_test_out
$ hadoop jar ssafy.jar [5.에서 확인한 MapReduce의 별칭] matadd_test matadd_test_out
```

---

### 7. MapReduce 알고리즘 결과 확인

```bash
// wordCount1Char, wordCountSort, InvertedIndex 알고리즘 첫번째 Reducer 결과 확인
$ hdfs dfs -cat wordcount_test_out/part-r-00000

// wordCount1Char, wordCountSort, InvertedIndex 알고리즘 두번째 Reducer 결과 확인
$ hdfs dfs -cat wordcount_test_out/part-r-00001

// matrixAddition 알고리즘 첫번째 Reducer 결과 확인
$ hdfs dfs -cat matadd_test_out/part-r-00000

// matrixAddition 알고리즘 두번째 Reducer 결과 확인
$ hdfs dfs -cat matadd_test_out/part-r-00001
```

📢 각각의 MapReduce 별 Reducer를 2개만 사용했기 때문에 결과 또한 2개로 생성됨

📢 각각의 날짜 별 프로젝트 산출물을 2~7 과정을 반복하여 확인

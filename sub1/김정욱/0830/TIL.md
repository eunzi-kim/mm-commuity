## 특화PJT - 광주 1반 3팀 빅데이터#2(분산처리)
# 프로젝트 개요

- 병렬 분산 시스템과 맵리듀스 프레임워크 이해하고 하둡을 이용하여 여러 빅데이터 분석 문제들에 대해서 맵리듀스 알고리즘을 자바 언어로 구현하고 실행한다.

# 프로젝트 목표

- 병렬 분산 알고리즘 구현이 가능한 맵리듀스 프레임워크 이해한다.
- 맵리듀스 프레임워크를 사용할 수 있는 Hadoop 설치 및 맵리듀스 알고리즘 코드를 실행한다.
- 하둡을 이용하여 빅데이터 분석 및 처리용 맵리듀스 알고리즘을 구현하는데 필요한 지식과 코딩 능력을 배양한다.

# 왜 병렬분산 알고리즘?

## Scaling-out is superior to Scaling-up

### Scale-out

아주 많은 값싼 서버들을 이용함

### Scale-up

적은 수의 값비싼 서버들을 이용함

- 데이터 중심(data-intensive) 어플리케이션 분야에서는 아주 많은 값싼 서버들을 많이 이용하는 것을 선호함
- 고가의 서버들은 가격에 관점에서는 선형으로 성능이 증가하지 않음
    - 두 배의 성능의 프로세서 한 개를 가진 컴퓨터의 가격이 일반적인 프로세서 한 개를 가진 컴퓨터 가격의 두 배보다 훨씬 더 비쌈

# Why MapReduce?

- 데이터 중심 프로세싱(Data-intensive processing)
    - 한 대의 컴퓨터의 능력으로 처리가 어려움
    - 근본적으로 수 십대, 수 백대 혹은 수 천대의 컴퓨터를 묶어서 처리해야함
    - 맵리듀스(MapReduce) 프레임 워크가 하는 바로 이것!
- 맵리듀스는 빅데이터를 이용한 효율적인 계산이 가능한 첫 번째 프로그래밍 모델
    - 기존에 존재하는 여러 가진 다른 병렬 컴퓨팅 방법에서는 프로그래머가 낮은 레벨의 시스템 세부 내용까지 아주 잘 알고 많은 시간을 쏟아야만 함

# MapReduce Framework

- 빅데이터를 이용하는 응용분야에서 최근에 주목 받고 있음
- 값싼 컴퓨터들을 모아서 클러스터를 만들고 여기에서 빅 데이터를 처리하기 위한 스케일러블(Scalable) 병렬 소프트웨어의 구현 쉽게 할 수 있도록 도와주는 간단한 프로그래밍 모델
    - scalable하다 : 많은 사용자 수가 급증하거나 데이터가 급증해도 프로그램이 멈추거나 성능이 크게 떨어지는 일이 없다는 뜻
- 구글의 맵리듀스(MapReduce) 또는 오픈소스인 하둡(Hadoop)은 맵리듀스 프레임워크(MapReduce Framework)의 우수한 구현의 형태임
- 드라이버에 해당하는 메인 함수가 맵(map)함수와 리듀스(reduce)함수를 호출해서 처리

# MapReduce Programming Model

- 함수형 프로그래밍(Functional programming) 언어의 형태
- 유저는 아래 3가지 함수를 구현해서 제공해야 함
    - Main 함수
    - Map 함수: (key1, val1) → [(key2, val2)]
    - Reduce 함수: (key2, [val2]) → [(key3, val3)]

# MapReduce Framework

- 맵리듀스 프레임워크에서는 각각의 레코드(record) 또는 튜플(tuple)은 키-밸류(KEY, VALUE) 쌍으로 표현된다.
- 맵리듀스 프레임워크는 메인(main) 함수를 한 개의 마스터 머신(master machine)에서 수행하는데 이 머신은 맵 함수를 수행하기 전에 전처리를 하거나 리듀스 함수의 결과를 후처리 하는데 사용될 수 있다.
- 컴퓨팅은 맵(map)과 리듀스(reduce)라는 유저가 정의한 함수 한 쌍으로 이루어진 맵리듀스 페이즈(phase)를 한 번 수행하거나 여러 번 반복해서 수행할 수 있다.
- 한번의 맵리듀스 페이즈는 맵 함수를 먼저 호출하고 그 다음에 리듀스 함수를 호출하는데 때에 따라서는 맵 함수가 끝난 후에 컴바인(combine) 함수를 중간에 수행할 수 있다.
- 드라이버에 해당하는 메인(main) 프로그램에서 맵리듀스 페이즈를 수행시킨다.

# MapReduce Phase(3단계로 수행)

## Map Phase

- 제일 먼저 수행되며 데이터의 여러 파티션(partition)에 병렬 분산으로 호출되어 수행된다.
- 각 ㅓ신마다 수행된 Mapper는 맵 함수가 입력 데이터의 한 줄마다 맵(Map) 함수를 호출한다.
- Map 함수는 (KEY, VALUE)쌍 형태로 결과를 출력하고 여러 머신에 나누어 보내며 같은 KEY를 가진(KEY, VALUE) 쌍은 같은 머신으로 보내진다.

## Shuffling Phase

- 모든 머신에서 맵 페이즈가 다 끝나면 시작된다.
- 맵 페이즈에서 각각의 머신으로 보내진 (KEY, VALUE) 쌍을 KEY를 이용해서 정렬(Sorting)을 한 후에 각각의 KEY 마다 같은 KEY를 가진 (KEY, VALUE) 쌍을 모아서 밸류-리스트(VALUE-LIST)를 만든 다음에 (KEY-VALUE-LIST) 형태로 KEY에 따라서 여러 머신에 분산해서 보낸다.

## 리듀스(Reduce) 페이즈

- 모든 머신에서 셔플링 페이즈가 다 끝나면 각 머신마다 리듀스 페이즈가 시작된다.
- 각각의 머신에서는 셔플링 페이즈에서 해당 머신으로 보내진 각각의 (KEY, VALUE-LIST) 쌍 마다 리듀스 함수가 호출되며 하나의 리듀스 함수가 끝나면 다음 (KEY, VALUE-LIST) 쌍에 리듀스 함수가 호출된다.
- 출력이 있다면 (KEY, VALUE) 쌍 형태로 출력한다.

## Hadoop

- Apache 프로젝트의 맵리듀스 프레임워크의 오픈 소스
- 하둡 분산 파일 시스템(Hadoop Distributed File System - HDFS)
    - 빅 데이터 파일을 여러 대의 컴퓨터에 나누어서 저장함
    - 각 파일은 여러 개의 순차적인 블록으로 저장함
    - 하나의 파일의 각각의 블록은 폴트 톨러런스(fault tolerance)를 위해서 여러 개로 복사되어 여러 머신의 여기저기 저장됨
        - 폴트 톨러런스(fault tolerance)는 시스템을 구성하는 부품의 일부에서 결함(fault) 또는 고장(failure)이 발생하여도 정상적 혹은 부분적으로 기능을 수행할 수 있는 것을 말함
    - 빅 데이터를 수천 대의 값싼 컴퓨터에 병렬 처리하기 위해 분산함

- 주요 구성 요소들
    - MapReduce - 소프트웨어의 수행을 분산함
    - Hadoop Distributed File System(HDFS) - 데이터를 분산함
- 한 개의 Namenode (master)와 여러 개의 Datanode (slaves)
    - Namenode
        - 파일 시스템을 관리하고 클라이언트가 화일에 접근할 수 있게 함
    - Datanode
        - 컴퓨터에 들어있는 데이터를 접근할 수 있게 함
- 자바 프로그래밍 언어로 맵리듀스 알고리즘을 구현

# 설치

1. VMware 다운로드
2. Ubuntu 설치
3. Hadoop 설치

## Hadoop 설치

- ubuntu 에서 Applications > Accessories > Terminal ( or, Ctrl + Alt + t)
- 학생들의 편의를 위해 패키징된 파일을 다운로드

```java
wget http://kdd.snu.ac.kr/~kddlab/Project.tar.gz
```

- 설치
    - tar zxf Project.tar.gz → 디렉토리가 만들어짐
    - sudo chown -R hadoop:hadoop Project
    - cd Project
    - sudo mv hadoop-3.2.2 /usr/local/hadoop
    - sudo apt update
    - sudo apt install ssh openjdk-8-jdk ant -y
    - ./set_hadoop_env.sh
    - source ~/.bashrc

# Hadoop 실행을 위한 준비

- 비밀번호 안 치고도 실행할 수 있도록 Empty 'ssh key' generation
    - $ ssh-keygen -t rsa -P ""
        - 저장할 파일을 물어보면 default로 enter만 친다.
    - $ cat $HOME/.ssh/id_rsa.pub >> $HOME/ssh/autorhized_keys
    - 제대로 생성되었는지 확인
        - $ssh localhost
        - 질문이 뜨면 yes를 입력하고
        - 그 다음 비밀번호를 물어보지 않고 prompt가 뜨면 성공
- 모든 명령은 hadoop 계정에서!
    - Path를 지정하기 위해 /home/hadoop에서 source .bashrc를 실행
- Name node format
    - Diskformat과 같은 개념
    - $ hadoop namenode -format
- Dfs daemon start
    - $start-dfs.sh
- MapReduce daemon start (standalone 모드에서는 불필요)
    - $start-mapred.sh
- 확인
    - 수행중인 java 프로세스 리스트를 확인한다
    - $jps
        - SecNameNode
        - ondaryNameNode
        - DataNode
        - TaskTracker (Standalone에서는 불필요)
        - JobTracker (Standalone에서는 불필요)
- hadoop 계정의 HDFS 상에 아무 디렉토리나 파일이 없음
    - $hdfs dfs -ls /
- hadoop 계정의 HDFS상에서의 맨 위에 user 디렉토리를 생성
- hadoop 계정의 HDFS상에서 /user 디렉토리 안에 hadoop 디렉토리 생성
    - $hdfs dfs -mkdir /user/hadoop

# Linux와 HDFS

- 데이터 생성이나 코딩은 Linux에서 하고 MapReduce 코드와 입력 데이터는 HDFS에 옮겨서 MapReduce 알고리즘을 수행한다.
- Linux 디렉토리
    - src/ (맵리듀스 코드)
        - [Driver.java](http://driver.java) (맵리듀스 코드 컴파일을 위한 파일)
        - Wordcount.java
    - template/ (과제를 위한 template)
    - datagen/ (과제 데이터를 생성하기 위한 코드)
    - data/ (과제를 위한 데이터)
        - wordcount-data.txt
    - build.xml (맵리듀스 코드 컴파일을 위한 파일)
- Hadoop 디렉토리
    - wordcount_test/ (맵리듀스 코드 실행을 위한 데이터 디렉토리)
    - wordcount_test_out/ (맵리듀스 코드 실행 결과를 저장하는 디렉토리)
- 데이터 생성이나 코딩은 Linux에서 하고 MapReduce 코드와 입력 데이터는 HDFS에 옮겨서 MapReduce 알고리즘을 수행한다.

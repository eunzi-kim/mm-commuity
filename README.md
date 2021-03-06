
<img src="images/logo.png" width="300" height="200" align="left" /> 

| About 알쓸싸잡🔎                                          |
| :----------------------------------------------------------- |
| [팀 Notion](https://nolluwa.notion.site/SSAFY-PJT-0f1a14188d254d4c80bce7bd731f76e6) |
| [요구사항 명세서](https://nolluwa.notion.site/a85649b7811a400b9c5597c60be9a432?v=1a1dc73d844e407ab1beeac9cbe3c0bf) |
| [아키텍쳐](https://nolluwa.notion.site/cc307afc3fb54a58a07f4325de4535a2) |
| [API문서](https://nolluwa.notion.site/353d2623aec84f11ac9035ba517c8384?v=643e05e53b78420f82f6d3dfca85a7c2) |
| [ERD](https://nolluwa.notion.site/DB-5b48d46642014b2fbe347aa84c38a2ed) |  


# 알쓸싸잡 - 알아두면 **쓸**모있는 **싸**피 **잡**학사전

# 팀 구성🙋‍

- Back-End : 김성규(팀장), 김정욱, 박소현, 오미정
- Front-End : 김은지, 황성안

# 기술 스택⚒

- FE : React
- BE : Spring Boot / Spring Data JPA / Spring Data Redis / Spring Batch
- Big Data : Hadoop HDFS / Hadoop MapReduce / Kafka
- DB : MariaDB / HBase / Redis
- Infra : AWS EC2 /Nginx / Jenkins / Docker
- Mattermost : mattermost4j

# **문제 상황💢**

### **[Matter Most]📨**

- SSAFY의 MM 채널에 **매일 많은** 게시글이 등록되지만, **잦은 알림**으로 인해 채널의 알림을 꺼놔 **그 날** 등록된 게시글을 놓치는 경우가 많다.
- 원하는 게시글을 찾고자 하더라도 채널을 하나하나 확인해야하는 불편함이 있다.

### **[SSAFYcial 기사]📰**

- SSAFYcial에 좋은 기사들이 많지만 **접근성이 좋지 않아** 놓치는 기사들이 많다.
- SSAFYcial 기자단분들이 2주 간격으로 블로그에 게시하지만 **에듀싸피에는 제때 게시되지 않는다.**

# **해결 방안💡**

- 일괄처리에 적합한 **Hadoop Map-Reduce** 프레임워크를 이용해 다음과 같은 해결 방안을 서비스로써 제공한다.
- **매일** MM에 등록된 게시글을 정리하여 교육생들에게 제공한다.
- SSAFYcial 기사가 게시되는 주기인 **2주 단위**로 SSAFYcial 기사를 교육생들에게 제공한다.
- 추가적으로, **주 단위**로 게시글을 많이 등록한 교육생, 게시글에 반응을 많이 한 교육생, 많은 반응을 얻은 교육생을 우수 교육생으로 선발한다.

    ⭐ 반응의 척도 : **이모티콘**과 **댓글**의 수
- SSAFY의 교육프로 계정은 교육생들이 MM에서 활동한 이력을 확인할 수 있다.


# **주요 서비스**
<img src="images/main-service.jpg" width="800" height="400">

# **시스템 아키텍쳐**
<img src="images/architecture.jpg" width="1000" height="500">

# References
- Mattermost API : https://api.mattermost.com
- Mattermost4j GitHub : https://github.com/maruTA-bis5/mattermost4j
- Mattermost4j Javadoc : https://www.javadoc.io/doc/net.bis5.mattermost4j/mattermost4j-core/0.4.0/net/bis5/mattermost/client4/MattermostClient.html

 
    
    

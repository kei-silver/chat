# chat project
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.11-brightgreen?style=flat&logo=spring)](https://spring.io/projects/spring-boot)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.6.21-orange?style=flat&logo=kotlin)](https://kotlinlang.org/)
[![Spring Kafka](https://img.shields.io/badge/Spring%20Kafka-latest-blue?style=flat&logo=spring)](https://spring.io/projects/spring-kafka)
[![Spring Cloud Netflix Eureka Client](https://img.shields.io/badge/Spring%20Cloud%20Netflix%20Eureka%20Client-3.1.7-blue?style=flat&logo=spring)](https://spring.io/projects/spring-cloud-netflix)
![Resilience4j](https://img.shields.io/badge/Resilience4j-2.1.7-yellow?style=flat)
[![Spring Boot Freemarker](https://img.shields.io/badge/Spring%20Boot%20Freemarker-latest-brightgreen?style=flat&logo=spring)](https://projects.spring.io/spring-boot)
[![Vue.js](https://img.shields.io/badge/Vue.js-2.5.16-brightgreen?style=flat&logo=vue.js)](https://vuejs.org/)


Spring boot + websocket + Stomp를 사용한 채팅 서버 토이 프로젝트
------------

## 실습 환경 

프로젝트를 실행하기 위해서는 다음 사전 요구 사항이 필요:


- Java 17 이상
- MongoDB
- Redis
- Apache Kafka
- Eureka Server (Eureka 서비스 디스커버리)

## 실습 내용

### 부하 테스트 및 서버 개선
동시 접속자 1,000명과 초당 트랜잭션 3,000건(TPS 3000) 가정한 부하 테스트를 진행
- [성능 테스트 -  웹소켓 테스트 구성](https://purple-drill-6fe.notion.site/61acebbeefc44ca99f5aa2472aa1434d?pvs=4)
- [성능 테스트 - 멀티스레드와 웹소켓 세션 타임아웃 ](https://purple-drill-6fe.notion.site/0dbea9c8c318423db6de0862fab5005f?pvs=4)
- [성능 테스트 - 웹소켓 세션 타임아웃 해결편 ](https://purple-drill-6fe.notion.site/4cb3949c4bab40f0834fcd195700e0ab?pvs=4)

### MSA 기반 아키텍처 디자인 패턴
MSA를 기반으로 한 아키텍처 디자인 패턴을 구현하고 테스트 진행
- [MSA - 서킷 브레이커 패턴 - 이론편](https://purple-drill-6fe.notion.site/MSA-7c346ba6a1a748838aee7f0189ac113d?pvs=4)
- [MSA - 서킷 브레이커 - 실습편](https://purple-drill-6fe.notion.site/MSA-9020b17e22a44ac187f191d2a198868e?pvs=4)
- [MSA - 서비스 디스커버리 패턴 구현 ](https://purple-drill-6fe.notion.site/MSA-b2eb362f078e4b308e8566196c0ff8e4?pvs=4)

### 실시간 메시지 동기화 처리
Redis Pub-Sub 메커니즘을 활용하여 서버 간 실시간 메시지 동기화 처리를 구현
- [Redis pub-sub을 이용한 서버 간의 메시지 동기화 ](https://purple-drill-6fe.notion.site/Redis-pub-sub-a1d9d012c4a8493cb0dafc3eec2fb7f2?pvs=4)

### 실시간 데이터 스트림 처리
Apache Kafka를 활용하여 실시간 데이터 스트림의 고속 처리 및 저장을 구현
- [MongoDB Kafka Connector로 데이터 저장하기-1](https://purple-drill-6fe.notion.site/MongoDB-Kafka-Connector-1-3a433efea0b4483782dae6932dc256f7?pvs=4)
- [MongoDB Kafka Connector로 데이터 저장하기-2](https://purple-drill-6fe.notion.site/MongoDB-Kafka-Connector-2-1ae07069501d47d9b81eed8892c53759?pvs=4)

## 접속 방법 및 인프라 구성

1. **Eureka 서버 시작**
   - 채팅 서버는 Eureka Client로 등록되어 있음
   - Eureka 서비스 디스커버리를 사용하기 위해 Eureka 서버를 시작해야 함.
   - Eureka 서버를 시작하려면 다음 URL로 이동: [https://github.com/kei-silver/eureka-server](https://github.com/kei-silver/eureka-server)

2. **채팅 서버 테스트 환경 구성**
    - 채팅 서버를 테스트하려면 먼저 필수 인프라 구성을 위해 `docker-compose.yml` 파일을 실행

3. **채팅 서버 구동**

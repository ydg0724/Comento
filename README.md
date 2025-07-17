# Comento

## 1주차 개발환경 세팅

1. 소프트웨어 설치
   - IntelliJ 설치 (완료)
   - mariaDB 설치 (완료)
      - V11.8
   - Debeaver 설치 (완료)
    
2. Spring 환경 설정
   - 프로젝트 생성 (완료)
      - JDK 11
   - maven 설정 (완료)

3. DB 연동
   - dependency 추가 (완료)
   - DAO, Controller, service 추가 (완료)

4. 테스트
   - /ping 테스트 (완료)
  
   ![image](https://github.com/user-attachments/assets/e1950a0d-ddb2-4bdd-bf11-e174747c6034)
   - /requests 테스트 (완료)

   ![image](https://github.com/user-attachments/assets/3d068907-c4b6-455e-8479-6b767a2bd2f9)


## 2주차 인터페이스 가이드 문서 작성

1. REST API <br>
   REST란 "Representational State Transfer" 약자이며, 자원의 상태를 주고받는 것이다. 즉, 자원에 접근하는 방식을 정해놓은 규칙이다. <br>
   REST API는 보통 HTTP를 기반으로 구현된다.
   - REST 설계 원칙
     - 자원 중심: URI 자체가 자원을 나타내며 /users, /product 등 명사 형태로 설계된다.
     - HTTP 메서드 기반: GET, POST, PUT, DELETE 등 HTTP 메서드를 사용하여 자원에 대한 CRUD 작업을 수행한다.
     - 무상태성(stateless): 요청 간에 서버가 클라이언트의 상태를 저장하지 않아서 모든 요청은 독립적이어야한다.
     - 계층화 구조: REST 시스템은 중간 서버(프록시, 로그밸런서 등)을 포함할 수 있다.
     - 캐시 처리 가능: HTTP의 캐시 기능을 사용 가능하다.
     - 일관된 인터페이스: 자원 접근 방식은 일관되어야 하며 모든 API는 비슷한 형식으로 동작해야함

   - RESTful API: REST의 원칙을 철저하게 준수한 API
   

2. HTTP 통신 <br>
   HTTP(HyperText Transfer protocol)는 텍스트 기반의 통신 규약으로 인터넷에서 데이터를 주고받을 수 있는 프로토콜이다.
   클라이언트-서버 모델을 따르며, 애플리케이션 레벨의 프로토콜로 TCP/IP위에서 작동한다.

   - 특징
     - 클라이언트-서버 구조
       <br>클라이언트가 서버에 요청을 보내면, 서버가 요청에 대한 응답을 보내는 구조로 이루어져 있다. (단방향 통신)
     - 무상태 프로토콜(stateless)
       <br>HTTP에서는 서버가 클라이언트의 상태를 보존하지 않는다. 즉, 응답과 요청이 독립적이다.
     - 비연결성(Connectionless)
       <br>HTTP에서는 실제로 요청을 주고받을 때만 연결을 유지하고 응답을 주고마녀 TCP/IP 연결을 끊는다.
       
3. 브라우저 URL 요청 후 응답까지의 과정 <br>
   (1) URL 입력 <br>
      - 사용자가 브라우저 주소창에 http://www.example.com/users/1 입력한다고 했을 때 브라우저는 다음과 같이 분석 <br>
    - 프로토콜: http <br>
    - 도메인: www.example.com <br>
    - URI(경로): users/1 (GET 요청)
   
   (2) DNS 조회 <br>
   브라우저는 도메인을 실제 IP주소로 변환하기 위해 DNS서버에 요청하여 어디로 요청 보낼지 결정<br>
    - www.example.com -> 93.184.216.34  <br>
   
   (3) TCP 연결<br>
   - 브라우저는 해당 IP주소의 서버에 연결하기 위해 TCP 연결 시도 (3-way HandShake)<br>
   
   (4) HTTP 요청<br>
   - 브라우저는 서버에 HTTP요청 전송<br>
   GET / HTTP/1.1<br>
   Host: www.example.com

   (5) 서버 처리<br>
   - 서버에서 요청 내용을 분석 후 해당하는 자원(Json 등)을 찾아 처리<br>
   
   (6) HTTP 응답<br>
   - 서버는 HTTP 응답을 보냄<br>
   HTTP/1.1 200 OK <br>
   Content-Type: text/html

   (7) 브라우저 렌더링<br>
   - 브라우저는 응답을 받아서 사용자가 보는 웹페이지 완성
  
## 3주차 간단한 RESTful API 구현

1. spring boot 환경설정
   - https://start.spring.io/ 에서 프로젝트 생성 (완료)
        - Maven
        - JDK 17
        - Spring Boot 3.5.3
   - IDE에서 프로젝트 불러오기 (완료)
   - Ping API 작성 (완료)
   <img width="249" height="45" alt="image" src="https://github.com/user-attachments/assets/7850d382-3d11-40f1-81ab-373c82799f43" />

2. 년도 로그인수 API 구현
   - pom.xml에 dependency 추가 (완료)
        - mybatis 3.0.3
        - mariaDB jdbc 2.3.0
        - junit 4.13.1
   - application.properties에 datasource 추가 (완료)
   - mapper 구현 (완료)
   - service 구현 (완료)
   - Controller 구현 (완료)

SQL

- 월별 접속자 수
```sql

SELECT 
  DATE_FORMAT(login_date, '%Y-%m') AS month,
  COUNT(DISTINCT user_id) AS visitor_count
FROM login_logs
WHERE login_date BETWEEN :startDate AND :endDate
GROUP BY DATE_FORMAT(login_date, '%Y-%m')
ORDER BY month;

```
- 일자 별 접속사 수
```sql
SELECT 
  DATE(login_date) AS date,
  COUNT(DISTINCT user_id) AS visitor_count
FROM login_logs
WHERE login_date BETWEEN :startDate AND :endDate
GROUP BY DATE(login_date)
ORDER BY date;

```

- 평균 하루 로그인 수
```sql
SELECT 
  ROUND(COUNT(*) / COUNT(DISTINCT DATE(login_date)), 2) AS avg_daily_login_count
FROM login_logs
WHERE login_date BETWEEN :startDate AND :endDate;
```
- 휴일을 제외한 로그인 수

- 부서별 월별 로그인 수
```sql
SELECT 
  department,
  DATE_FORMAT(login_date, '%Y-%m') AS month,
  COUNT(*) AS login_count
FROM login_logs
WHERE login_date BETWEEN :startDate AND :endDate
GROUP BY department, DATE_FORMAT(login_date, '%Y-%m')
ORDER BY department, month;

```


   
       


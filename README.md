# ShareFit(패션 SNS)
- **기간 : 2024년 07월 - ~ 2024년 08월**
- **담당 : 백엔드 API 개발**
- **참여 인원 : 2명(프론트엔드 1, 백엔드 1)**
- **개발 환경**
    - **언어 : Java 17**
    - **프레임워크 : Spring Boot 3.3.1, Spring Security, Spring Data JPA, QueryDSL**
    - **데이터베이스 : MySQL, Redis**
    - **도구 : Docker, nGrinder, Giuhub Actions**
    - **AWS : EC2, S3, CodeDeploy, RDS, Elastic Load Balancing, Route 53**
- **주요 구현**
    - **Java, Spring Boot를 이용한 RESTful API 설계**
    - **Spring Data JPA, QueryDSL 적용**
    - **Spring Security, JWT를 활용한 토큰 방식 인증/인가 적용**
    - **Github Actions, AWS S3, AWS Codedeploy를 활용하여 자동 배포 파이프라인 구현**
    - **캐시를 활용한 조회 성능 향상**
    - **실행계획 분석 후 쿼리 최적화**
 
## 프로젝트 설명

#### 아키텍처 설계
![캡처1](https://github.com/user-attachments/assets/dc1d5b29-aabe-4f72-998b-1b0b3ce3655f)


## 구현 기능
- **회원 기능**
    - 회원 추가/조회/수정/삭제를 할 수 있습니다.
- **인증 기능**
    - 로그인 시 Access, Refresh 토큰을 발급받을 수 있습니다.
    - Refresh 토큰을 활용하여 새로운 Access, Refresh 토큰을 발급받을 수 있습니다.
- **게시글 기능**
    - 게시글 추가/조회/수정/삭제를 할 수 있습니다.
    - 인기 게시글 리스트를 조회할 수 있습니다.
    - 게시글 리스트를 페이징할 수 있습니다.
- **댓글 기능**
    - 게시글에 대한 댓글 추가/조회/수정/삭제를 할 수 있습니다.
- **좋아요 기능**
    - 게시글에 대한 좋아요 추가/삭제를 할 수 있습니다.
 

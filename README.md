# [ShareFit] 패션 소셜 네트워크 서비스
- **기간 : 2024년 07월 ~ 2024년 08월**
- **담당 : 백엔드 API 개발**
- **참여 인원 : 2명(프론트엔드 1, 백엔드 1)**
- **개발 환경**
    - **언어 : Java 17**
    - **프레임워크 : Spring Boot 3.3.1, Spring Security, Spring Data JPA, QueryDSL**
    - **데이터베이스 : MySQL, Redis**
    - **도구 : Docker, nGrinder, Giuhub Actions**
    - **AWS : EC2, S3, CodeDeploy, RDS, Elastic Load Balancing, Route 53**

## :mag_right: 프로젝트 설명

### 아키텍처 설계
![캡처1](https://github.com/user-attachments/assets/dc1d5b29-aabe-4f72-998b-1b0b3ce3655f)

### 서비스 기능
- **회원 기능**
    - 회원 추가/조회/수정/삭제를 할 수 있습니다.
- **게시글 기능**
    - 게시글 추가/조회/수정/삭제를 할 수 있습니다.
    - 게시글 리스트를 페이징할 수 있습니다.
    - 상단에 인기 게시글을 확인할 수 있습니다.
- **댓글 기능**
    - 게시글에 대한 댓글 추가/조회/수정/삭제를 할 수 있습니다.
- **좋아요 기능**
    - 게시글에 대한 좋아요 추가/삭제를 할 수 있습니다.
 
## :fire: 기술적 개선 및 고려

### QueryDSL 적용
- JPQL에서는 Limit 기능을 지원하지 않으므로 최적화 어려움
    - `QueryDSL`을 도입하여 복잡한 쿼리 구조를 효율적으로 작성하고 최적화

### 인증/인가 적용
- Spring Security, JWT 인증/인가 적용
    - 추가 기능1. `Refresh Token`을 활용한 `Access Token` 재발급 구현
    - 추가 기능2. `Refresh Token Rotation` 구현
      
### 캐시를 활용한 조회 성능 향상
- 사용자가 자주 조회하는 게시글을 빠르게 제공하기 위해 캐시를 적용하여 조회 성능을 향상
    - `Redis`를 활용하여 Look Aside + Write Around 조합
    - `@Cacheable`, `@CachePut`, `@CacheEvict`를 이용해 캐시 관리

**[nGrinder]**

캐시 적용 전
![노캐시](https://github.com/user-attachments/assets/7731ff28-524d-4674-b1cb-6b58fbd63b45)

캐시 적용 후
![캐시](https://github.com/user-attachments/assets/3f96330e-8db2-4ce8-a1f1-ab40f13d42a3)

### 쿼리 최적화
- 인기 게시글 리스트를 가져오는 쿼리 성능 개선 필요
    - MySQL `EXPLAIN`을 활용하여 실행계획 분석 후 최적화
    - [쿼리 최적화 과정 기록](https://velog.io/@gcael/MySQL-%EC%BF%BC%EB%A6%AC-%EC%B5%9C%EC%A0%81%ED%99%94-qs9vrhhi)

### CI/CD
- `Github Actions`, `AWS S3`, `AWS Codedeploy`를 활용하여 자동 배포 파이프라인 구현

### HTTPS 적용
- Refresh Token을 쿠키로 발급하는 과정에서 SameSite, Secure 옵션을 위해 HTTPS 적용 필요
    - `AWS Elastic Load Balancing`, `AWS Certificate Manager`를 활용하여 HTTPS 적용
    - HTTP 요청 시 HTTPS 요청으로 리다이렉션 설정

### 도메인 네임 적용
- IP를 입력하여 접속하는 방식은 사용자 편의성이 부족하고 변경 및 관리의 어려움
    - 도메인 등록 후 `AWS Route 53`을 활용하여 DNS 설정
    - 사용자 편의성 향상, 유연성 및 관리 효율적

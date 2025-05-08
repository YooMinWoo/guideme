# 🧭 GuideMe - 여행 가이드를 위한 예약 플랫폼

![Java](https://img.shields.io/badge/Java-17-blue?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-success?logo=spring-boot)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue)
![JWT](https://img.shields.io/badge/Auth-JWT-orange)
![Swagger](https://img.shields.io/badge/API-OpenAPI%2FSwagger-lightgrey)

**GuideMe**는 여행 가이드와 여행자를 위한 플랫폼입니다.  
가이드는 게시글을 등록하고 특정 날짜에 가격을 설정하거나 예약을 받을 수 있으며, 여행자는 게시글을 검색하고 예약하거나 좋아요를 눌러 관심을 표현할 수 있습니다.

---

## ✨ 주요 기능

### 🧑‍✈️ 사용자 및 인증
- 회원가입 및 로그인 (JWT 기반)
- Spring Security로 역할 기반 접근 제어

### 📌 게시글 관리
- 게시글 등록 / 수정 / 삭제
- 이미지 업로드 (Multipart 지원)
- 특정 날짜별 가격 등록 및 수정

### 📍 지역 기능
- 가이드의 활동 지역 추가 및 수정
- 지역 기반 조회

### 📆 예약 기능
- 사용자의 게시글 예약
- 예약 가능 여부 확인
- 예약 내역 조회

### ❤️ 좋아요 기능
- 사용자 게시글 좋아요 및 취소
- 좋아요한 게시글 목록 조회

### 📄 기타
- Swagger UI를 통한 API 문서 자동화
- 예외 처리 및 표준 응답 포맷 적용 (ApiResponse)

---

## 🧰 사용 기술

| 기술 스택       | 상세 내용 |
|----------------|-----------|
| Language       | Java 17 |
| Framework      | Spring Boot 3.4.3 |
| Build Tool     | Gradle |
| ORM            | Spring Data JPA, JPQL |
| Database       | MySQL |
| Security       | Spring Security, JWT |
| 문서화         | Swagger (Springdoc OpenAPI) |

---

## 향후 개선 예정
- 요청 별 유효성 검증 강화
- 도메인별 예외 분리

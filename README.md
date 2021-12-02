# HomeBody-BackEnd-Public-
# HomeBody 프로젝트 소개_Back-End
  <div
    align="center">
    <a
      href="https://play.google.com/store/apps/details?id=com.fitbuddy.homebody">
        <img
          src="https://user-images.githubusercontent.com/36183001/140261891-ce43893c-5879-4884-b74f-6fbbd18e82af.png"
          alt="Logo"
          width="120"
          height="120">
        </img>
    </a>
  </div>
  <h1
    align="center">HomeBody</h1>
  <h5
    align="center"> 🤜 지속적으로 운동을 즐길 수 있는 스마트 홈트레이닝 서비스 🤛</h5>
    
## 목차
1. [목적](#1-목적)
2. [시스템 아키텍처](#2-시스템-아키텍처)
3. [적용 기술 및 인프라](#3-적용-기술-및-인프라)
4. [구현 기능](#4-구현-기능)
5. [API 문서](#5-api-문서)
6. [데이터 모델링 ERD](#6-데이터-모델링-erd)
7. [데모 영상](#7-데모-영상)
8. [백엔드 개발 문의](#8-백엔드-개발-문의)

<br>

### 1. 목적
신체 부위별 다양한 운동과 이를 조합한 여러 루틴들을 제공하는 HomeBody 애플리케이션의 API 구축 및 데이터 처리 <br>
<br>

### 2. 시스템 아키텍처
<img width="1000" alt="백엔드아키텍처" src="https://user-images.githubusercontent.com/48276633/140633227-7e420805-dd6d-44d3-a640-4e16e8264409.png">

<br>

### 3. 적용 기술 및 인프라
<img src="https://img.shields.io/badge/Java 11-e74c3c?style=flat-square&logo=Java&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=Gradle&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Spring Security-6DB33F?style=flat-square&logo=Spring Security&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Swagger-85EA2D?style=flat-square&logo=Swagger&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=flat-square&logo=IntelliJ IDEA&logoColor=white"/><br>

<img src="https://img.shields.io/badge/CloudFlare-F38020?style=flat-square&logo=CloudFlare&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat-square&logo=Amazon AWS&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat-square&logo=Amazon S3&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=Redis&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Firebase-FFCA28?style=flat-square&logo=Firebase&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/OAuth-EB5424?style=flat-square&logo=Auth0&logoColor=white"/>&nbsp;<br>

<img src="https://img.shields.io/badge/GitLab-FCA121?style=flat-square&logo=GitLab&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/Git-F05032?style=flat-square&logo=Git&logoColor=white"/>&nbsp;
<img src="https://img.shields.io/badge/SourceTree-0052CC?style=flat-square&logo=SourceTree&logoColor=white"/>&nbsp;

<details open>
<summary>사용 방법</summary>
 <ol>
  - Spring Boot를 이용한 REST API 설계
 </ol>
 <ol>
  - Spring Boot에서 JPA를 이용한 AWS RDS(MySQL) 연동
 </ol>
 <ol>
  - Spring Batch를 이용하여 랭킹 데이터 관리 배치 작업
 </ol>
 <ol>
  - Firebase Authentication, JWT를 활용한 Spring Security를 적용하여 로그인한 사용자 유효성 검사
 </ol>
 <ol>
  - AWS EC2에 Redis 설치 및 랭킹 데이터 관리
 </ol>
 <ol>
  - AWS RDS에 DB 세팅
 </ol>
 <ol>
  - AWS S3에 기본 운동 데이터 저장
 </ol>
 <ol>
  - GitLab, AWS Elastic Beanstalk을 이용한 CI/CD 구축
 </ol>
 <ol>
  - CloudFlare를 이용하여 DNS 설정
 </ol>
</details>
<br>


### 4. 구현 기능 
<details open>
<summary>랭킹 시스템</summary>
 <ol>
  1. 부위별 및 전체 점수 상위 50명 정보를 구글 계정 프로필 이미지와 사용자 이름으로 반환
 </ol>
</details>

<details open>
<summary>운동 데이터 기록</summary>
 <ol>
  1. 사용자의 운동 데이터를 AWS RDS에 저장  <br>
  2. 운동 데이터 중 운동 수행 기록 부분을 추출하여 점수 계산 <br>
  3. Redis의 사용자 랭킹에 계산된 점수를 합산 <br>
 </ol>
</details>

<details open>
<summary>운동 리포트</summary>
 <ol>
  1. 사용자의 신체 정보 및 운동 리포트를 받고 싶은 기간을 기반으로 구성 <br>
  2. Mets 계산법을 통한 칼로리, 운동 데이터의 운동 시작 시간과 끝 시간, 그리고 운동 횟수를 기반으로 총 운동 시간 및 총 운동 횟수 제공 <br>
  3. 운동의 부위별 세부 정보 제공 <br>
 </ol>
</details>

<details open>
<summary>CI/CD</summary>
 <ol>
  1. GitFlow를 이용하여 개발 프로세스 구축 (Master, Develop, Feature) <br>
  2. Develop에 merge되면 staging 서버 자동 업데이트 <br>
  3. Master에 merge되면 production 서버 자동 업데이트  <br> </ol>
</details>

<br>
 
### 5. API 문서
- [백엔드 API 확인하기](https://production.homebody.tech/swagger-ui.html)

<br>

### 6. 데이터 모델링 ERD
![erd](https://user-images.githubusercontent.com/48276633/140633644-3f295b22-6281-44cc-bada-9e1720679fa1.png)

 <br>
 
### 7. 데모 영상
[![Video Label](https://user-images.githubusercontent.com/48276633/140654054-f3ad1eb8-5fe8-4603-9324-f42e9d25f86f.png)](https://youtu.be/-ryPfkqeQQM) 

<a href="https://play.google.com/store/apps/details?id=com.fitbuddy.homebody"><img width="150" alt="백엔드아키텍처" src="https://user-images.githubusercontent.com/48276633/139568570-f6bfd786-7fc1-4d19-9adf-1c64845183b4.png"></a>

<br> 
 
### 8. 백엔드 개발 문의
- 이주원(leeez0128@gmail.com)
- 이태민(koalakid154@gmail.com)



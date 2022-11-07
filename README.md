<img src='https://user-images.githubusercontent.com/102512612/200274763-d76bc556-4f21-4005-bf80-b717a801a972.png' width="40%" height="30%"/>

## 📌 프로젝트 개요
### 프로젝트 소개
- 여행지를 유형별(연령대, 성별, 관심사 등등)로 그룹화하여 그룹 특성에 맞는 패키지 여행상품을 추천하는 서비스 플랫폼

### 프로젝트 기간
- 2022 . 09 . 06 ~ 2022 . 10 . 14
- `미완성으로 인해 현재 진행중!`

### 프로젝트 배경
- [KDT] 핀테크 서비스 백엔드 개발 과정 `Final Project`인 기업연계 프로젝트
- `[KDT] 핀테크 서비스 프론트앤드 개발 과정 수강생들`과 `패스트캠퍼스 UI/UX 교육과정 수강생들`과의 협업 프로젝트
- 기업 측에서 기존에 아임웹 템플릿을 통해 개발없이 웹 페이지를 운영하였으나 한계로 인한 개발 문의

### 기업 요구 사항
- 기존 고투게더 사이트 `리뉴얼`
- `여행은 사람이다`를 모토로 큐레이션(설문조사)을 통해 같이 여행할 사람들의 성향을 파악하고 비슷한 사람들끼리의 여행이 되도록함

<br>

## 🙋🏻 팀원 소개

### UX/UI

| 조서우  | 송아름 |
|--------|-------|

### Frontend

| 조현아                                    | 변승훈                                             |
|----------------------------------------|-------------------------------------------------|
| [@hyeonahc](https://github.com/hyeonahc) | [@SeungHun6450](https://github.com/SeungHun6450) |

### Backend

| 김현준                                    | 이현승                                       | 김대곤                                           | 진우림                        |                                
|------------------------------------------|-------------------------------------------- |-----------------------------------------------|--------------------------------| 
| [@khjun723](https://github.com/khjun723) | [@iheese](https://github.com/iheese) | [@bbyuggyu](https://github.com/bbyuggyu) | [@jinwoorim](https://github.com/jinwoorim) | 

<br>

## :family: Backend 업무 분담

`김현준` : 상품 조회(상세, 추천, 카테고리별), 검색 기능

`이현승` : 사용자, 큐레이션, 관리자 기능, AWS S3

`진우림` : 예약 및 찜 기능

`김대곤` : 서버 배포(AWS EC2, RDS, ROUTE 53), CI/CD, Dokcer

<br>

## 🛠 기술 스택
- Front
    - Next.js
    - TypeScript
    - Router from Next.js
    - Redux Tool Kit 
    - getServerSideProps, Axios
    - CSS Module
    - Scss
    - MUI

- Back
    - Java 17
    - Spring Boot , JPA
    - Spring Security, JWT
    - Gradle
    - MySQL
    - Redis
    - QueryDSL
    - AWS EC2 , ROUTE 53, RDS, S3
    - Jenkins
    - Docker

<br>

## :white_check_mark: 프로젝트 내용

![관리자](https://user-images.githubusercontent.com/88040158/196174137-105870cc-1243-4dbc-be4c-9160fd96e082.png)

- 관리자 권한이 있는 계정으로 로그인 후 상품 추가 기능 페이지로 넘어오게 됩니다. 
- 각 칸에 모든 데이터를 작성해야 상품 등록이 완료됩니다.

<br>

![회원가입 로그인](https://user-images.githubusercontent.com/88040158/196174165-85164c74-507f-44fe-9ef6-523ab760401a.png)

- 회원 가입과 로그인 페이지입니다.

<br>

![큐레이션](https://user-images.githubusercontent.com/88040158/196174161-01cf757c-168b-4aec-839f-8ef5e37d630e.png)

![설문완료후](https://user-images.githubusercontent.com/88040158/196174152-81ca783d-5f22-4143-8f67-3e998b3e36cb.png)

- 추천 상품을 제공하기 위해 유저에게 큐레이션을 실시하여 데이터를 입력받습니다.
- 로그인 상태의 회원은 큐레이션 데이터가 바로 연결되어 저장되며, 비회원은 서버에 일시적으로 저장되어 추천 상품을 띄어줍니다. 

<br>

![상세페이지](https://user-images.githubusercontent.com/88040158/196174145-f0f1f57d-d8dd-49c0-894a-d389ab6d147a.png)

- 상세 페이지에서는 여행 상품에 대한 설명을 자세히 볼 수 있습니다.

<br>

![찜하기](https://user-images.githubusercontent.com/88040158/196174159-902cd099-078c-43aa-b160-a2e7e9267e55.png)

- 상세페이지에서 상품을 찜하고, 찜 목록을 확인할 수 있습니다. 

<br>

![예약](https://user-images.githubusercontent.com/88040158/196174155-ff770ea3-6ea2-4922-9437-07127ce0ae04.png)

- 여행 상품을 예약 할 수 있습니다.
- 예약된 상품 목록들을 볼 수 있습니다.

<br>

## :clipboard: API 명세서 링크

:zap: <a href="https://www.notion.so/65043cf00458463588fb056adc8a9e9f?v=646bfd16c06a4d53b0d5814ebdb3d0a0
">API 명세서 </a>

<br>

## :cloud: ERD Cloud 링크

:zap: <a href="https://www.erdcloud.com/d/bdPHHqeiiKZ6GC9rP
">ERD Cloud </a>

<br>

## :closed_book: 프로젝트 히스토리 링크
:mag_right: <a href="https://www.notion.so/BE-FE2-UXUI3-_5-68fbfff1ac584f1485272b1d2a372763
">프로젝트 노션</a>

:mag_right: <a href="https://xd.adobe.com/view/4fd96ade-7c67-429d-971c-8ea151ae344b-1a8a/
">Adobe XD - 와이어프레임</a>

:mag_right: <a href="https://xd.adobe.com/view/b526d4f5-cd7d-453b-9e76-3963a51256c9-e075/
">Adobe XD - 목업</a>

:mag_right: <a href="https://xd.adobe.com/view/80ef21aa-a95e-442e-8953-86d1239fb8e9-d5bc/
">Adobe XD - 관리자 페이지</a>

:mag_right: <a href="https://github.com/gotogether-s/gotogether-s
">FE github</a>

:mag_right: <a href="https://github.com/gotogether-s/gotogether-s-admin
">FE Admin github</a>

:mag_right: <a href="https://github.com/gotogether-s/gotogether-s-BE
">BE github</a>

:mag_right: <a href="https://gotogether-s-admin.vercel.app/signin">관리자 사이트</a>

:mag_right: <a href="https://gotogether-s.vercel.app">서비스 사이트</a>

(추후 서버 유지 비용으로 인해 연결이 불가할 수 있습니다.)

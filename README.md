# 웹/모바일(웹 기술) 스켈레톤 프로젝트

<!-- 필수 항목 -->

## 카테고리

| Application | Domain | Language | Framework |
| ---- | ---- | ---- | ---- |
| :white_check_mark: Desktop Web | :black_square_button: AI | :white_check_mark: JavaScript | :black_square_button: Vue.js |
| :black_square_button: Mobile Web | :black_square_button: Big Data | :white_check_mark: TypeScript | :white_check_mark: React |
| :white_check_mark: Responsive Web | :black_square_button: Blockchain | :black_square_button: C/C++ | :black_square_button: Angular |
| :black_square_button: Android App | :black_square_button: IoT | :black_square_button: C# | :white_check_mark: Node.js |
| :black_square_button: iOS App | :black_square_button: AR/VR/Metaverse | :black_square_button: Python | :black_square_button: Flask/Django |
| :black_square_button: Desktop App | :black_square_button: Game | :white_check_mark: Java | :white_check_mark: Spring/Springboot |
| | | :black_square_button: Kotlin | |

<!-- 필수 항목 -->

# Project : Bon Voyage

## [홈 페이지 바로가기](https://i10a207.p.ssafy.io)

Test용 ID/PW : dfddf0 / 123456

## Roles

### Team Leader : 이동훈
### FE : 김성욱, 박수민, 이윤정
### BE : 이동훈, 김아린, 유현종
### Infra : 유현종, 김아린
### WebRTC : 김아린(FE), 유현종(BE)

<br/>

## Used Stacks

### FE : React, Next.js, Three.js
### BE : Java, Spring boot, MySQL
### Infra : Jenkins, Docker, AWS, NGINX
### Project Manage : JIRA, GitLab
### Addtional Stack : WebRTC

<br/>

## View and Functions

### 메인 화면

![Main View](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/Maingif.gif)


<br/>

### 회원 가입 화면

![선택](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/SellListgif.gif)

가입자 선택화면

<br/>

![작가 가입](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/ArtistSignin.png)

작가 회원가입 입력화면

<br/>

![멤버 가입](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/MemberSignin.png)

일반 회원가입 입력화면

<br/>

### 경매 참여

![Auction Join](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/AuctionJoingif.gif)

- WebRTC 기능을 통한 경매 사이트 활성화.

- sessionId, currentUser의 Id, 경매 개설자 여부를 인자값으로 전달하여 URL을 형성하고 경매 세션을 생성함.

<br/>

### 경매 진행

![Auction](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/Auctiongif.gif)

- WebRTC를 사용하여 화상 경매를 진행함.

<br/>

### 작가 리스트 화면

![작가 리스트](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/ArtistListgif.gif)

<br/>

### 작가 미니홈피 화면

![Minihome](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/ArtistMinihomegif.gif)

<br/>

### MyPage, 개인정보 화면

![my Page](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/MyPageGif.gif)


- MyPage에서 회원정보, 포인트 충전, 결제 내역 관리
- 회원정보, 결제내역, 자주 묻는 질문, 입찰 신청 경매 관리

<br/>

### LogIn & LogOut

![Login](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/Logingif.gif)

로그인

![Logout](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/Logoutgif.gif)

로그아웃

- sessionStorage를 통하여 로그인, 로그아웃 기능 구현

<br/>

### 판매 리스트 화면

![Product](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/SellListgif.gif)

- Pagination 구현


![Product_Login](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/SellList_Login.png)

로그인 상태 판매 리스트
<br/>

![Product_LogOut](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/SellList_Logout.png)

로그아웃 상태 판매 리스트

- Login한 유저만 게시물 작성이 가능하도록 구현 

<br/>

### 판매 상품 상세 화면

![판매 상세](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/SellListDetail.png)

<br/>

### 경매 리스트 화면

![경매리스트_로그아웃](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/AuctionList_Logout.png)

로그아웃 상태

<br/>

![경매리스트_로그인](https://lab.ssafy.com/s10-webmobile1-sub2/S10P12A207/-/raw/master/readmeAssets/AuctionList_Login.png)

로그인 상태

<br/>

- 로그인 한 유저만 작성, 수정, 삭제가 가능하도록 구현
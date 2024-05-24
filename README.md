### 기술스택

| Application | Domain | Language | Framework |
| ---- | ---- | ---- | ---- |
| :white_check_mark: Desktop Web | :black_square_button: AI | :white_check_mark: JavaScript | :black_square_button: Vue.js |
| :black_square_button: Mobile Web | :black_square_button: Big Data | :white_check_mark: TypeScript | :white_check_mark: React |
| :white_check_mark: Responsive Web | :black_square_button: Blockchain | :black_square_button: C/C++ | :black_square_button: Angular |
| :black_square_button: Android App | :black_square_button: IoT | :black_square_button: C# | :white_check_mark: Node.js |
| :black_square_button: iOS App | :black_square_button: AR/VR/Metaverse | :black_square_button: Python | :black_square_button: Flask/Django |
| :black_square_button: Desktop App | :black_square_button: Game | :white_check_mark: Java | :white_check_mark: Spring/Springboot |
| | | :black_square_button: Kotlin | |


# Project : Bon Voyage

## 📌프로젝트 정보
> SSAFY 10기 공통 프로젝트 - 실시간 경매 커뮤니티 플랫폼
> 
> 개발 기간 : 2024.01.03 ~ 2024.02.16

### [홈 페이지 바로가기](https://i10a207.p.ssafy.io)

Test용 ID/PW : dfddf0 / 123456

### Roles

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

### 1. 메인 화면
- 3js를 이용한 메인화면 구성
- ![Maingif](https://github.com/Alleestar/Art_Auction/assets/147222319/86ea1744-58d9-407e-85a7-83287c4c6001)


### 2. 회원 가입 화면
- 작가와 개인회원으로 나누어서 가입할 수 있는 기능 (가입자 선택화면)
- 아이디 중복검사와 연락처 인증을 통해서 확인
- ![SignInSelect](https://github.com/Alleestar/Art_Auction/assets/147222319/1efc7fe7-459c-4845-b6ac-507a0e135e99)
- ![ArtistSignin](https://github.com/Alleestar/Art_Auction/assets/147222319/4e0e3ada-0eb2-436f-b2ac-bc0545755e8c)

### 3. MY PAGE 기능
- 해당 멤버의 MYPAGE 화면
- 경매와 판매 제품에 대하여 거래할 수 있는 기능
- 개인정보 수정 및 구매내역, FAQ, 나의 경매일정을 모두 확인 가능
- ![MyPageGif](https://github.com/Alleestar/Art_Auction/assets/147222319/eae430a4-ef25-4cf5-bb84-91c43eddc784)


### 3. LOGIN
- 로그인 기능과 로그아웃 기능 구현
- 로그인한 회원 가입 수정 탈퇴
- 로그인에 따른 기능 차이
- ![Logoutgif](https://github.com/Alleestar/Art_Auction/assets/147222319/2dcfe763-6f2e-435b-bac5-fc62842f2be8)
- ![Logingif](https://github.com/Alleestar/Art_Auction/assets/147222319/067a304b-5cc4-4861-9790-12028b756ad4)
- ![AuctionList_Logout](https://github.com/Alleestar/Art_Auction/assets/147222319/b0c3e53b-ff35-4fe5-9973-e19f8ed9c681)
- ![AuctionList_Login](https://github.com/Alleestar/Art_Auction/assets/147222319/437d8f06-add2-4c05-9f27-b28f4e5d2c11)

### 4. 판매물품 기능
- 일반 판매는 경매와 다르게 작가들이 경매없이 지정된 가격에 판매할 수 있도록 만듬
- 판매등록은 작가만 등록할 수 있으며 해당 제품에 대하여 리뷰를 작성할 수 있음.
- ![SellListgif](https://github.com/Alleestar/Art_Auction/assets/147222319/b25bd2de-9b9b-49fb-8c7f-4d2e77a28787)
- ![SellListDetail](https://github.com/Alleestar/Art_Auction/assets/147222319/9bcc5179-a7ac-42a8-85e9-c6311d336054)
- ![SellList_Logout](https://github.com/Alleestar/Art_Auction/assets/147222319/8725d83a-4e90-401e-9e2a-f36a5c28d694)
- ![SellList_Login](https://github.com/Alleestar/Art_Auction/assets/147222319/2de945ee-020d-40e4-a0e6-968904c6be04)

### 5. review 페이지
- 구매한 제품들에 한해서 review를 작성할 수 있으며, 삭제 수정 기능들 작성가능
- 작성된 댓글 밑에 댓글을 달 수 있음
- ![image](https://github.com/Alleestar/Art_Auction/assets/147222319/f51e259c-0c74-4a72-aa50-c9f1996a1ad1)
- ![image](https://github.com/Alleestar/Art_Auction/assets/147222319/448235a4-6ec6-4e4e-ad86-e3427ccc0847)

### 6. 경매기능 
- WEB RTC를 통해서 화상 소통을 가능할 수 있게 만들어줌 
- 경매는 작가가 경매를 진행할 수 있으며 왼쪽하단의 경매입찰을 통해서 정해진 양만큼의 입찰을 할 수 있음
- 오른쪽에는 채팅을 통해서 해당 작가와의 직접적인 소통을 할 수 있음.
- ![AuctionJoingif](https://github.com/Alleestar/Art_Auction/assets/147222319/9196fd18-3fe0-4afe-a47c-2b2aa79ab338)
- ![Auctiongif](https://github.com/Alleestar/Art_Auction/assets/147222319/92744f9c-c685-40e7-9580-a3609b6a923c)

### 7. 작가 리스트 화면
- 등록된 작가들을 볼 수 있음
- 인기순과 최신순으로 정렬되어 볼 수 있음
- ![ArtistListgif](https://github.com/Alleestar/Art_Auction/assets/147222319/ab28ed99-d2b1-4175-98e0-db20568bf49a)


### 8. 미니홈피 기능
- 작가마다 미니홈피를 운영할 수 있음
- 작가 이력을 기록할 수 있음
- 작가의 작품을 등록해서 볼 수 있음
- 개인의 포트폴리오를 올릴 수 있음
- 소통에 갖가에 대한 응원을 할 수 있음
- 해당 작가의 경매 일정을 확인할 수 있음
- 해당 작가의 작품에 대한 리뷰를 볼 수 있음
- ![ArtistMinihomegif](https://github.com/Alleestar/Art_Auction/assets/147222319/f9357421-4e81-40df-852f-143c2a5bd979)

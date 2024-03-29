### 개요

- 프로젝트 명칭: Jun Commerce
- 개발 인원: 1명
- 주요 기능:
    - 상품 - CRUD 기능
    - 회원 - Security 회원가입 및 로그인, 유효성 검사 및 중복검사
    - 상품 주문
- 개발 언어 : Java 17
- 개발 환경: SpringBoot 2.7.5, Gradle 7.5.1, JPA, Spring Security
- 데이터베이스 : MariaDB
- 형상관리 툴 : GitHub
- 간단 소개 : e커머스에 사용될 API 서버입니다.

---

### 요구사항 분석

1. 회원가입
    - 유효성 검사
        - 패스워드는 8자 이상 16자 이하의 숫자, 영문자, 특수문자를 포함
        - 로그인 아이디, 닉네임 길이 2 ~ 15자 제한
        - 로그인 아이디, 닉네임, 비밀번호, 비밀번호 확인이 비어있다면, “OOO를 입력하세요” 메세지 return
    - 중복 확인 및 비밀번호 확인
        - 데이터베이스에 이미 존재하는 아이디로 회원가입을 한 경우 “이미 존재하는 회원입니다.” 메세지 return
        - 비밀번호, 비밀번호 확인이 다를 경우 “비밀번호가 일치하지 않습니다.” 메세지 return
2. 로그인
    - 로그인 하지 않은 경우 접근 가능 api
        - 상품 리스트
        - 상품 조회
        - 회원가입
        - 로그인
    - 로그인 검사
        - 아이디와 비밀번호가 일치하지 않을 시 “아이디 또는 비밀번호가 일치하지 않습니다.”의 메세지 return (아직 구현x)
3. 상품
    - 상품 등록 / 수정 시 모든 항목 입력
        - 입력을 하지 않으면 “OOO을 입력하세요” 메세지 return
    - Admin 권한을 가진 경우에만 등록, 수정, 삭제 가능
4. 주문
    - 상품 주문
        - 상품 주문 시 상품 재고 감소
            - 상품 재고보다 많이 주문 요청을 할 경우 “상품의 재고가 부족합니다.(현재 재고 수량: OO)” 메세지 return
    - 상품 주문 삭제 (아직 구현x)
        - 주문 후 배달 상태가 READY인 경우에만 주문 삭제 가능
            - READY 상태가 아닐 경우 “이미 배송완료된 상품은 취소가 불가능합니다.” 메세지 return
    - 주문 조회
        - 자신의 주문 list 조회 (User) (아직 구현x)
        - 모든 주문 list 조회 (Admin) (아직 구현x)
        - 특정 유저의 주문 조회 (Admin) (아직 구현x)
    - 주문 삭제
        - 자신의 주문 삭제 (User) (아직 구현x)
        - 특정 유저의 주문 삭제 (Admin) (아직 구현x)

---

### DB 설계
        
![스크린샷 2023-01-30 오후 3 13 45](https://user-images.githubusercontent.com/110039142/215401912-2ac90553-fc25-4499-9f36-9da577a1bd75.png)

![스크린샷 2023-01-30 오후 3 34 32](https://user-images.githubusercontent.com/110039142/215405060-9dcb5a2f-2b9e-44ef-bbaa-2c00bf4e318d.png)

![스크린샷 2023-01-30 오후 3 34 28](https://user-images.githubusercontent.com/110039142/215405097-f33cda25-b4e7-4026-9045-dafcb60bd017.png)

![스크린샷 2023-01-30 오후 3 33 59](https://user-images.githubusercontent.com/110039142/215405271-72f178a6-dcfe-4190-bc74-4ce6f0fef366.png)

![스크린샷 2023-01-30 오후 3 34 06](https://user-images.githubusercontent.com/110039142/215405341-8f0cc7f8-1484-4515-8124-fbca482c8bab.png)

![스크린샷 2023-01-30 오후 3 34 14](https://user-images.githubusercontent.com/110039142/215405416-13db76cc-899e-4e90-a36d-361864b97236.png)

![스크린샷 2023-01-30 오후 3 34 21](https://user-images.githubusercontent.com/110039142/215405438-df1c4fe0-8ea8-446d-94e7-abd00ff0969c.png)

---

### API 설계

![스크린샷 2023-01-30 오후 4 11 34](https://user-images.githubusercontent.com/110039142/215411123-87118a62-b56c-46ce-9b84-485a752529a7.png)

![스크린샷 2023-01-30 오후 4 11 28](https://user-images.githubusercontent.com/110039142/215411161-4f864be5-906a-4247-a42a-af2b6dd760c8.png)

![스크린샷 2023-01-30 오후 4 11 41](https://user-images.githubusercontent.com/110039142/215411194-86afcee3-e3a5-4bd7-b76c-611ef1e43eb8.png)

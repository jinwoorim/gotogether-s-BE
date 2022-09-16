--  workbench에서 DB 이름 : goto 로 생성
--  application.yml에 DB 설정 3줄 본인에 맞게 변경
--  url: jdbc:mysql://mysql-container:3306/goto?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true
--  username: khjun723 < 본인 계정 이름으로 변경
--  password: 1324 < 본인 비밀 번호로 변경

DROP TABLE IF EXISTS Member;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Booker;
DROP TABLE IF EXISTS Interest;
DROP TABLE IF EXISTS Book;

create table Member(
                       id          bigint               PRIMARY KEY ,
                       email       varchar(100)         not null ,
                       password    varchar(100)         not null ,
                       name        varchar(100)         not null ,
                       birth       DATE                 not null ,
                       gender      enum('남', '여') ,
                       groupAge    enum('2030 그룹', '3040 그룹', '4050 그룹','5060 그룹','6070 그룹') ,
                       companion   enum('나홀로 참가', '친구나 동료', '연인이나 부부', '자녀를 동반하는 가족'),
                       religion    enum('주님', '부처님'),
                       groupType   enum('여자끼리 그룹', '남자끼리 그룹', '자녀동반 그룹'),
                       theme       enum('문화탐방', '골프여행', '리조트 휴양 및 힐링', '오지탐험', '트레킹여행', '봉사활동', '성지순례'),
                       season      enum('12월~2월', '3월~5월', '6월~8월', '9월~11월'),
                       trvlDstnIntr varchar(255)
);

create table Product(
                        id          bigint          PRIMARY KEY ,
                        thumbnail   varchar(255)    not null ,
                        productName varchar(255)    not null ,
                        amount      Long            not null ,
                        ctgNation   enum('동남아/태평양', '인도/중앙아시아', '아프리카/중동', '유럽/코카서스', '중남미/북미', '대만/중국/일본') ,
                        ctgGroup    enum('5070끼리', '2040 끼리', '남자끼리' , '여자끼리', '자녀동반'),
                        ctgTheme    enum('문화탐방', '골프여행', '휴양지', '트레킹', '성지순례', '볼론투어'),
                        summary     varchar(255)    not null ,
                        detail      varchar(255)    not null ,
                        info        varchar(255)             ,
                        way         varchar(255)             ,
                        infoImage   varchar(255)    not null
);

create table Booker(
                       id          bigint          PRIMARY KEY ,
                       name        varchar(100)    not null ,
                       age         int                      ,
                       phoneNumber varchar(50)     not null ,
                       role        boolean         not null
);

create table Interest(
                         id          bigint          PRIMARY KEY ,
                         productId   bigint,
                         FOREIGN KEY(productId) REFERENCES Product(id) ON DELETE CASCADE,
                         memberId    bigint,
                         FOREIGN KEY(memberId) REFERENCES Member(id) ON DELETE CASCADE
);

create table Book(
                     id          bigint          PRIMARY KEY ,
                     productId   bigint,
                     FOREIGN KEY(productId) REFERENCES Product(id) ON DELETE CASCADE,
                     memberId    bigint,
                     FOREIGN KEY(memberId) REFERENCES Member(id) ON DELETE SET NULL,
                     bookerId    bigint,
                     FOREIGN KEY (bookerId) REFERENCES Booker(id) ON DELETE SET NULL,
                     duration    varchar(100)    not null ,
                     totalPrice  LONG            not null ,
                     optionList  varchar(255)
);
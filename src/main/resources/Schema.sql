    --  workbench에서 DB 이름 : goto 로 생성
    --  application.yml에 DB 설정 3줄 본인에 맞게 변경
    --  url: jdbc:mysql://mysql-container:3306/goto?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true
    --  username: khjun723 < 본인 계정 이름으로 변경
    --  password: 1324 < 본인 비밀 번호로 변경

    DROP TABLE IF EXISTS Book;
    DROP TABLE IF EXISTS Booker;
    DROP TABLE IF EXISTS Curation;
    DROP TABLE IF EXISTS Member;
    DROP TABLE IF EXISTS Product;
    DROP TABLE IF EXISTS Wish;

    create table Book(
                           id             bigint               auto_increment        PRIMARY KEY ,
                           productId      bigint,
                           memberId       bigint,
                           bookerId       bigint,
                           departureDate  DATE                 not null ,
                           arrivalDate    DATE                 not null ,
                           departureArea  varchar(255)         not null ,
                           selection      varchar(255)         not null ,
                           totalPrice     long                 not null ,
                           status         varchar(255)
    );

    create table Booker(
                           id             bigint               auto_increment       PRIMARY KEY ,
                           name           varchar(255)         not null ,
                           age            int ,
                           phoneNumber    varchar(255)         not null ,
                           role           boolean              not null
    );

    create table Curation(
                            id             bigint              auto_increment       PRIMARY KEY ,
                            ages           varchar(255)        not null ,
                            together       varchar(255)        not null ,
                            companion      varchar(255)        not null ,
                            religion       varchar(255)        not null ,
                            theme          varchar(255)        not null
    );

    create table Member(
                            id             bigint              auto_increment       PRIMARY KEY ,
                            curationId     bigint                       ,
                            email          varchar(255)        not null ,
                            password       varchar(255)        not null ,
                            name           varchar(255)        not null ,
                            birth          DATE                not null
    );

    create table Product(
                            id             bigint              auto_increment       PRIMARY KEY ,
                            thumbnail      varchar(255)        not null ,
                            productName    varchar(255)        not null ,
                            amount         long                not null ,
                            country        varchar(255)        not null ,
                            ages           varchar(255)        not null ,
                            together       varchar(255)        not null ,
                            companion      varchar(255)        not null ,
                            religion       varchar(255)        not null ,
                            theme          varchar(255)        not null ,
                            ctgNation      varchar(255)        not null ,
                            summary        varchar(255)        not null ,
                            detail         varchar(255)        not null ,
                            info           varchar(255)        not null ,
                            way            varchar(255)        not null ,
                            infoImage      varchar(255)        not null
    );

    create table Wish(
                            id             bigint              auto_increment       PRIMARY KEY ,
                            productId      bigint,
                            memberId       bigint
    );

    insert into Member(email, password, name, authority) values("admin@admin", "$2a$12$oGkrW/rTkpaCSFDE1BWJHeZuKh3mymMAZpKsuEEKjUMZhyCqEM1Da", "ADMIN", "ROLE_ADMIN");
    -- 비밀 번호는 admin을 암호화해놨습니다.
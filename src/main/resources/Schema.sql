    DROP TABLE if EXISTS book CASCADE;
    DROP TABLE if EXISTS booker CASCADE;
    DROP TABLE if EXISTS member CASCADE;
    DROP TABLE if EXISTS product CASCADE;
    DROP TABLE if EXISTS wish CASCADE;

    CREATE TABLE book
    (
        book_id        BIGINT NOT NULL AUTO_INCREMENT,
        duration       VARCHAR(255),
        total_price    long,
        option_list    VARCHAR(255),
        product_id     BIGINT,
        member_id      BIGINT,
        booker_id      BIGINT,

        PRIMARY KEY (book_id)
    );

    CREATE TABLE booker
    (
        booker_id    BIGINT NOT NULL AUTO_INCREMENT,
        name         VARCHAR(255),
        age          Int,
        phone_number VARCHAR(255),
        role         BOOLEAN,

        PRIMARY KEY (booker_id)
    );

    CREATE TABLE member
    (
        member_id    BIGINT NOT NULL AUTO_INCREMENT,
        email        VARCHAR(255),
        password     VARCHAR(255),
        name         VARCHAR(255),
        birth        Date,
        gender       VARCHAR(255),
        age          VARCHAR(255),

        PRIMARY KEY (member_id)
    );

    CREATE TABLE product
    (
        product_id       BIGINT NOT NULL AUTO_INCREMENT,
        thumbnail        VARCHAR(255),
        product_name     VARCHAR(255),
        amount           Long,
        ctg_nation       VARCHAR(255),
        ctg_group        VARCHAR(255),
        ctg_theme        VARCHAR(255),
        summary          VARCHAR(255),
        detail           VARCHAR(255),
        info             VARCHAR(255),
        way              VARCHAR(255),
        info_image       VARCHAR(255),

        PRIMARY KEY (product_id)
    );

    CREATE TABLE wish
    (
        wish_id        BIGINT NOT NULL AUTO_INCREMENT,
        product_id     BIGINT,
        member_id      BIGINT,

        PRIMARY KEY (wish_id)
    );

    alter table book
        add constraint FKka98u01ogtkot3g9ibmt3qfr4
        foreign key (product_id)
        references product;

    alter table book
        add constraint FKpdmglejuicm0m2wwgvosuv0nq
        foreign key (member_id)
        references member;

    alter table book
        add constraint FKff7t7qd30eapf6och95jhmh1w
        foreign key (booker_id)
        references booker;

    alter table wish
        add constraint FKh3bvkvkslnehbxqma1x2eynqb
        foreign key (product_id)
        references product;

    alter table wish
        add constraint FK70nrc4a6uvljrtemsn80eq1gd
        foreign key (member_id)
        references member;
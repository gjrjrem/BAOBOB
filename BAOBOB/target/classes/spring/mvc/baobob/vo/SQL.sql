-- ����
CREATE USER baobob IDENTIFIED BY baobob DEFAULT TABLESPACE USERS;
GRANT CONNECT, RESOURCE TO baobob;
ALTER USER baobob ACCOUNT UNLOCK;

-- sql
--TABLE ��ü���� (���� �� ���� �Ǵ� ����!!!)
SELECT  'DROP TABLE ' || object_name || ' CASCADE CONSTRAINTS;'
FROM    user_objects WHERE   object_type = 'TABLE';
DROP TABLE PARK_HISTORY_tbl CASCADE CONSTRAINTS;
DROP TABLE BOARD_tbl CASCADE CONSTRAINTS;
DROP TABLE PARK_FEE_tbl CASCADE CONSTRAINTS;
DROP TABLE PARK_SPACE_tbl CASCADE CONSTRAINTS;
DROP TABLE REPLY_tbl CASCADE CONSTRAINTS;
DROP TABLE EMPLOYEE_tbl CASCADE CONSTRAINTS;
DROP TABLE THEATER_SEAT_tbl CASCADE CONSTRAINTS;
DROP TABLE MOVIE_HISTORY_tbl CASCADE CONSTRAINTS;
DROP TABLE MOVIE_REVIEW_tbl CASCADE CONSTRAINTS;
DROP TABLE RESTAURANT_HISTORY_tbl CASCADE CONSTRAINTS;
DROP TABLE RESTAURANT_MENU_tbl CASCADE CONSTRAINTS;
DROP TABLE RESTAURANT_REVIEW_tbl CASCADE CONSTRAINTS;
DROP TABLE PARK_tbl CASCADE CONSTRAINTS;
DROP TABLE THEATER_SCHEDULE_tbl CASCADE CONSTRAINTS;
DROP TABLE RESTAURANT_SCHEDULE_tbl CASCADE CONSTRAINTS;
DROP TABLE MOVIE_tbl CASCADE CONSTRAINTS;
DROP TABLE REVIEW_tbl CASCADE CONSTRAINTS;
DROP TABLE THEATER_tbl CASCADE CONSTRAINTS;
DROP TABLE HISTORY_tbl CASCADE CONSTRAINTS;
DROP TABLE RESTAURANT_tbl CASCADE CONSTRAINTS;
DROP TABLE MEMBER_tbl CASCADE CONSTRAINTS;
DROP TABLE RESTAURANT_FOOD_HISTORY_tbl CASCADE CONSTRAINTS;
DROP TABLE MEMBER_KEy_tbl CASCADE CONSTRAINTS;
DROP TABLE restaurant_table_tbl CASCADE CONSTRAINTS;
DROP TABLE faq_tbl CASCADE CONSTRAINTS;
DROP TABLE member_wishList_tbl CASCADE CONSTRAINTS;
DROP TABLE wordcloud_tbl CASCADE CONSTRAINTS;
--������ ����
DROP SEQUENCE BOARD_TBL_SEQ;
DROP SEQUENCE EMPLOYEE_TBL_SEQ;
DROP SEQUENCE HISTORY_TBL_SEQ;
DROP SEQUENCE MOVIE_TBL_SEQ;
DROP SEQUENCE PARK_HISTORY_TBL_SEQ;
DROP SEQUENCE PARK_TBL_SEQ;
DROP SEQUENCE REPLY_TBL_SEQ;
DROP SEQUENCE RESTAURANT_MENU_TBL_SEQ;
DROP SEQUENCE RESTAURANT_SCHEDULE_TBL_SEQ;
DROP SEQUENCE RESTAURANT_TBL_SEQ;
DROP SEQUENCE REVIEW_TBL_SEQ;
DROP SEQUENCE THEATER_SCHEDULE_TBL_SEQ;
DROP SEQUENCE THEATER_SEAT_TBL_SEQ;
DROP SEQUENCE THEATER_TBL_SEQ;
DROP SEQUENCE faq_tbl_SEQ;
-- ���̺� ������ ���踦 ����Ͽ� �� ���� �����ص� ������ �߻����� �ʰ� ���ĵǾ����ϴ�.

-- member_tbl Table Create SQL
CREATE TABLE member_tbl
(
    member_id          VARCHAR2(30)     NOT NULL, 
    member_pwd         VARCHAR2(30)     NOT NULL, 
    member_name        VARCHAR2(20)     NOT NULL, 
    member_tel         VARCHAR2(13)     NULL, 
    member_email       VARCHAR2(50)     NOT NULL,
    member_birth       VARCHAR2(30)     NOT NULL, 
    member_sex         VARCHAR2(10)     NOT NULL, 
    member_address     VARCHAR2(100)    NULL, 
    member_point       NUMBER           NULL, 
    member_step        NUMBER           NOT NULL, 
    member_cumPoint    NUMBER           NULL, 
    member_reg_date    TIMESTAMP        NOT NULL, 
    member_img         VARCHAR(100)     NULL, 
    CONSTRAINT MEMBER_TBL_PK PRIMARY KEY (member_id)
)
/


-- restaurant_tbl Table Create SQL
CREATE TABLE restaurant_tbl
(
    restaurant_index    NUMBER(2)        NOT NULL, 
    restaurant_tel      VARCHAR2(20)     NULL, 
    restaurant_name     VARCHAR2(100)    NULL, 
    CONSTRAINT RESTAURANT_TBL_PK PRIMARY KEY (restaurant_index)
)
/

CREATE SEQUENCE restaurant_tbl_SEQ
START WITH 1
INCREMENT BY 1;
/




-- movie_tbl Table Create SQL
CREATE TABLE movie_tbl
(
    movie_index       NUMBER            NOT NULL, 
    movie_title       VARCHAR2(200)     NOT NULL, 
    movie_content     VARCHAR2(4000)    NULL, 
    movie_janre       NUMBER(5)         NULL, 
    movie_age         NUMBER(2)         NULL, 
    movie_rel_date    VARCHAR2(20)      NULL, 
    movie_director    VARCHAR2(50)      NULL, 
    movie_star        VARCHAR2(100)     NULL, 
    movie_country     VARCHAR2(50)      NULL, 
    movie_runTime     NUMBER(5)         NULL, 
    movie_poster      VARCHAR2(100)     NULL, 
    movie_trailer     VARCHAR2(200)     NULL, 
    movie_state       VARCHAR2(20)      NULL, 
    movie_count       NUMBER            NULL, 
    CONSTRAINT MOVIE_TBL_PK PRIMARY KEY (movie_index)
)
/

CREATE SEQUENCE movie_tbl_SEQ
START WITH 1
INCREMENT BY 1;
/




-- restaurant_schedule_tbl Table Create SQL
CREATE TABLE restaurant_schedule_tbl
(
    restaurant_schedule_index    NUMBER(3)       NOT NULL, 
    schedule_startTime           TIMESTAMP       NULL, 
    schedule_endTime             TIMESTAMP       NULL, 
    restaurant_index             NUMBER(3)       NULL, 
    member_id                    VARCHAR2(30)    NULL, 
    CONSTRAINT RESTAURANT_SCHEDULE_TBL_PK PRIMARY KEY (restaurant_schedule_index)
)
/

CREATE SEQUENCE restaurant_schedule_tbl_SEQ
START WITH 1
INCREMENT BY 1;
/



ALTER TABLE restaurant_schedule_tbl
    ADD CONSTRAINT FK_restaurant_schedule_tbl_res FOREIGN KEY (restaurant_index)
        REFERENCES restaurant_tbl (restaurant_index)
/

ALTER TABLE restaurant_schedule_tbl
    ADD CONSTRAINT FK_restaurant_schedule_tbl_mem FOREIGN KEY (member_id)
        REFERENCES member_tbl (member_id)
/


-- history_tbl Table Create SQL
CREATE TABLE history_tbl
(
    history_index    NUMBER(3)       NOT NULL, 
    history_date     TIMESTAMP       NULL, 
    member_id        VARCHAR2(30)    NOT NULL, 
    CONSTRAINT HISTORY_TBL_PK PRIMARY KEY (history_index)
)
/

CREATE SEQUENCE history_tbl_SEQ
START WITH 1
INCREMENT BY 1;
/



ALTER TABLE history_tbl
    ADD CONSTRAINT FK_history_tbl_member_id_membe FOREIGN KEY (member_id)
        REFERENCES member_tbl (member_id)
/


-- theater_tbl Table Create SQL
CREATE TABLE theater_tbl
(
    theater_index    NUMBER       NOT NULL, 
    theater_row      NUMBER(3)    NOT NULL, 
    theater_col      NUMBER(3)    NOT NULL, 
    CONSTRAINT THEATER_TBL_PK PRIMARY KEY (theater_index)
)
/

CREATE SEQUENCE theater_tbl_SEQ
START WITH 1
INCREMENT BY 1;
/




-- review_tbl Table Create SQL
CREATE TABLE review_tbl
(
    review_index       NUMBER(3)        NOT NULL, 
    review_grade       VARCHAR2(20)     NULL, 
    review_content     VARCHAR2(500)    NULL, 
    member_id          VARCHAR2(20)     NULL, 
    review_state       NUMBER(2)        NULL, 
    review_reg_date    TIMESTAMP        NULL, 
    CONSTRAINT REVIEW_TBL_PK PRIMARY KEY (review_index)
)
/

CREATE SEQUENCE review_tbl_SEQ
START WITH 16
INCREMENT BY 1;
/



ALTER TABLE review_tbl
    ADD CONSTRAINT FK_review_tbl_member_id_member FOREIGN KEY (member_id)
        REFERENCES member_tbl (member_id)
/


-- restaurant_menu_tbl Table Create SQL
CREATE TABLE restaurant_menu_tbl
(
    restaurant_menu_index      NUMBER(3)        NOT NULL, 
    restaurant_index           NUMBER(3)        NULL, 
    restaurant_menu_name       VARCHAR2(50)     NULL, 
    restaurant_menu_img        VARCHAR2(200)    NULL, 
    restaurant_menu_content    VARCHAR2(500)    NULL, 
    restaurant_menu_price      NUMBER(6)        NULL   
)
/

ALTER TABLE restaurant_menu_tbl
    ADD CONSTRAINT FK_restaurant_menu_tbl_restaur FOREIGN KEY (restaurant_index)
        REFERENCES restaurant_tbl (restaurant_index)
/


-- theater_schedule_tbl Table Create SQL
CREATE TABLE theater_schedule_tbl
(
    theater_schedule_index    NUMBER       NOT NULL, 
    movie_index               NUMBER       NULL, 
    theater_index             NUMBER       NULL, 
    schedule_startDate        TIMESTAMP    NOT NULL, 
    schedule_startTime        TIMESTAMP    NOT NULL, 
    schedule_endTime          TIMESTAMP    NOT NULL, 
    schedule_MDNstate         NUMBER       NOT NULL, 
    schedule_empty_seat       NUMBER       NULL, 
    schedule_seat             NUMBER       NULL, 
    CONSTRAINT THEATER_SCHEDULE_TBL_PK PRIMARY KEY (theater_schedule_index)
)
/

CREATE SEQUENCE theater_schedule_tbl_SEQ
START WITH 1
INCREMENT BY 1;
/



ALTER TABLE theater_schedule_tbl
    ADD CONSTRAINT FK_theater_schedule_tbl_movie_ FOREIGN KEY (movie_index)
        REFERENCES movie_tbl (movie_index)
/

ALTER TABLE theater_schedule_tbl
    ADD CONSTRAINT FK_theater_schedule_tbl_theate FOREIGN KEY (theater_index)
        REFERENCES theater_tbl (theater_index)
/


-- board_tbl Table Create SQL
CREATE TABLE board_tbl
(
    board_index        NUMBER            NOT NULL, 
    member_id          VARCHAR2(30)      NOT NULL, 
    board_pwd          VARCHAR2(20)      NOT NULL, 
    board_type         NUMBER            NOT NULL, 
    board_subject      VARCHAR2(500)     NOT NULL, 
    board_content      VARCHAR2(4000)    NULL, 
    board_img          VARCHAR2(100)     NULL, 
    board_readCnt      NUMBER            NULL, 
    board_ref          NUMBER            NULL, 
    board_ref_step     NUMBER            NULL, 
    board_ref_level    NUMBER            NULL, 
    board_reg_date     TIMESTAMP         NULL, 
    board_ip           VARCHAR2(15)      NULL, 
    CONSTRAINT BOARD_TBL_PK PRIMARY KEY (board_index)
)
/

CREATE SEQUENCE board_tbl_SEQ
START WITH 1
INCREMENT BY 1;
/



ALTER TABLE board_tbl
    ADD CONSTRAINT FK_board_tbl_member_id_member_ FOREIGN KEY (member_id)
        REFERENCES member_tbl (member_id)
/


-- park_tbl Table Create SQL
CREATE TABLE park_tbl
(
    park_index        NUMBER       NOT NULL, 
    park_state        NUMBER       NULL, 
    park_theme        NUMBER       NULL, 
    park_last_date    TIMESTAMP    NULL, 
    park_last_out     TIMESTAMP    NULL, 
    CONSTRAINT PARK_TBL_PK PRIMARY KEY (park_index)
)
/

CREATE SEQUENCE park_tbl_SEQ
START WITH 1
INCREMENT BY 1;
/




-- restaurant_review_tbl Table Create SQL
CREATE TABLE restaurant_review_tbl
(
    review_index        NUMBER(3)    NOT NULL, 
    restaurant_index    NUMBER(3)    NOT NULL
)
/

ALTER TABLE restaurant_review_tbl
    ADD CONSTRAINT FK_restaurant_review_tbl_revie FOREIGN KEY (review_index)
        REFERENCES review_tbl (review_index)
/

ALTER TABLE restaurant_review_tbl
    ADD CONSTRAINT FK_restaurant_review_tbl_resta FOREIGN KEY (restaurant_index)
        REFERENCES restaurant_tbl (restaurant_index)
/


-- restaurant_history_tbl Table Create SQL
CREATE TABLE restaurant_history_tbl
(
    history_index                NUMBER(3)    NOT NULL, 
    restaurant_schedule_index    NUMBER(3)    NULL, 
    restaurant_table_index       NUMBER(3)    NOT NULL, 
    discount                     NUMBER       NULL, 
    member_use_point             NUMBER       NULL, 
    payvalue                     NUMBER       NULL, 
    restaurant_history_state     NUMBER       NULL   
)
/

ALTER TABLE restaurant_history_tbl
    ADD CONSTRAINT FK_restaurant_history_tbl_rest FOREIGN KEY (restaurant_schedule_index)
        REFERENCES restaurant_schedule_tbl (restaurant_schedule_index)
/

ALTER TABLE restaurant_history_tbl
    ADD CONSTRAINT FK_restaurant_history_tbl_hist FOREIGN KEY (history_index)
        REFERENCES history_tbl (history_index)
/


-- movie_review_tbl Table Create SQL
CREATE TABLE movie_review_tbl
(
    review_index    NUMBER    NOT NULL, 
    movie_index     NUMBER    NOT NULL
)
/

ALTER TABLE movie_review_tbl
    ADD CONSTRAINT FK_movie_review_tbl_review_ind FOREIGN KEY (review_index)
        REFERENCES review_tbl (review_index)
/

ALTER TABLE movie_review_tbl
    ADD CONSTRAINT FK_movie_review_tbl_movie_inde FOREIGN KEY (movie_index)
        REFERENCES movie_tbl (movie_index)
/


-- movie_history_tbl Table Create SQL
CREATE TABLE movie_history_tbl
(
    history_index             NUMBER    NOT NULL, 
    theater_schedule_index    NUMBER    NULL, 
    movie_history_price       NUMBER    NULL, 
    use_point                 NUMBER    NULL   
)
/

ALTER TABLE movie_history_tbl
    ADD CONSTRAINT FK_movie_history_tbl_history_i FOREIGN KEY (history_index)
        REFERENCES history_tbl (history_index)
/

ALTER TABLE movie_history_tbl
    ADD CONSTRAINT FK_movie_history_tbl_theater_s FOREIGN KEY (theater_schedule_index)
        REFERENCES theater_schedule_tbl (theater_schedule_index)
/


-- theater_seat_tbl Table Create SQL
CREATE TABLE theater_seat_tbl
(
    seat_index                NUMBER(5)       NOT NULL, 
    theater_index             NUMBER          NULL, 
    seat_row                  NUMBER(3)       NOT NULL, 
    seat_col                  NUMBER(3)       NOT NULL, 
    seat_state                NUMBER(2)       NOT NULL, 
    seat_price                NUMBER(5)       NULL, 
    theater_schedule_index    NUMBER          NULL, 
    member_id                 VARCHAR2(30)    NULL,
    history_index             NUMBER          NULL, 
    CONSTRAINT THEATER_SEAT_TBL_PK PRIMARY KEY (seat_index)
)
/

CREATE SEQUENCE theater_seat_tbl_SEQ
START WITH 1
INCREMENT BY 1;
/

ALTER TABLE theater_seat_tbl
    ADD CONSTRAINT FK_theater_seat_tbl_theater_in FOREIGN KEY (theater_index)
        REFERENCES theater_tbl (theater_index)
/
ALTER TABLE theater_seat_tbl
    ADD CONSTRAINT FK_theater_seat_tbl_history_in FOREIGN KEY (history_index)
         REFERENCES history_tbl (history_index)
/


-- employee_tbl Table Create SQL
CREATE TABLE employee_tbl
(
    employee_index     NUMBER          NOT NULL, 
    member_id          VARCHAR2(30)    NOT NULL, 
    employee_jumin2    VARCHAR2(20)    NOT NULL, 
    employee_date      TIMESTAMP       NOT NULL, 
    CONSTRAINT EMPLOYEE_TBL_PK PRIMARY KEY (employee_index)
)
/

CREATE SEQUENCE employee_tbl_SEQ
START WITH 1
INCREMENT BY 1;
/



ALTER TABLE employee_tbl
    ADD CONSTRAINT FK_employee_tbl_member_id_memb FOREIGN KEY (member_id)
        REFERENCES member_tbl (member_id)
/


-- reply_tbl Table Create SQL
CREATE TABLE reply_tbl
(
    comment_index      NUMBER           NOT NULL, 
    reg_date           TIMESTAMP(6)     NOT NULL, 
    comment_content    VARCHAR2(500)    NULL, 
    member_id          VARCHAR2(30)     NOT NULL, 
    board_index        NUMBER           NOT NULL, 
    CONSTRAINT REPLY_TBL_PK PRIMARY KEY (comment_index)
)
/

CREATE SEQUENCE reply_tbl_SEQ
START WITH 1
INCREMENT BY 1;
/



ALTER TABLE reply_tbl
    ADD CONSTRAINT FK_reply_tbl_member_id_member_ FOREIGN KEY (member_id)
        REFERENCES member_tbl (member_id)
/

ALTER TABLE reply_tbl
    ADD CONSTRAINT FK_reply_tbl_board_index_board FOREIGN KEY (board_index)
        REFERENCES board_tbl (board_index)
/


-- park_space_tbl Table Create SQL
CREATE TABLE park_space_tbl
(
    p_space_col     NUMBER            NULL, 
    p_space_row     NUMBER            NULL, 
    p_space_info    VARCHAR2(4000)    NULL   
)
/


-- park_fee_tbl Table Create SQL
CREATE TABLE park_fee_tbl
(
    p_fee_exc_price     NUMBER    NULL, 
    p_fee_exc_time      NUMBER    NULL, 
    p_fee_base_price    NUMBER    NULL, 
    p_fee_base_time     NUMBER    NULL, 
    p_fee_movie_time    NUMBER    NULL, 
    p_fee_rest_time     NUMBER    NULL   
)
/


-- park_history_tbl Table Create SQL
CREATE TABLE park_history_tbl
(
    p_history_index    NUMBER          NOT NULL, 
    history_index      NUMBER          NULL, 
    p_history_in       TIMESTAMP       NULL, 
    p_history_out      TIMESTAMP       NULL, 
    p_history_price    NUMBER          NULL, 
    p_history_space    VARCHAR2(20)    NULL, 
    p_history_date     TIMESTAMP       NOT NULL, 
    CONSTRAINT PARK_HISTORY_TBL_PK PRIMARY KEY (p_history_index)
)
/

CREATE SEQUENCE park_history_tbl_SEQ
START WITH 1
INCREMENT BY 1;
/



ALTER TABLE park_history_tbl
    ADD CONSTRAINT FK_park_history_tbl_history_in FOREIGN KEY (history_index)
        REFERENCES history_tbl (history_index)
/


-- restaurant_food_history_tbl Table Create SQL
CREATE TABLE restaurant_food_history_tbl
(
    restaurant_schedule_index    NUMBER(3)    NOT NULL, 
    restaurant_menu_index        NUMBER(3)    NULL, 
    restaurant_index             NUMBER(3)    NULL, 
    restaurant_menu_count        NUMBER(3)    NULL, 
    restaurant_table_index       NUMBER(3)    NULL   
)
/

ALTER TABLE restaurant_food_history_tbl
    ADD CONSTRAINT FK_restaurant_food_history_ FOREIGN KEY (restaurant_schedule_index)
        REFERENCES restaurant_schedule_tbl (restaurant_schedule_index)
/



ALTER TABLE restaurant_food_history_tbl
    ADD CONSTRAINT FK_restaurant_food_history_tb FOREIGN KEY (restaurant_index)
        REFERENCES restaurant_tbl (restaurant_index)
/


-- member_wishList_tbl Table Create SQL
CREATE TABLE member_wishList_tbl
(
    member_id      VARCHAR2(30)    NOT NULL, 
    movie_index    NUMBER          NOT NULL
)
/

ALTER TABLE member_wishList_tbl
    ADD CONSTRAINT FK_member_wishList_tbl_member_ FOREIGN KEY (member_id)
        REFERENCES member_tbl (member_id)
/

ALTER TABLE member_wishList_tbl
    ADD CONSTRAINT FK_member_wishList_tbl_movie_i FOREIGN KEY (movie_index)
        REFERENCES movie_tbl (movie_index)
/


-- member_key_tbl Table Create SQL
CREATE TABLE member_key_tbl
(
    member_id     VARCHAR2(30)    NOT NULL, 
    member_key    VARCHAR2(20)    NULL   
)
/

ALTER TABLE member_key_tbl
    ADD CONSTRAINT FK_member_key_tbl_member_id_me FOREIGN KEY (member_id)
        REFERENCES member_tbl (member_id)
/


-- restaurant_table_tbl Table Create SQL
CREATE TABLE restaurant_table_tbl
(
    restaurant_index             NUMBER(3)    NOT NULL, 
    restaurant_table_index       NUMBER(3)    NOT NULL, 
    table_row                    NUMBER(3)    NULL, 
    table_col                    NUMBER(3)    NULL, 
    table_state                  NUMBER(2)    NULL, 
    restaurant_schedule_index    NUMBER(3)    NULL   
)
/

ALTER TABLE restaurant_table_tbl
    ADD CONSTRAINT FK_restaurant_table_tbl_resta FOREIGN KEY (restaurant_index)
        REFERENCES restaurant_tbl (restaurant_index)
/

ALTER TABLE restaurant_table_tbl
    ADD CONSTRAINT FK_restaurant_table_tbl_rest FOREIGN KEY (restaurant_schedule_index)
        REFERENCES restaurant_schedule_tbl (restaurant_schedule_index)
/


-- faq_tbl Table Create SQL
CREATE TABLE faq_tbl
(
    faq_index        INT               NOT NULL, 
    faq_title        VARCHAR2(255)     NULL, 
    faq_sub_title    VARCHAR2(255)     NULL, 
    faq_content      VARCHAR2(2000)    NULL, 
    CONSTRAINT FAQ_TBL_PK PRIMARY KEY (faq_index)
)
/

CREATE SEQUENCE faq_tbl_SEQ
START WITH 1
INCREMENT BY 1;
/



-- wordcloud_tbl Table Create SQL
CREATE TABLE wordcloud_tbl
(
    word              VARCHAR2(60)    NOT NULL, 
    count             NUMBER          NOT NULL, 
    type_of_speech    VARCHAR2(40)    NOT NULL, 
    reg_date          TIMESTAMP       NOT NULL, 
    update_date       TIMESTAMP       NOT NULL, 
    movie_index       NUMBER          NOT NULL
)
/

ALTER TABLE wordcloud_tbl
    ADD CONSTRAINT FK_wordcloud_tbl_movie_index_m FOREIGN KEY (movie_index)
        REFERENCES movie_tbl (movie_index)
/

































--insert movie_tbl
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (8,'���ٷ��','�λ��� �� �� �� ������ĵƮ��?

�ƴ�, ��¥ ����� ���������� ���ݺ��ʹ�!

 

��� ���� ����, ���� �ϳ� ������ �⺻ ������ �� ���� �ôϾ����

�� ���� Ȳȥ�� �����ϱ�� ����� �� �����

������ ���� ���ݲ� �̷�Դ� ������ ��Ŷ����Ʈ�� ������ �����Ѵ�.

������ �پ�Ѵ� �׵��� ������Ʈ�� �� ���װ� ��Ĭ �������� �Ǵµ���

 

2018�� ����, �ɺ��� �Ƹ��ٿ� ��¥ �λ��� �����ϴ�!',6,12,'18-01-24','�̼���','����ȯ ,  �ű� ,  ������ ,  ������','�ѱ�',97,'qlqkqqkfnffk.jpg','f6Dr2fX2lnc','1',0);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (9,'����','����� ���� ���� �����塯(���·�).
��� �� ���ڱ� ���� ���� �̻��� ��ȭ�� ã�ƿ´�.
���������� ������ �����̴� ���� �ɷ�, �ٷ� ������ ���� ��.
 
����, ���λ��塯(�����)�� ��ȫ�󹫡�(������)�� ����
�����塯�� ��, û�� ���� ����̡�(������)�� �̿����� ���⿡ ó�ϰ� �ǰ�...
�����塯�� ����̡�, �׸��� ��ȣ�� ��������(������)�� �׵鿡 �¼��� ���� ���� �������µ�...!
 
�������� �����, �Ϸ��ħ�� �ʴɷ�
���� ���� ������ �����Ѵ�!',3,15,'18-01-31','����ȣ','���·� ,  ������','�ѱ�',101,'duafur.jpg','ZdGdh5MxGmY','0',0);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (10,'�� �Ҽ�','���ú� ���� ���� ��ĭ���� ������ ����� Ƽ����(ä���� ������)��
��ĭ�ٿ��� �����ϴ� �ְ� ��� �ݼ� �����󴽡���
���¸� �븮�� �������� ���� ���������� �������� ������
������ ���� ����� ���� �Ҽ����μ� ���� �� ���� ���￡ �����µ���',8,12,'18-02-14','���̾� ��۷�','ä���� ������ ,  ����Ŭ B. ���� ,  ����Ÿ �� ,  �ٳ��� ������','�̱�',135,'qmffor_vostj.jpg','k0DF4LSG1z8','0',0);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (11,'�νõ�4: ��Ʈ Ű','�ڽ��� ������ ������ �ϵ��� �Ͼ�ٴ� �� ������ ��ȭ�� �ް� �� ���� ������(�� ����).
ã�ư� �� ���� �ٸ� �ƴ� �ڽ��� � ���� ��Ҵ�, ���߽����� �������̴�.
���� �� ���� �ٽ� ã�� ������ �������� ��ü�Ҹ��� ����� �ľ��ϱ� ���� ���� ������.
� ���� �ڽ��� �޾��� ����ϰ� ���� ���� ������ �����ϰ� �Ǵµ���
 
���νõ��� �ø�� ���۵� �� ��, ���� ������ ����� ��������!',10,15,'18-01-31','�ִ� �κ���','�� ���� ,  ���漭 ��ũ ,  ���� ��Ʃ��Ʈ ,  �Ϻ񿡸� ����','�̱�',103,'dlstleldjtm4.jpg','2nKntXg4KwM','0',0);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (12,'����ġ�� ��','�� ����, �츮���� ��¦ ģ���� ���� ��� ��ư��� ������.
 
10��° ��û����� ��� �ִ� ä����(�̻���)�� ��� �� ����ö���� ����ģ ���л�(�����)�� ���󰣴�. ä����� ���л��� �ڵ����� �� ���� ������(ȫ����)���� ��¥��¥ �ڽ��� ������ ����� �������� ��¦ģ����� �����Ѵ�. �������� ä���� ���𸣴� ������̶�� ����鼭�� ģ���μ� ���ο� ���踦 �״´�. ���� �������� ���� ��� �ִ� ������(������)�� ä������ ������ ħ���� ���������� ���� ä���񿡰� �ڽ��� ������ ����� ���ݾ� ������ ���´�. �׸��� �������� ������ ����� ��¦ģ�� ������(�����)�� ����� ���ø���.
 
���п�, ���� �����־�.

2018�� 1��, ����� ã�ƿ� ������ ���縦 ������.',3,15,'18-01-31','�̿Ϲ�','�̻��� ,  ȫ���� ,  ����� ,  �̼�ȣ ,  ������','�ѱ�',123,'sndpclejs_qkd.jpg','ddcfAvidaX8','0',0);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (13,'�������̽�','������ ź���ϴ� ������ ���� ����϶�!
���ź����� ���� ������ 10���� ���������̽���.

�״� 3���� ���� ȯ�ڿ� �Բ� ���� ��ȣ�縦 ��ġ, �װ����� Ż���Ѵ�.

�̳� �׵鿡�� ���� ���� ���Ȱ��� �߰ݰ� ������ �¼�

''�������̽�''�� ���ż���� ���� �ı��Ǿ� ����

������ ������ ������ ������θ� ������ ���ذ���.',9,19,'18-01-25','�˷���帣 �߽�Ƽ��','��Ƽ�� ���� ,  ���� ���Ϸ�','�̱�',87,'fpejvpdltm.jpg','RxeFONX07gU','1',0);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (14,'��罽����','�ƹ��� ���� ��
�׸��� �ݵ�� ��Ƴ���
 
���ϰ� ������ �ù��� ���ǿ졯(������).
�ֱ� ����ù����� �����Ǿ� ������ ź �׿��� ����б� ���� ģ�� ��������(�����)�κ��� ������ �´�.
�������� ��ȸ�� �ݰ��� ���,
�׵� �� �տ��� ���� �뼱�ĺ��� ��ź �׷��� ���� �ϻ���ϴ� ����� ��������.
��Ȳ�� �ǿ쿡�� ������ �� ��� ���� ��ȹ�� ���̸�,
�ǿ츦 �ϻ������ ����� �� �ڸ����� ���� ��Ű�� �� ������ ��ȹ�̶�� �̾߱⸦ ���Ѵ�.
 
�ܿ� ���忡�� ����ġ���� ���İ��� �ϻ��ڷ� ����Ǿ� ���� ����� �ǿ�.
CCTV, ����, ����ڱ��� �Ϻ��� ���۵� ��Ȳ,
������ ���� ���� �� �ι�, ���� ����� ���ξ���(���Ǽ�)�� ã�� �ǿ��
�׸� ���� ����� ��ü�� ���ݾ� �˰� �ȴ�.
��� ���� ����, �׸��� ������ ���� ���� �ʻ������� �¼��� �ǿ�.
������ �ڽ��� ����ĥ���� ���� ģ���� �����ԡ�(����), ����ö��(�輺��), ��������(��ȿ��)����
���迡 ������ �Ǵµ���
 
2018�� 2��, ������ �׸� �Ѵ´�!',3,15,'18-02-14','�뵿��','������ ,  ���Ǽ� ,  ��ȿ�� ,  �輺��,  ����','�ѱ�',107,'rhfemstmffjaqj.jpg','FBotiXtiM60','0',0);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (15,'���� ��','��ó�� ȯ������
����, �ڴ� ���Ϸ���
 
����Ʈ������ ���ϴ� ���ϴ� �غ��� ������� ��Ű�� ����� ������.
����ġ ���� ĳ�Ѷ��̳��� �����ϸ� ���ڴ� ���Ϸ��塯 ���� �쿬ó�� ���� �� ������ �������� �θǽ��� ���۵ȴ�!',3,15,'18-01-25','��� �˷�','����Ʈ ������ ,  ����ƾ ��������ũ ,  �ֳ� ���� ,  ���ӽ� �����','�̱�',101,'dnjsej_gnlf.jpg','VI-6wUoKDbY','1',0);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (16,'����','�������� ��� �ο����� ������ �ִ�!��

<����> �������� ���� �ø���! 
������ �� ���Ҽ�? ���� ������ ���帮��!

�ؽ��� ����� ���ӵǴ� �����ô�, ��ȭ����(������)�� ȥ�縸�� ������ �ؼ��� ���̶� �ϴ� ��(����)�� ������� �θ� ������ �ǽ��ϰ�, ���� �ְ��� ������ ������(�̽±�)�� �θ� �ĺ���� ��ȭ������ ����Ǯ�̸� �ð� �ȴ�.
�糪�� ���ڷ� �ҹ��� ���� ȥ���� �������� �̷��� ��ȭ���ִ� �󱼵� �𸣴� ����� �������� ������ �� ���ٰ� �Ǵ��ϰ� �θ� �ĺ����� ���ִ��ڸ� ���� �� ������ ���� �ĺ����� ���ʷ� ��Ž�ϱ� �����Ѵ�.
��ȭ���ְ� ���ִ��ڸ� ��ģ �ó��� ������ �������� ���ִ��ڸ� ��ã�� ���� �׳��� ������ �Բ� �ϰ� �Ǵµ�..

�߽��� �ɷ³� ���ð�(������), �汹������ �����̳� ����(������), ȿ�� ������ �ųʳ� ��ġȣ(�ֿ��)�� �뼼 ���ϳ�����!

������ ���ڸ� �ٲ� �ְ��� ��(��)�� ã�ƶ�!',3,15,'18-02-01','ȫâǥ','������, �̽±�','�ѱ�',100,'rndgkq.jpg','Z6lhm1yIC5Y','0',0);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (17,'��Ž�� �ڳ�: ������ ��','��� Ǫ�� �ٴ�� ����, �װ��� ���� �ִ� ���� ����?!
 
�ް��� �¾� 300�� �� �����ߴ� ������ ������ ������ ������ �������� ���ص��� ã�� �ڳ� ����.
���� �� ������ ã������ Ʈ���� ����(���� ��ɲ�) �� �� ���� �ǹ��� ������ ���ϸ鼭 ������ ������ ���� �ڳ� ������ ���簡 ���۵ȴ�.
����, Ʈ���� ���͵� �� ������ ���踦 �ް� �ִ� ������ �ι����� �ִٴ� ����� ������ ���尨�� �����Ǵ� ��� �̶��� ���� ������� ����� �߻��ϴµ���! "
 
����, �ڳ��� �������� ���� ��ȣ�� Ǯ�� ��������� �̶��� ���� ã�Ƴ� �� ���� ���ΰ�?!',7,12,'18-02-14','�߸����� �߽���ġ��','�輱�� ,  ������ ,  ������ ,  �̿�� ,  ������','�Ϻ�',0,'audxkawjd_zhsks.jpg','rUXaMbEFXUk','0',0);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (1,'������ ����:���� ť��','�̷��� ���� Ȯ���϶�! 

�̽��͸��� ���� ����Ű�塯���� ���� ����ȣ��(�̱�ȫ)�� ���ϱ� ���� 
���丶����(���� ������̾�)�� ���ʵ��� ����Ű�塯�� ���ΰ� �ִ� ������ ���÷� ���Ѵ�. 
�η��� ����� �ɸ� ����Ű�塯�� ������ ��ȹ�� �˰� �� 
���丶������ ���ʵ��� ������ ������ �غ�������, 
���丶������ ģ���� �η��� ��� �տ��� �������� ������ �Ǵµ���',8,12,'18-01-17','���� ��','���� ������̾�, �丶�� ��ε� ������, ī�� ���ڵ��󸮿�, �̱�ȫ','�̱�',143,'maze_runner_ybh.jpg','_DkF1V30OCo','1',0);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (2,'�װ͸��� �� ����','�Ѷ��� WBC ���ͱ� ���� è�Ǿ��̾�����
������ ���� �� ������ �ѹ��� ���� ���� ''����''(�̺���).
�쿬�� 17�� ���� ����� ���� ''�μ�''(������)�� ��ȸ�ϰ�,
������ �ذ��ϱ� ���� ���� ������
������ ������ ���ߴ� ����� ���� ''����''(������)�� �����Ѵ�.

����ó�� �ôµ��� �����̶��?!

��� ���̱�, ���ӵ� �ְ�� ��������
�������� �ǾƳ뿡 õ���� ����� ���� ����Ʈ���ı� ����.
���ϴ� �Ը� ���� "��~" Ÿ���� �ɻ�ġ ���� ������ ���� �Ѽ����� ���´�.
������ ĳ���ٷ� ���� ���� ��� �����ϱ� �������� �� ����� ����� ���ϴ�
���� ����ġ ���� ������ ���Ż�Ȱ�� �ϱ� �����ϴµ���

��ƿ� ����, ���ϴ� �ϵ�, �����ϴ� �͵� �ٸ�
�� ������ ������!',3,12,'18-01-17','�ּ���','�̺���, ������, ������','�ѱ�',120,'Its_only_my_world_ybh.jpg','G-brqMCgOy4','1',0);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (3,'����','������ ����ϰ� ���� ȲȦ�� ������ ���۵ȴ�!



�������� �޲ٴ� �ҳ� �̱����� �������� ���� �����׽����� ��Ÿ�� ���� ��� ������ �ڵ��� ���󡯿� ���� �ȴ�.

�׸��� �װ����� ���� �ǹ��� �糪�� ���Ϳ� �Բ� ������� ���ߴ� ������ �����ϰ� �Ǵµ���

���� ������ �ڵ��� ���󡯿� ������ �����? �׸��� �̱����� ������ ���Ƿ� ���ƿ� �� ������?',7,0,'18-01-11','�� ��ũ��ġ','���� �����þ� ������, �ؼ��� ���߷���, ���ڹ� �귧','�̱�',127,'coco_ybh.jpg','LmS5KMJTWlA','1',0);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (4,'Ŀ����','���� �ð� 30��, ������ ������ ���� ���� ���� ����Ŭ(���� �Ͻ�)�� ��� �־��� ���� �׷����鿡�� �¼��� �ʴ��� �׼� ��Ϲ�����',8,15,'18-01-24','�ڿ� �ݷ� ����','���� �Ͻ�, ���� �Ĺ̰�','�̱�, ����',105,'the_commuter_ybh.jpg','bjGNQ_cg-2s','1',0);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (5,'1987','��å���� Ź! ġ�� ��! �ϰ� �׾����ϴ١�



1987�� 1��, ���� ���縦 �޴� ������ �� ���л��� ����Ѵ�.

�����θ��� ���� ��ó��(������)�� �ֵ� �Ͽ� ������ �ý� ȭ���� ��û������, ��� ���� �����̾��� �ְ˻�(������)�� �̸� �ź��ϰ� �ΰ��� �о���δ�.

�ܼ� ��ũ���� ��ó�� ���� ��ǥ�� �̾�� ����. �׷��� ���忡 ���� ������� �ΰ� �Ұ��� ���� ���� ����� ����Ű��,

����� �����ϴ� ������(������)�� ������ ���� ���Ļ硯�� �����Ѵ�. �̿� ��ó���� ������(�����)�� ���� �Ѹ� ���ӽ�Ű�� ����� ����Ϸ� �Ѵ�.

����, �����ҿ� ������ �������� ���� ����� ������ �˰� �� ������ �Ѻ���(������)�� �� ����� ���� ���� ����λ翡�� �����ϱ� ���� ��ī�� ����(���¸�)���� ������ ��Ź�� �ϰ� �Ǵµ���



�� ����� �װ�, ��� ���� ��ȭ�ϱ� �����ߴ�.

��ΰ� �߰ſ��� 1987���� �̾߱�.',3,15,'17-12-27','����ȯ','������, ������, ������, ���¸�, �����, ������','�ѱ�',129,'1987_ybh.jpg','WKQSRFlfr50','1',0);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (6,'1�ޱ��','���� ������ �߶�, ���ظ� 3��° ���� ������ ����?��

�׵��� ���߷� �ߴ�, ��ΰ� �˾ƾ� �ϴ� ���ѹα� ���� ���� ���� ��ȭ!

����� �������� �װ���ǰ���Ű� �������� ������ �ڴ��� �߷�(����)���� ��� ��, ���� ������ ���Ϸ� ������ ������ ã�ƿ� ������ ��ǰ ���� ��ü ������ ���� ��Ȥ�� �����Ѵ�. �̿� ������ ��ǰ���� ������ Ȯ���ϴ� �� ���� �̱��� ���Ÿ ��ǰ���� ���޵ǰ� ������ �߰��Ѵ�. ���� ������ ������ ������ �߶� ��� ���ϰ�, �̸� ������ ���Ƿ� ����� ����� �����ϴ� ������ ���Ѻ� ������ ū ����� �޴´�. �׸��� ������ ������ ���� ������ ������ ���Կ� ���� ���Ÿ�� ����� �� ��Ÿ��� ����� ���� ����ǰ� �ִ� ������ ����� �˰� �ȴ�. �����Ը�ŭ�� ���󿡼� ���� �ٺ� ������ ���󿡼� ���� �밨�� �������� ���� ���� ������ [PD25��]�� ���� ������(�����)�� ����� �����̶�� �̸����� ���� �ڿ� ���� ���ϵ��� ������ �����ϱ�� ����ϴ� ����

�׵��� ������ ����, ����� �׺����� ���� ���̴�!',3,12,'18-01-24','ȫ�⼱','����, �����, �ֹ���','�ѱ�',101,'class_1_security_ybh.jpg','2c0G53QvhiI','1',12);
Insert into BAOBOB.MOVIE_TBL (MOVIE_INDEX,MOVIE_TITLE,MOVIE_CONTENT,MOVIE_JANRE,MOVIE_AGE,MOVIE_REL_DATE,MOVIE_DIRECTOR,MOVIE_STAR,MOVIE_COUNTRY,MOVIE_RUNTIME,MOVIE_POSTER,MOVIE_TRAILER,MOVIE_STATE,MOVIE_COUNT) values (7,'12 ������','9.11 �׷� ���� 11�ϰ��� ����� ����!
5�� ���� ���� vs. 12���� ������ ���
 
�� ���谡 ����� ��� �־��� �׷� �߻� 15�� ��,
''��ġ(ũ���� �𽺿���)''�� ����ϴ� ������ �ڷ� �� ä
����� ���� ������ ���� 11���� ������ ������ �Բ�
Ż������ ������ �������Ͻ�ź�� �����Ѵ�.
 
�װ����� �׵��� ��ٸ��� �ִ� ���� 5�� ���� ������
������ �¸��� �� ���� �Ұ����� ������̴�!
 
��Ƴ��� Ȯ�� 0%, �׷��� �ݵ�� �����ؾ߸� �ϴ� ����!
 
2018�� ù ��° ���� ��Ϲ����Ͱ� ��������!',8,15,'18-01-31','���ݶ��� ǽ��','ũ���� �𽺿��� ,  ����Ŭ �䳪 ,  ����Ŭ ����','�̱�',130,'12_thfwutm.jpg','ROrrSbwTmoA','0',0);


-- insert member_tbl
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('movie','123','��ȭ������','010-1111-2345','movie@baobob.com','19940226','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',2600,2,3100,to_timestamp('18/01/24 15:27:03.071000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('res','123','�Ĵ������','010-1234-5678','res@baobob.com','20180101','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,4,0,to_timestamp('18/01/24 15:29:45.910000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('res1','123','�Ĵ�1������','010-1111-1111','res1@baobob.com','20180101','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,51,0,to_timestamp('18/01/24 15:32:50.262000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('res2','123','�Ĵ�2������','010-2222-2222','res2@baobob.com','20180101','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,52,0,to_timestamp('18/01/24 15:33:27.284000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('res3','123','�Ĵ�3������','010-3333-3333','res3@baobob.com','20180101','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,53,0,to_timestamp('18/01/24 15:34:04.643000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('park','123','����������','010-7733-3333','res3@baobob.com','19720121','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,7,0,to_timestamp('18/01/26 15:34:04.643000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('res11','123','��˹�','010-1122-1122','res11@baobob.com','20180101','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,9,0,to_timestamp('18/01/24 15:35:53.724000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('res12','123','�ھ˹�','010-1122-1122','res12@baobob.com','20180101','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,9,0,to_timestamp('18/01/24 15:40:29.948000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('res13','123','�־˹�','010-1122-1122','res13@baobob.com','20180101','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,9,0,to_timestamp('18/01/24 15:49:00.640000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('res21','123','�̾˹�','010-1122-1122','res21@baobob.com','20180101','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,9,0,to_timestamp('18/01/24 15:49:29.900000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('res22','123','���˹�','010-1122-1122','res22@baobob.com','20180101','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,9,0,to_timestamp('18/01/24 15:50:37.825000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('res23','123','�Ӿ˹�','010-1122-1122','res23@baobob.com','20180101','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,9,0,to_timestamp('18/01/24 15:51:00.994000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('lgt','123','�Ӱ���','010-8586-1906','lgt@naver.com','19890427','��','���� ��õ�� ���굿 147-63',0,9,0,to_timestamp('18/01/24 15:51:18.852000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('res31','123','�ξ˹�','010-1122-1122','res31@baobob.com','20180101','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,9,0,to_timestamp('18/01/24 15:51:20.881000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('res32','123','���˹�','010-1122-1122','res32@baobob.com','20180101','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,9,0,to_timestamp('18/01/24 15:51:44.538000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('res33','123','���˹�','010-1122-1122','res33@baobob.com','20180101','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,9,0,to_timestamp('18/01/24 15:52:11.220000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('phc','123','����â','010-1234-1234','phc@naver.com','19940226','��','����Ư����ġ�� ���ֽ� �쵵�� ��������� 1(����)',0,9,0,to_timestamp('18/01/24 15:53:11.786000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('ymk','123','���ΰ�','010-0303-0303','ymk@naver.com','19930303','��','���� ���Ǳ� ������� 2(��õ��)',0,9,0,to_timestamp('18/01/24 15:55:21.983000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('ybh','123','����ȣ','010-1225-1225','ybh@naver.com','19751225','��','���� ö���� ������ ������ 7(��ź��)',2800,9,3800,to_timestamp('18/01/24 15:56:18.054000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('chg','123','������','010-0201-0201','chg@naver.com','19990201','��','��� ���ý� ����� 14(���絿)',0,9,0,to_timestamp('18/01/24 15:57:25.290000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('mhj','123','������','010-0625-0625','mhj@naver.com','19930625','��','���� ������ ������ 403(û�㵿,LG û�����)',0,9,0,to_timestamp('18/01/24 15:58:31.486000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('khs','123','��ȣ��','010-0304-0304','khs@naver.com','19920304','��','��õ ��ȭ�� ��ȭ�� ����� 3(��û��)',0,9,0,to_timestamp('18/01/24 15:59:52.479000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('pcs','123','��â��','010-1818-1818','pcs1818@naver.com','19861818','��','���� ���� �������� 1(���굿)',0,13,0,to_timestamp('18/01/24 16:01:03.076000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('psn','123','�ڼ���','010-1234-1234','sunrise555@empas.com','19890427','��','���� ���۱� ���Ƿ�30�� 27(��絿,����Ǫ������)',0,9,0,to_timestamp('18/01/24 16:03:44.285000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('kjw','123','������','010-0103-0103','kjw@naver.com','19940103','��','�뱸 ���� ���� 3(���)',0,9,0,to_timestamp('18/01/24 16:06:01.882000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('psr','123','�ڼҶ�','010-9738-9568','psr@naver.com','19931128','��','��� ��õ�� �賲�� 19(��,���϶����� ���給����)',0,9,0,to_timestamp('18/01/24 16:07:40.036000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('lsh','123','�̼�ȣ','010-0612-0612','lsh@naver.com','19840612','��','���� ����� ���Ǹ� ���ֱ� 2(���긮)',0,9,0,to_timestamp('18/01/24 16:09:45.708000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('host','123','�Ѱ�����','010-1122-1122','host@baobob.com','20180101','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,1,0,to_timestamp('18/01/24 16:11:07.930000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('sdk@naver.com','123','�ŵ�ŭ','010-0913-0913','sdk@naver.com','19910913','��','���� ���� ������ ����������� 26(������)',0,9,0,to_timestamp('18/01/24 16:13:25.780000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('gockd','123','��ũâ','010-1010-1010','gjrjrem123@naver.com','19940101','��','���� ��õ�� ���������2�� 123(���굿,����޸����2��) 410',0,9,0,to_timestamp('18/01/24 16:16:29.617000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('kis','123','���ϼ�','010-0625-0625','kis@korea.love','19450625','��','��� ���ֽ� ź���� �ʽ·� 369(������,���λ�����������)',0,9,0,to_timestamp('18/01/24 16:19:50.644000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('kji','123','������','010-0625-0625','kji@korea.love','19560625','��','���� ���� ������ ����������� 26(������)',0,9,0,to_timestamp('18/01/24 16:21:03.710000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('kje','123','������','010-0625-0625','kje@korea.love','19810625','��','���� ���� ������ ����������� 223-1(������)',0,9,0,to_timestamp('18/01/24 16:21:51.873000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('obama','123','�������ٸ�','010-0512-0512','obama@yahoo.net','19640512','��','���� ���ϱ� �ﰢ��� 43(������,���� ����� ����ķ��)',0,9,0,to_timestamp('18/01/24 16:23:04.521000000','RR/MM/DD HH24:MI:SSXFF'),null);
Insert into BAOBOB.MEMBER_TBL (MEMBER_ID,MEMBER_PWD,MEMBER_NAME,MEMBER_TEL,MEMBER_EMAIL,MEMBER_BIRTH,MEMBER_SEX,MEMBER_ADDRESS,MEMBER_POINT,MEMBER_STEP,MEMBER_CUMPOINT,MEMBER_REG_DATE,MEMBER_IMG) values ('trump','123','Ʈ����','010-1212-1212','trump@yahoo.net','19601212','��','���� ������ �ʷϸ�����22�� 24(ȭ�,���������)',0,9,0,to_timestamp('18/01/24 16:24:14.827000000','RR/MM/DD HH24:MI:SSXFF'),null);

-- insert faq_tbl
INSERT INTO faq_tbl VALUES ( faq_tbl_SEQ.nextval, '��ȭ�� �̿�', '������� �ȳ�', '�ٿ����� ��ȭ �� �������￡ ���� ����(���� �����)�� �ؼ��մϴ�.<br>����� 29���� ������ [�� 12�� �̻������/ �� 15�� �̻������]�� ����̶�<br>�θ� �� ��ȣ�ڸ� �����ϴ� ��� ���(����) ������ �����մϴ�.<br>�ݵ�� ��ȣ���� ������ �ʿ����� �����Ͽ� �ֽñ� �ٶ��ϴ�.<br>��, û�ҳ�����Ұ� ��ȭ�� ��ȣ�� ���ݰ� ������� <br>�� 18���̸��̰ų� ���� ������ �����Ͽ��� ���߰� �������� û�ҳ� �� ������ ������ ���� �Ұ��մϴ�.');
INSERT INTO faq_tbl VALUES ( faq_tbl_SEQ.nextval, '��ȭ�� �̿�', '�нǹ��� ã�� �;��', '��ȭ���� �̿��Ͻôٰ� ������ǰ�� �н��Ͻ� ��쿡�� Ȩ������ [������] - [�нǹ� ���� �ٷΰ���] �Ǵ� [����������] - [�нǹ� ����]���� �нǹ� ������ �Ͻ� �� �ֽ��ϴ�.');
INSERT INTO faq_tbl VALUES ( faq_tbl_SEQ.nextval, '�¶���', '�¶��� ���� �� ���ų��� Ȯ���ϰ� �;��', '���ͳ� ������ ���ų����� �ٽ� Ȯ�� �Ͻ÷���<br>- Ȩ������ : [�α���] �� [����������] �� [���� ����]���� Ȯ�� �����մϴ�.<br>- ����� : [�α���] �� ���� ��� [MY] �޴� �� [����������] �� [���� ����]���� Ȯ�� �����մϴ�.');
INSERT INTO faq_tbl VALUES ( faq_tbl_SEQ.nextval, '�¶���', '��ȭ ����� ��� �ۼ��ϳ���?', '[Ȩ������/�����] �� [��ȭ] �޴����� �ش� ��ȭ�� �����Ͻø� ���並 �ۼ��Ͻ� �� �ֽ��ϴ�.<br>���� �ۼ��� ���� ���� ���� ���Ͽ� �ۼ� �����ϸ�, �� �Ǵ� 50p�� L.POINT�� �����˴ϴ�.<br>�ۼ��Ͻ� ����� [����������] �� [����α�]���� Ȯ���Ͻ� �� �ֽ��ϴ�.');
INSERT INTO faq_tbl VALUES ( faq_tbl_SEQ.nextval, 'ȸ��', '��ȸ������ ���Ű� �����Ѱ���?', '��ȸ�� ��� ���� �Ŀ� ���� �������� (�̸�+�޴�����ȣ+��й�ȣ 4�ڸ�) ���� �� ���� �����մϴ�.<br>��ȸ�� �α��� �� ���� �� ����Ȯ��/��� �޴��� �̿밡���ϸ� <br>������, ���α��� ������ ��Ÿ ����/���� ������ ����� �� �����ϴ�.');
INSERT INTO faq_tbl VALUES ( faq_tbl_SEQ.nextval, 'ȸ��', '���������� ��𿡼� ������ �� �ֳ���?', 'Ȩ������ [����������] �� [���� ����(���� ���)]���� �����մϴ�. ');
INSERT INTO faq_tbl VALUES ( faq_tbl_SEQ.nextval, 'ȸ��', '���̽��� ȸ���� ���������� ��� �����ϳ���?', 'Ȩ������ [�α���] �� [��й�ȣ ã��]�� �Ͻø� ���̽��Ͽ� ��ϵ� �̸��Ϸ� ���� �� ��й�ȣ�� ���۵˴ϴ�.');
INSERT INTO faq_tbl VALUES ( faq_tbl_SEQ.nextval, 'ȸ��', 'Ż���ϸ� �� ���������� ��� ���� �ǳ���?', 'Ż�� ��� ������ ���������� ��� �����˴ϴ�. <br>��, ���ڻ�ŷ� �� �Һ��� �Ǹ� ��ȣ�� ���� �������� ���� �ŷ����� ���� �������� ���� �Ⱓ ���� ������ �� �ֽ��ϴ�.');
INSERT INTO faq_tbl VALUES ( faq_tbl_SEQ.nextval, 'ȸ��', '��й�ȣ�� �Ҿ���Ⱦ��', 'Ȩ������ [�α���] - [��й�ȣ ã��]�� �̿��Ͻø� ��й�ȣ�� Ȯ���Ͻ� �� �ֽ��ϴ�.');
INSERT INTO faq_tbl VALUES ( faq_tbl_SEQ.nextval, 'ȸ��', 'ȸ�������� ��� �ϳ���?', 'Ȩ������ ���� ����� [ȸ������] ��ư�� �����ų�, [�α���] - [ȸ������] ��ư�� Ŭ���� �¶���(Ȩ������, ����� APP) ȸ���� ������ �� �ֽ��ϴ�.');
INSERT INTO faq_tbl VALUES ( faq_tbl_SEQ.nextval, 'ȸ��', 'ȸ��Ż��� ��� �ϳ���?', 'Ȩ������ [����������] �� [���� ����(���� ���)]���� [ȸ�� Ż��] ��ư�� Ŭ���Ͽ� ȸ�� Ż�� �����մϴ�.');
INSERT INTO faq_tbl VALUES ( faq_tbl_SEQ.nextval, '����Ʈ', '����Ʈ Ȯ���� ��� �ϳ���?', 'Ȩ������ [����������]�� ���ؼ� ����Ʈ�� Ȯ���Ͻ� �� �ֽ��ϴ�.');
INSERT INTO faq_tbl VALUES ( faq_tbl_SEQ.nextval, '����Ʈ', 'Ż�� �� �簡�Խ� ����Ʈ�� �Ҹ�ǳ���?', 'Ż�� �� �ڵ� �Ҹ� �˴ϴ�');

-- insert board_tbl
INSERT INTO board_tbl(BOARD_INDEX, MEMBER_ID, BOARD_PWD, BOARD_TYPE, BOARD_SUBJECT,BOARD_CONTENT,BOARD_IMG,BOARD_READCNT,BOARD_REF,BOARD_REF_STEP,BOARD_REF_LEVEL,BOARD_REG_DATE,BOARD_IP)
VALUES (1, 'movie', '123', 2, '��ȭ ���� �����մϴ�.','û�ҳ� �Ұ� ��ȭ�� ��ȣ�� ����� �� �� �ֳ���?','null',0,0,0,0,SYSTIMESTAMP, 111);
INSERT INTO board_tbl(BOARD_INDEX, MEMBER_ID, BOARD_PWD, BOARD_TYPE, BOARD_SUBJECT,BOARD_CONTENT,BOARD_IMG,BOARD_READCNT,BOARD_REF,BOARD_REF_STEP,BOARD_REF_LEVEL,BOARD_REG_DATE,BOARD_IP)
VALUES (2, 'movie', '123', 2, '�Ĵ� ���� �����մϴ�.','�ܻ�ǳ���?','null',0,0,0,0,SYSTIMESTAMP, 111);
INSERT INTO board_tbl(BOARD_INDEX, MEMBER_ID, BOARD_PWD, BOARD_TYPE, BOARD_SUBJECT,BOARD_CONTENT,BOARD_IMG,BOARD_READCNT,BOARD_REF,BOARD_REF_STEP,BOARD_REF_LEVEL,BOARD_REG_DATE,BOARD_IP)
VALUES (3, 'movie', '123', 3, '������ �Ҿ���Ⱦ��','��ȭ�� 3������ �ڵ����� �Ҿ���Ƚ��ϴ�. ������ ������7�̿���.','null',0,0,0,0,SYSTIMESTAMP, 111);

COMMIT;



-- �Ĵ� �߰� SQL

Insert into BAOBOB.RESTAURANT_TBL (RESTAURANT_INDEX,RESTAURANT_TEL,RESTAURANT_NAME) values (1,'02-1234-1234','��Ű��Ű (�Ȫ��ɪ�)');
Insert into BAOBOB.RESTAURANT_TBL (RESTAURANT_INDEX,RESTAURANT_TEL,RESTAURANT_NAME) values (2,'02-1234-2345','�پ�');
Insert into BAOBOB.RESTAURANT_TBL (RESTAURANT_INDEX,RESTAURANT_TEL,RESTAURANT_NAME) values (3,'02-1234-3456','BoutBack');
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,0,0,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,1,0,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,2,0,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,3,0,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,4,0,4,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,5,0,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,6,0,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,7,0,7,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,8,0,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,9,1,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,10,1,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,11,1,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,12,1,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,13,1,4,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,14,1,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,15,1,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,16,1,7,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,17,1,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,18,2,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,19,2,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,20,2,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,21,2,3,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,22,2,4,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,23,2,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,24,2,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,25,2,7,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,26,2,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,27,3,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,28,3,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,29,3,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,30,3,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,31,3,4,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,32,3,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,33,3,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,34,3,7,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,35,3,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,36,4,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,37,4,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,38,4,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,39,4,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,40,4,4,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,41,4,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,42,4,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,43,4,7,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,44,4,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,45,5,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,46,5,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,47,5,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,48,5,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,49,5,4,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,50,5,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,51,5,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,52,5,7,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,53,5,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,54,6,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,55,6,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,56,6,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,57,6,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,58,6,4,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,59,6,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,60,6,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,61,6,7,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,62,6,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,63,7,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,64,7,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,65,7,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,66,7,3,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,67,7,4,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,68,7,5,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,69,7,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,70,7,7,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,71,7,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,72,8,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,73,8,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,74,8,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,75,8,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,76,8,4,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,77,8,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,78,8,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,79,8,7,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (1,80,8,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,2,0,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,3,0,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,4,0,4,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,5,0,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,6,0,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,7,0,7,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,8,0,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,9,1,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,10,1,1,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,11,1,2,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,12,1,3,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,13,1,4,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,14,1,5,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,15,1,6,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,16,1,7,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,17,1,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,18,2,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,19,2,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,20,2,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,21,2,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,22,2,4,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,23,2,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,24,2,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,25,2,7,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,26,2,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,27,3,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,28,3,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,29,3,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,30,3,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,31,3,4,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,32,3,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,33,3,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,34,3,7,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,35,3,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,36,4,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,37,4,1,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,38,4,2,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,39,4,3,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,40,4,4,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,41,4,5,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,42,4,6,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,43,4,7,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,44,4,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,45,5,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,46,5,1,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,47,5,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,48,5,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,49,5,4,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,50,5,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,51,5,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,52,5,7,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,53,5,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,54,6,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,55,6,1,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,56,6,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,57,6,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,58,6,4,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,59,6,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,60,6,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,61,6,7,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,62,6,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,63,7,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,64,7,1,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,65,7,2,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,66,7,3,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,67,7,4,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,68,7,5,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,69,7,6,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,70,7,7,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,71,7,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,72,8,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,73,8,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,74,8,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,75,8,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,76,8,4,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,77,8,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,78,8,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,79,8,7,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,80,8,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,2,0,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,3,0,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,4,0,4,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,5,0,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,6,0,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,7,0,7,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,8,0,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,9,1,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,10,1,1,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,11,1,2,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,12,1,3,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,13,1,4,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,14,1,5,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,15,1,6,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,16,1,7,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,17,1,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,18,2,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,19,2,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,20,2,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,21,2,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,22,2,4,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,23,2,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,24,2,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,25,2,7,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,26,2,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,27,3,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,28,3,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,29,3,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,30,3,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,31,3,4,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,32,3,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,33,3,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,34,3,7,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,35,3,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,36,4,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,37,4,1,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,38,4,2,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,39,4,3,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,40,4,4,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,41,4,5,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,42,4,6,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,43,4,7,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,44,4,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,45,5,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,46,5,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,47,5,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,48,5,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,49,5,4,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,50,5,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,51,5,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,52,5,7,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,53,5,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,54,6,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,55,6,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,56,6,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,57,6,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,58,6,4,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,59,6,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,60,6,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,61,6,7,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,62,6,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,63,7,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,64,7,1,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,65,7,2,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,66,7,3,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,67,7,4,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,68,7,5,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,69,7,6,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,70,7,7,1,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,71,7,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,72,8,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,73,8,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,74,8,2,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,75,8,3,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,76,8,4,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,77,8,5,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,78,8,6,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,79,8,7,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,80,8,8,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,0,0,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (2,1,0,1,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,0,0,0,0,null);
Insert into BAOBOB.RESTAURANT_TABLE_TBL (RESTAURANT_INDEX,RESTAURANT_TABLE_INDEX,TABLE_ROW,TABLE_COL,TABLE_STATE,RESTAURANT_SCHEDULE_INDEX) values (3,1,0,1,0,null);
COMMIT;

Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (1,1,'������','������.jpg','�ٻ��� �������� �� ���� �ø��� ���� ¬©�� ����ҽ��� ���� �ξ�Դ� ����',12000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (2,1,'������Ű«��','������Ű«��.jpg','������ ���� �ʰ� �߰� �������� �츰 ������ ���ֿ� ���� ���� ��� ���� ��� ���������� �ϳ��� �ҿ��� �̷������ ������Ű������ ��������',11000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (3,1,'��� �ʹ�','����ʹ�.jpg','�ż��� ȸ�� ������ ��� ���� ��� �ʹ�
(����, ����, ����¡��, ����, ���, ��ġ, ��ġ�˷�, ����, ���)',49000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (4,1,'��� �ع���','����ع���.jpg','�پ��� �ع��� ��ä�� �־� ǳ���� ���� ��ǰ�� ��� �ع���',16000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (5,1,'��� �ػ깰','����ػ깰.jpg','�ż��� �ع��� �������� �پ��ϰ� ���� �� �ִ� ��� �ػ깰',29000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (6,1,'��� ȸ','���ȸ.jpg','�ڿ��� ���� �״�� ����־� �Ծȿ��� ��Ƽ����µ��� ��� ȸ
(����, �췰, ����, ����)',55000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (7,1,'������','������.jpg','������ ��°�� ��� ���� �ż��ϰ�, ���� ���� ���� �� �ִ� ������',49000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (8,1,'���ڳ�̾߳�','���ڳ�̾߳�.jpg','���� Ǭ �а��縦 �⺻���� ��⳪ ���з� �� ��ä�� ���� ö�ǿ��� ���� �ҽ��� �ѷ� �Դ� �丮',13000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (1,2,'��ġ�','��ġ�.jpg','ǫ �;� ���� ���� �ű�ġ�� �������� ������ �ż��� ������Ⱑ ��ȭ�Ӱ� ��췯�� ���ִ� ��ġ�',9000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (2,2,'��������','��������.jpg','������ ������ �񺭸Դ� ��������',10000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (3,2,'���ܺ����','���ܺ����.jpg','���⸸�ص� ��θ����� ��� Ǫ���� ������ ���ܺ����',9000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (4,2,'�δ��','�δ��.jpg','���縮�� ���, ��, �ҽ��� �߰� ���� ��� �� �پ��� ��Ḧ ���� �����Դ� ���ִ� �δ��',10000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (5,2,'�Ұ�� ������','�Ұ��������.jpg','��ū�� ������ �̱��̱� ������ �Ұ�Ⱑ �� ������',12000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (6,2,'�ҺҰ�ⵤ��','�ҺҰ�ⵤ��.jpg','Ǫ���� ��� �޴��� ���� ��ǰ�� �ҺҰ�ⵤ��',11000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (7,2,'���κ��','���κ��.jpg','�������� ���κ��� ȯ������ ��',9000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (8,2,'���������','���������.jpg','������ ���� ���ִ� �������̰� ��ǰ�� ���������',10000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (9,2,'ö��ġ���ġ������','ö��ġ���ġ������.jpg','�Ʊ��ڱ��� ���־�� �ѳ��� �� ���� ���� ö��ġ���ġ������',9000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (10,2,'�ᳪ�������','�ᳪ�������.jpg','���� ���� �ᳪ���� �񺭸Դ� �ᳪ�������',9000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (3,3,'�������ũ(1����)','����ɟ�.jpg','Į�θ� ���� No!!!
�Ծȿ��� ��� ��� ����Ʈ�� �Ա� ���� ������ �������ũ',7000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (1,3,'�ɵ�ɽ�����ũ','�ɵ�ɽ�����ũ.jpg','������ ���� �ѿ츸�� �����ϴ� BoutBack SteakHouse�� �ɵ�� ������ũ',18000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (2,3,'ĥ�� ����','ĥ�� ����.jpg','�ż��� �丶��� �ƻ��� ��Ŭ, ĥ���� ����� �ٻ��� ���ʰ� ������ ���� ����',9000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (4,3,'����������','����������.jpg','��޽����� ������ �������� ���ְ� ������ �޴�',20000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (5,3,'�ٿ��� ������ũ','�ٿ��佺����ũ.jpg','BoutBack SteakHouse�� ��ǥ �޴�!!!
�ٿ����̶�� �̸��� �ɸ´� Ŀ�ٶ� ������ũ',20000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (6,3,'������ũ ������','������ũ������.jpg','��⸦ �����鼭 ���̾�Ʈ�� �� �� �ִٰ�?
������ũ ������',12000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (7,3,'�Ƚɽ�����ũ','�Ƚɽ�����ũ.jpg','�����ҽ��� ǳ���� ���� ������ ���� ǳ���ϰ� ������ִ� �α� ��ǰ',19000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (8,3,'���� ������ũ','�������ũ.jpg','�븣���� ���� Ư���� ���� ���� �� �ִ� ���� ������ũ',13000);
Insert into BAOBOB.RESTAURANT_MENU_TBL (RESTAURANT_MENU_INDEX,RESTAURANT_INDEX,RESTAURANT_MENU_NAME,RESTAURANT_MENU_IMG,RESTAURANT_MENU_CONTENT,RESTAURANT_MENU_PRICE) values (9,3,'������','������.jpg','���� �߰��ִ� ��ǰ���� �Ұ��� ��ä���� ���� ���� �� �ִ� ��ǰ',8000);




--review_tbl
Insert into BAOBOB.REVIEW_TBL (REVIEW_INDEX,REVIEW_GRADE,REVIEW_CONTENT,MEMBER_ID,REVIEW_STATE,REVIEW_REG_DATE) values (14,'5','There were eight of us all together and we all agreed that the streak was the best dish on the menu.','trump',2,to_timestamp('18/01/30 11:50:13.000000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into BAOBOB.REVIEW_TBL (REVIEW_INDEX,REVIEW_GRADE,REVIEW_CONTENT,MEMBER_ID,REVIEW_STATE,REVIEW_REG_DATE) values (15,'4','����̿���¡������ũ�� ������ ���� �Ա� ������ ��������̶�� �����մϴ�','ybh',2,to_timestamp('18/01/30 11:51:23.000000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into BAOBOB.REVIEW_TBL (REVIEW_INDEX,REVIEW_GRADE,REVIEW_CONTENT,MEMBER_ID,REVIEW_STATE,REVIEW_REG_DATE) values (1,'4','���� ���� ���ε� ���� ���� ��� �Ϻ����� �Դ� ����ī�� ���� �������������.','mhj',2,to_timestamp('18/01/30 11:28:03.000000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into BAOBOB.REVIEW_TBL (REVIEW_INDEX,REVIEW_GRADE,REVIEW_CONTENT,MEMBER_ID,REVIEW_STATE,REVIEW_REG_DATE) values (2,'4','���� �ʹ� ���ִٰ� �� ������ �� �˾�ä�ð� �ȳ��� ���ֽð�..
���ݱ��� ���� �ϽĴ��߿� �� ���� ���������ϴ�. ','chg',2,to_timestamp('18/01/30 11:29:16.000000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into BAOBOB.REVIEW_TBL (REVIEW_INDEX,REVIEW_GRADE,REVIEW_CONTENT,MEMBER_ID,REVIEW_STATE,REVIEW_REG_DATE) values (3,'5','���� ������ �ӵ��� ���� �����ϰ� ����� ���� �޾Ҵٴ� ������ ������ϴ�. �� ������ ���� ģ���� ���񽺸� ���̴ּ� Ȧ �����е鵵 ����帳�ϴ�.','ymk',2,to_timestamp('18/01/30 11:30:50.000000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into BAOBOB.REVIEW_TBL (REVIEW_INDEX,REVIEW_GRADE,REVIEW_CONTENT,MEMBER_ID,REVIEW_STATE,REVIEW_REG_DATE) values (4,'5','�㸧�� �� �� ������ ������ �����⿡�� ���ִ� ���ø� ���� �� �ֽ��ϴ�. ','phc',2,to_timestamp('18/01/30 11:33:14.000000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into BAOBOB.REVIEW_TBL (REVIEW_INDEX,REVIEW_GRADE,REVIEW_CONTENT,MEMBER_ID,REVIEW_STATE,REVIEW_REG_DATE) values (5,'5','���� �̵�� ��������� �Ҹ���� ������ ���� ���ƺ��Դϴ�. ȸ�� ���� �ſ� ���� ����� ���ƿ�. ���ݵ� �ո����� ���̶� �δ��� ��� �����ϴ�','kjw',2,to_timestamp('18/01/30 11:34:25.000000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into BAOBOB.REVIEW_TBL (REVIEW_INDEX,REVIEW_GRADE,REVIEW_CONTENT,MEMBER_ID,REVIEW_STATE,REVIEW_REG_DATE) values (6,'4','�̽��� ���� ��������̶� ��븦 �ϰ� �������� ���µ� �ʹ� ���Ҿ��!
','kjw',2,to_timestamp('18/01/30 11:36:15.000000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into BAOBOB.REVIEW_TBL (REVIEW_INDEX,REVIEW_GRADE,REVIEW_CONTENT,MEMBER_ID,REVIEW_STATE,REVIEW_REG_DATE) values (7,'3','������ �� ��δٰ� ��������µ� �粯 ������ ������ �͹��� ���� ������ �ƴϴ�����
��ġ�� �������� �� �� ���̿���','phc',2,to_timestamp('18/01/30 11:36:49.000000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into BAOBOB.REVIEW_TBL (REVIEW_INDEX,REVIEW_GRADE,REVIEW_CONTENT,MEMBER_ID,REVIEW_STATE,REVIEW_REG_DATE) values (8,'4','��������� ����ϳ��ϳ� �����ϰ�, ������ ���� ������ �־�,
����� ������� ���⿡ ���� ���̶�� �����մϴ�.','mhj',2,to_timestamp('18/01/30 11:38:20.000000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into BAOBOB.REVIEW_TBL (REVIEW_INDEX,REVIEW_GRADE,REVIEW_CONTENT,MEMBER_ID,REVIEW_STATE,REVIEW_REG_DATE) values (9,'3','���ݵ� ������ ���̰�, �޴��� �پ��ϰ� ���ƿ�~','chg',2,to_timestamp('18/01/30 11:39:03.000000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into BAOBOB.REVIEW_TBL (REVIEW_INDEX,REVIEW_GRADE,REVIEW_CONTENT,MEMBER_ID,REVIEW_STATE,REVIEW_REG_DATE) values (10,'4','��⵵ ���ִµ� ��ġ� �ʹ� ���־�� �ٵ� ���� ����̴µ��� ���� ������ �������̼� �������־ �� ���ƿ�','khs',2,to_timestamp('18/01/30 11:39:45.000000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into BAOBOB.REVIEW_TBL (REVIEW_INDEX,REVIEW_GRADE,REVIEW_CONTENT,MEMBER_ID,REVIEW_STATE,REVIEW_REG_DATE) values (11,'5','�Ǹ��� ������ ���İ� �������� ������ �� �̻��� �� ���ٴ� ������ ��ϴ�','psn',2,to_timestamp('18/01/30 11:43:47.000000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into BAOBOB.REVIEW_TBL (REVIEW_INDEX,REVIEW_GRADE,REVIEW_CONTENT,MEMBER_ID,REVIEW_STATE,REVIEW_REG_DATE) values (12,'4','������ũ �β�,��, ���� ��� �����ؿ�.
�̰����� �� �Ծ�� �;����. ���� ���� �Ʊ��� �ʾҾ��.','kji',2,to_timestamp('18/01/30 11:44:46.000000000','RR/MM/DD HH24:MI:SSXFF'));
Insert into BAOBOB.REVIEW_TBL (REVIEW_INDEX,REVIEW_GRADE,REVIEW_CONTENT,MEMBER_ID,REVIEW_STATE,REVIEW_REG_DATE) values (13,'5','��~~~��~ ������ũ �ʹ� ���־��! ���� �Ⱥ����� ��� ��ƿ�~����!!!','khs',2,to_timestamp('18/01/30 11:45:49.000000000','RR/MM/DD HH24:MI:SSXFF'));
--restaurant_review_tbl
Insert into BAOBOB.RESTAURANT_REVIEW_TBL (REVIEW_INDEX,RESTAURANT_INDEX) values (14,3);
Insert into BAOBOB.RESTAURANT_REVIEW_TBL (REVIEW_INDEX,RESTAURANT_INDEX) values (15,3);
Insert into BAOBOB.RESTAURANT_REVIEW_TBL (REVIEW_INDEX,RESTAURANT_INDEX) values (1,1);
Insert into BAOBOB.RESTAURANT_REVIEW_TBL (REVIEW_INDEX,RESTAURANT_INDEX) values (2,1);
Insert into BAOBOB.RESTAURANT_REVIEW_TBL (REVIEW_INDEX,RESTAURANT_INDEX) values (3,1);
Insert into BAOBOB.RESTAURANT_REVIEW_TBL (REVIEW_INDEX,RESTAURANT_INDEX) values (4,1);
Insert into BAOBOB.RESTAURANT_REVIEW_TBL (REVIEW_INDEX,RESTAURANT_INDEX) values (5,1);
Insert into BAOBOB.RESTAURANT_REVIEW_TBL (REVIEW_INDEX,RESTAURANT_INDEX) values (6,2);
Insert into BAOBOB.RESTAURANT_REVIEW_TBL (REVIEW_INDEX,RESTAURANT_INDEX) values (7,2);
Insert into BAOBOB.RESTAURANT_REVIEW_TBL (REVIEW_INDEX,RESTAURANT_INDEX) values (8,2);
Insert into BAOBOB.RESTAURANT_REVIEW_TBL (REVIEW_INDEX,RESTAURANT_INDEX) values (9,2);
Insert into BAOBOB.RESTAURANT_REVIEW_TBL (REVIEW_INDEX,RESTAURANT_INDEX) values (10,2);
Insert into BAOBOB.RESTAURANT_REVIEW_TBL (REVIEW_INDEX,RESTAURANT_INDEX) values (11,3);
Insert into BAOBOB.RESTAURANT_REVIEW_TBL (REVIEW_INDEX,RESTAURANT_INDEX) values (12,3);
Insert into BAOBOB.RESTAURANT_REVIEW_TBL (REVIEW_INDEX,RESTAURANT_INDEX) values (13,3);
COMMIT;







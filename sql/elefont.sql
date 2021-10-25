--===========================================
-- semi_project :  elefont 계정 생성
--===========================================
alter session set "_oracle_script" = true;
--drop user elefont cascade;
--
--create user elefont
--identified by elefont
--default tablespace users;
--
--alter user elefont quota unlimited on users;
--
--grant connect, resource to elefont;
--
--view 테이블 생성 권한 필요
--grant create view to elefont;

--============================================

CREATE TABLE attachment(
    att_no number ,
    member_no varchar2(200) not null,
    comm_no varchar2(500),
    original_filename varchar2(255) not null,
    renamed_filename varchar2(255) not null,
    reg_date Date default sysdate,
    
    constraint pk_attachment_att_no primary key(att_no)
 
);

select * from member;
update member set member_role = 'A' where member_no =1;
commit;


CREATE TABLE member (
	member_no	varchar2(200) not null ,
	member_id	varchar2(20) not null ,
	member_pwd	varchar2(300) not null ,
	member_name	varchar2(20) not null ,
	member_gender	varchar2(1) not null ,
	member_email	varchar2(30) not null ,
	member_phone	char(11) not null ,
	member_birthday	Date,
	member_job varchar2(50),
	member_point	number	DEFAULT 200	,
	member_reg_date Date	DEFAULT sysdate,
	member_quit_yn	char(1)	 DEFAULT 'N',
	member_role	char(1)	 DEFAULT 'U',
    att_no number,
	constraint ck_member_member_gender check(member_gender in ('M', 'F')),
	constraint ck_member_member_quit_yn check(member_quit_yn in ('Y','N')),
	constraint ck_member_member_role check(member_role in ('U', 'A', 'S')),
    constraint uq_member_id unique(member_id),
    constraint uq_member_email unique(member_email),
    constraint fk_att_no foreign key(att_no) references attachment(att_no)
);

ALTER TABLE member ADD CONSTRAINT PK_member_member_NO PRIMARY KEY (
	member_no
);

create sequence seq_member_no;

--drop table member_deleted;
CREATE TABLE member_deleted (
    member_no varchar2(200),
	member_id	varchar2(20)	,
	member_pwd	varchar2(300)	,
	member_name	varchar2(20)	,
	member_gender	varchar2(1)	,
	member_email	varchar2(30)	,
	member_phone	char(11) ,
	member_birthday Date	,
    member_job	varchar2(50),
    member_point number ,
    member_reg_date Date ,
    member_quit_yn	char(2)	 not null,
	member_role	char(1),
    att_no number,
	member_quit_date	Date	DEFAULT sysdate
);

CREATE OR REPLACE TRIGGER trg_member_deleted
    after
    delete on member
    for each row
BEGIN
        insert into
        member_deleted
        values
            (:old.member_no, 
            :old.member_id, 
            :old.member_pwd, 
            :old.member_name,
            :old.member_gender,
            :old.member_email,
            :old.member_phone,
            :old.member_birthday,
            :old.member_job,
            :old.member_point,
            :old.member_reg_date,
            :old.member_quit_yn,
            :old.member_role,
            :old.att_no,
            default
            );
end;
/


CREATE TABLE font (
	font_no	varchar2(500)	not null ,
	font_name	varchar2(200)	not null,
	font_url	varchar2(500)	not null ,
	font_price	number DEFAULT 300 not null ,
	font_discount_rate	number DEFAULT 1,
	font_like_count	number DEFAULT 0,
	font_view_count	number DEFAULT 0,
	font_purchased_count	number DEFAULT 0,
	font_reg_date		Date	 DEFAULT sysdate
);
ALTER TABLE font ADD CONSTRAINT PK_FONT_FONT_NO PRIMARY KEY (
	font_no
);


CREATE TABLE font_category (
	category_code	varchar2(50)  not null ,
	font_no		varchar2(500)	not null ,
	category_is_free	char(1)	,
	category_lang		char(2)	not null ,
	category_style		varchar2(50)	,
	category_release_year	Date	not null,
	constraint ck_font_category_category_is_free check(category_is_free in ('Y', 'N')),
	constraint fk_font_category_font_no foreign key(font_no) references font(font_no)
);
ALTER TABLE font_category ADD CONSTRAINT PK_FONT_CATEGORY PRIMARY KEY (
	category_code,
	font_no
);


CREATE TABLE font_copyright (
	font_no	varchar2(500)	not null ,
	font_publisher		varchar2(50),
	font_designer		varchar2(50),
	font_root_url		varchar2(200)	,
	constraint fk_font_copyright_font_no foreign key(font_no) references font (font_no)
);
ALTER TABLE font_copyRight ADD CONSTRAINT PK_FONT_COPYRIGHT_FONT_NO PRIMARY KEY (
	font_no
);

CREATE TABLE cart (
	cart_no	varchar2(500)		not null ,
	font_no	varchar2(500)		not null ,
	cart_reg_date	Date	DEFAULT sysdate ,
	constraint fk_cart_font_no foreign key(font_no) references font (font_no)
);
ALTER TABLE cart ADD CONSTRAINT  PK_CART_CART_NO PRIMARY KEY (
    cart_no
);

CREATE TABLE member_cart (
	member_no	varchar2(200)		not null ,
	cart_no	varchar2(500)		not null,
	constraint fk_member_cart_member_no foreign key(member_no) references member(member_no),
	constraint fk_member_cart_cart_no foreign key(cart_no) references cart(cart_no)
);
ALTER TABLE member_cart ADD CONSTRAINT PK_member_CART_MEMBER_NO PRIMARY KEY (
	member_no,
	cart_no
);

CREATE TABLE like_font (
    member_no varchar2(200) not null,
	font_no	varchar2(500)		not null ,
	like_font_reg_date	Date	DEFAULT sysdate,
    
    constraint fk_like_font_member_no foreign key(member_no) references member(member_no),
	constraint fk_like_font_font_no foreign key(font_no) references font(font_no)
);
ALTER TABLE like_font ADD CONSTRAINT PK_LIKE_FONT_MEMBER_NO PRIMARY KEY (
    member_no,
	font_no
);


CREATE TABLE community (
    comm_no varchar2(500),
    comm_writer varchar2(20) not null,
    comm_content varchar2(2000) not null,
    comm_view_count number default 0,
    comm_like_count number default 0,
    comm_reg_date Date default sysdate,
    font_no varchar2(500),
    constraint pk_community_comm_no primary key (comm_no),
    constraint fk_community_font_no foreign key(font_no) references  font(font_no)
);


CREATE TABLE rep (
	rep_no number,
	rep_writer varchar2(20) not null,
	rep_content varchar2(2000) not null,
	rep_reg_date	Date	 default sysdate,
	font_no	varchar2(500) not null,
    comm_no varchar2(500),
    rep_level number not null,
    rep_ref number,
    constraint pk_rep_rep_no primary key(rep_no),
	constraint fk_rep_font_no foreign key(font_no) references font(font_no),
	constraint fk_rep_comm_no foreign key(comm_no) references community(comm_no),
    constraint fk_rep_ref foreign key(rep_ref) references rep(rep_no) on delete cascade
);


CREATE TABLE point (
	point_no	varchar2(200)	not null ,
	point_code	varchar2(1) not null ,
	point_val	varchar2(500)	not null ,
	point_date	Date	DEFAULT sysdate not null,
    point_name varchar2(200),
    constraint  fk_point_point_code foreign key(point_code) references point_category(point_code)
);
ALTER TABLE point ADD CONSTRAINT PK_POINT_POINT_NO PRIMARY KEY (
	point_no
);


CREATE TABLE member_point (
	point_no	varchar2(200)	not null ,
	member_no	varchar2(200)	not null ,
    constraint  fk_member_point_point_no foreign key (point_no) references point(point_no),
    constraint  fk_member_point_member_no foreign key(member_no) references member(member_no)
);
ALTER TABLE member_point ADD CONSTRAINT PK_member_POINT_POINT_NO PRIMARY KEY (
	point_no, member_no
);

--FK제약 없는테이블
CREATE TABLE community_deleted (
	comm_no	varchar2(500)		,
	comm_writer	varchar2(20)		,
	comm_content	varchar2(2000)		,
	comm_reg_date	Date		,
	comm_delete_date	Date	DEFAULT sysdate	
);

CREATE OR REPLACE TRIGGER trg_community_deleted
	after
	delete on community
	for each row
BEGIN
		insert into
		community_deleted
		values
		(:old.comm_no, :old.comm_writer, :old.comm_content, :old.comm_reg_date, default);
end;
/


--FK제약 없는테이블
CREATE TABLE rep_deleted (
    rep_no    number    ,
    rep_writer    varchar2(20)        ,
    rep_content    varchar2(2000)        ,
    rep_reg_date    Date        ,
    font_no    varchar2(500)        not null ,
    comm_no varchar2(500)  not null,
    rep_level number,
    rep_ref number,
    rep_delete_date Date DEFAULT sysdate 
);

CREATE OR REPLACE TRIGGER trg_rep_deleted
    after
    delete on rep
    for each row
  
BEGIN
        insert into
        rep_deleted
        values
        (:old.rep_no, :old.rep_writer, :old.rep_content, :old.rep_reg_date, :old.font_no, :old.comm_no, :old.rep_level,:old.rep_ref, default);

end;
/

--drop trigger trg_rep_deleted;


CREATE TABLE like_community (
	member_no varchar2(200),	
    comm_no varchar2(500),
	like_comm_reg_date Date default sysdate,
    constraint pk_like_community_member_no primary key(member_no, comm_no),
    constraint fk_like_community_member_no foreign key (member_no) references member(member_no),
    constraint fk_like_community_comm_no foreign key (comm_no) references community (comm_no)
);    

--FK제약 없는테이블
CREATE TABLE question (
	q_no	number		not null ,
    q_questioner varchar2(25) not null,
	q_content	varchar2(2000)	not null	,
	q_writer 	varchar2(15)		not null,
	q_date	Timestamp DEFAULT systimestamp,
    q_is_answered char(1) DEFAULT 'N'	not null, 
    
    constraint ck_question_q_is_answered check(q_is_answered in ('Y', 'N'))
);

ALTER TABLE question ADD CONSTRAINT PK_QUESTION_Q_NO PRIMARY KEY (
	q_no,
    q_questioner
);

--FK제약 없는테이블
CREATE TABLE faq (
	faq_no	number		not null ,
	faq_title	varchar2(50)		not null ,
	faq_content	varchar2(2000)		not null 
);
ALTER TABLE faq ADD CONSTRAINT PK_FAQ_FAQ_NO PRIMARY KEY (
	faq_no
);

create table coupon(
    coupon_no varchar2(200),
    coupon_type char(1) not null,
    coupon_reg_date date default sysdate,
    coupon_expired number,
    coupon_used char(1) default 'N',
    coupon_p_amount number,
    coupon_discount number,
    member_no varchar2(200),
    constraint pk_coupon_no primary key(coupon_no),
    constraint ck_coupon_type check(coupon_type in ('D','P')),
    constraint ck_coupon_used check(coupon_used in('Y','N')),
    constraint fk_coupon_member_no foreign key(member_no) references member(member_no)
);

create table used_coupon(
    coupon_no varchar2(200),
    coupon_type char(1),
    coupon_reg_date date,
    coupon_expired number,
    coupon_used char(1),
    coupon_p_amount number,
    coupon_discount number,
    member_no varchar2(200),
    coupon_used_date date
);

CREATE OR REPLACE TRIGGER trg_used_coupon
    after
    delete on coupon
    for each row

BEGIN
        insert into
        used_coupon
        values
        (:old.coupon_no, :old.coupon_type, :old.coupon_reg_date, :old.coupon_expired, 'Y', :old.coupon_p_amount, :old.coupon_discount, :old.member_no, sysdate);

end;
/

CREATE TABLE member_orders(
    order_no varchar2(500),
    member_no varchar2(200) not null,
    member_order_date Date default sysdate,
    constraint pk_member_order_order_no primary key(order_no),
    constraint fk_member_order_member_no foreign key(member_no) references member(member_no)    
);

CREATE TABLE orders(
    order_no varchar2(500),
    font_no varchar2(500),
    constraint pk_orders_order_no primary key(order_no, font_no)
   
);

--orders fk 
 alter table orders add constraint fk_order_no foreign key(order_no) references member_orders(order_no);
 alter table orders add  constraint fk_font_no foreign key(font_no) references font(font_no);


--attachment  fk

 alter table attachment  add constraint fk_attachment_member_no foreign key(member_no) references member(member_no);
  alter table attachment   add  constraint fk_attachment_comm_no foreign key(comm_no) references community(comm_no);
-- view 생성
-- 회원 - 포인트 뷰
create or replace view view_member_point
as
select
    p.member_no no,
    s.*
from
    member_point p
               join point s
                   on p.point_no = s.point_no;
                            
select * from view_memeber_point where member_no = ?;


-- member_like_font view 회원-폰트 좋아요 뷰
create or replace view view_member_like_font
as
select
    lf.member_no,
    lf.like_font_reg_date,
    f.*
from
           like_font lf
                   join font f
                       on lf.font_no = f.font_no;

-- member_like_community  view 회원-후기 좋아요 뷰
create or replace view view_member_like_community
as
select
    lc.member_no,
    lc.like_comm_reg_date,
    c.*
from
    like_community lc
        join community c
            on lc.comm_no = c.comm_no;

-- view_member_cart view 회원-장바구니 뷰
create or replace view view_member_cart
as
select
    mc.member_no,
    c.cart_no,
    c.cart_reg_date,
    f.font_no,
    f.font_name,
    f.font_price,
    f.font_discount_rate
from
    member_cart mc
        join cart c
            on mc.cart_no = c.cart_no
                join font f
                    on c.font_no = f.font_no;

-- view_member_order view 회원-주문 뷰
create or replace view view_member_orders
as
select
    mo.member_no,
    mo.member_order_date,
    o.order_no,
        f.font_no,
    f.font_name,
    f.font_price,
    f.font_discount_rate
from
    member_orders mo
        join orders o
            on mo.order_no = o.order_no
                join font f
                    on o.font_no = f.font_no;
                    
select * from question where q_questioner = 'hyejin' order by q_date asc;;
    

select * from member;
select * from view_member_orders where member_no =?




-- member 테이블 테스트용
insert into member values ('1','test1','1234','테스트1','M','test1@naver.com','01012341234',null,null,default,default,default,null,null);
insert into member values ('2','test2','1234','테스트2','M','test2@naver.com','01022341234',null,null,default,default,default,null,null);
insert into member values ('3','test3','1234','테스트3','M','test3@naver.com','01032341234',null,null,default,default,default,null,null);
insert into member values ('4','test4','1234','테스트4','M','test4@naver.com','01042341234',null,null,default,default,default,null,null);
insert into member values ('5','test5','1234','테스트5','M','test5@naver.com','01052341234',null,null,default,default,default,null,null);

insert into member values ('11','seller','1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==','판매자테스트','M','seller@naver.com','01012341234',null,null,default,default,default,'S',null);
insert into member values ('111','admin','1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==','관리자테스트','M','admin@naver.com','01012341234',null,null,default,default,default,'A',null);
--insert into member values ('6', 'test6', '1234', '테스트6', 'F', 'test6@gmail.com', '01012341234', null, null, default,default,default,null,default,default);
--insert into member values ('7','test7','1234','테스트7','M','test7@naver.com','01012341234',null, null, default,default,default,null,default,default);
--insert into member values ('8','test8','1234','테스트8','F','test8@naver.com','01012341234',null, null, default,default,default,null,default,default);
--insert into member values ('9','test9','1234','테스트9','F','test9@naver.com','01012341234',null, null, default,default,default,null,default,default);
--insert into member values ('10','test10','1234','테스트10','M','test10@naver.com','01012341234',null, null, default,default,default,null,default,default);


-- 회원-포인트 뷰 테스트용
--insert into point_category values('C', '고객이벤트');
--insert into point values('1', 'C', 300, default);
--insert into member_point values('1','1');

--select *from point_category;
--select *from point;
--select * from member_point;

--update  member set member_pwd = '1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==' where member_id= 'test111';
--update member set member_name = '고와서새미나' where member_id = 'test1';
select * from member;

-- 제약조건 조회
SELECT * FROM    ALL_CONSTRAINTS
WHERE    TABLE_NAME = 'MEMBER';


--update member set member_role = 'S' where member_no =3;
--alter table member
--drop constraint CK_MEMBER_MEMBER_ROLE;

--alter table member
--add constraint ck_member_member_role check (member_role in ('U', 'A', 'S'));

commit;
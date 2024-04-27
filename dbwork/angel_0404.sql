-- 자바연동 연습용 시퀀스및 테이블 생성
create sequence seq_test nocache;

create table person (
   pnum number(3) constraint pk_pnum primary key,
   pname varchar2(20),
   pblood varchar2(5) default 'B',
   page number(3),
   ipsaday date
);

-- 5개 정도 데이타 추가
insert into person values (seq_test.nextval,'이상순','a',34,'2010-12-20');
insert into person values (seq_test.nextval,'강호동','o',21,'2008-03-20');
insert into person values (seq_test.nextval,'유재석','A',29,sysdate);
insert into person values (seq_test.nextval,'손미나','a',39,'2015-09-13');
insert into person values (seq_test.nextval,'손지영','o',28,sysdate);
commit;

select * from person;

select pnum,pname,upper(pblood) pblood,page,to_char(ipsaday,'yyyy-mm-dd') ipsaday from person;







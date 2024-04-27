--복습문제
-- ename 이 'A' 나 'S' 로 시작하는 사람의 ENAME,SAL,COMM 조회
select ename,sal,comm from emp where ename like 'A%' or ename like 'S%';
--COMM 이 NULL 이 아닌사람마나 조회
select * from emp where comm is not null;
--MGR 이 7698,7566,7782 인 사람 조회 (IN 사용)
select * from emp where mgr in (7698,7566,7782);
--SAL 이 2500부터 3000 까지 조회(AND연산)
select * from emp where sal>=2500 and sal<=3000;
--SAL 이 2500부터 3000 까지 조회(BETWEEN 사용)
select * from emp where sal BETWEEN 2500 and 3000;
--JOB을 중복처리해서 한번씩만 출력하시오
select DISTINCT job from emp;
--서브쿼리 문제
--ALLEN 의 직업과 같은 직업을 가진 사람들을 조회하시오
SELECT * FROM EMP WHERE JOB=(select job from emp where ename='ALLEN');
--SCOTT 의 MGR 과 같은값을 가진 사람의 정보를 조회하시오
SELECT * FROM EMP WHERE MGR=(SELECT MGR FROM EMP WHERE ENAME='SCOTT');

--숫자함수,문자함수 연습
SELECT ABS(-5),ABS(5) FROM DUAL; --ABS :절대값
SELECT CEIL(3.0),CEIL(3.1),CEIL(3.9) FROM DUAL; --CEIL:무조건 올림
SELECT FLOOR(3.0),FLOOR(3.1),FLOOR(3.9) FROM DUAL;--FLOOR:무조건내림
SELECT MOD(7,3),MOD(9,5) FROM DUAL;--MOD:나머지
SELECT POWER(2,3),POWER(3,4) FROM DUAL;--POWER:지수승
SELECT ROUND(23.456,2),ROUND(8927,-1),ROUND(4567890,-4) FROM DUAL;--ROUND:반올림

--문자함수
SELECT CONCAT('HAPPY','DAY') FROM DUAL;
SELECT 'HAPPY'||'DAY' FROM DUAL; --위와 결과가 같다
SELECT INITCAP('HAPPY'),INITCAP('hello') from dual;--첫글자만 대문자로 
select lower('heLLOdAy'),upper('heLLOdAy') from dual;
select lower(ename),initcap(ename) from emp;
select lpad(sal,10,'*') from emp;--왼쪽으로 * 채우기
select rpad(sal,10,'*') from emp;--오른쪽으로 * 채우기
select substr('happy day',2,3) from dual;--2번인덱스부터 3글자(첫글자 인덱스는 1) : app
select substr('happy day',-5,3) from dual;--뒤에서 5번째부터 3글자(첫글자 인덱스는 1) : y d
select ename,length(ename) 글자수 from emp;--length:길이,글자수
select replace('Good Day','o','*') from dual;--o 을 *로 변경해서 출력
select instr('happy','a') from dual;--happy 에 a의 위치 :2
select instr('happy','x') from dual;--x가 없을경우 0
select instr('have a nice day','nice') from dual;--8번째에 nice가 있음
select trim('   hello   ') from dual;--앞뒤 공백 제거후 출력

--기타함수
--NVL 은 널일경우 대체값으로 출력
select NVL(comm,1000) from emp; --null 일경우 1000 으로 출력
--decode : 값에 따라 다른값을 출력할때
select ename 사원명,deptno 부서코드,
 decode(deptno,10,'홍보부',20,'교육부',30,'인사부') 부서명 from emp;
 
 --join : ppt 43페이지
 --inner join(내부조인),equi join
 
 --방법1
 select
    EMP.EMPNO,EMP.ENAME,EMP.JOB,DEPT.DNAME,DEPT.LOC
 from emp,dept
 where emp.deptno=dept.deptno;
 
 --방법2 , 두테이블에 공통적으로 있는 컬럼일경우에만 앞에 테이블명을 붙이고 나머지는 생략가능
 select
    EMPNO,ENAME,JOB,DNAME,LOC
 from emp,dept
 where EMP.deptno=dept.deptno;
 
 --방법 3 : 테이블명이 너무 길경우 별칭을 사용할수 있다
 select
    e.EMPNO,ENAME,JOB,DNAME,LOC
 from emp e,dept d
 where e.deptno=d.deptno;










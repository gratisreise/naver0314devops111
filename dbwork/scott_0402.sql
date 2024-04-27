-- 카페에서 scott_table 을 다운받아 naver0314 에 넣은후
-- cmd 창에서 naver0314 폴더로 이동후(cd naver0314) 
-- sqlplus 로 scott 계정으로 접속후
-- @scott_table 엔터
-- 자동으로 안에 있는 sql 명령어가 실행됩니다
select * from tab; --생성된 테이블 확인
select * from emp; -- emp 테이블의 연습용 데이타 확인
commit; -- 추가된 데이타 커밋 , 커밋 안할시 추가된 데이타가 취소됨

-- emp 테이블의 구조 확인
desc emp;

--전체 컬럼으로 emp 테이블 조회
select * from emp;
--ename,job 컬럼만 조회
select ename,job from emp;

--ename,job 컬럼만 조회, ename 의 오름차순(asc) 또는 내림차순으로 조회(desc)
select ename,job from emp order by ename asc;--asc 인경우 생략 가능,오름차순 출력
select ename,job from emp order by ename desc; --ename 의 내림차순으로 정렬

-- job을 조회
select job from emp;

--job 을 조회하는데 같은 직업은 한번만 나오게, 오름차순으로 출력
select DISTINCT job from emp order by job asc;
select DISTINCT job from emp order by 1;--컬럼번호는 1번부터, 위와 같다

-- ename 이 SMITH 인 사람 조회
SELECT * FROM EMP WHERE ENAME='SMITH';
SELECT * FROM EMP WHERE ENAME='ward';--sql명령어는 대소문자 상관없지만 데이타는 정확히 써야한다

--ENAME 이 SMITH 이거나 WARD 인사람 조회(OR)
SELECT * FROM EMP WHERE ENAME='SMITH' OR ename='WARD';

-- ENAME,SAL 을 조회
SELECT ENAME , SAL FROM EMP;

--컬럼제목을 변경
SELECT ENAME "사 원 명",SAL "급 여" FROM EMP; --제목변경시 공백이 포함되어있을경우 반드시 "" 로 묶어준다
SELECT ENAME 사원명,SAL 급여 FROM EMP;

--여러컬럼을 합해서 하나의 컬럼으로 출력할경우,컬럼명을 비교해보세요
SELECT '내이름은 '||ENAME||'이고 직업은 '||JOB||'입니다' FROM EMP;
SELECT '내이름은 '||ENAME||'이고 직업은 '||JOB||'입니다' "사원" FROM EMP;

--예)SMITH 님의 연봉은 800만원입니다 : 제목은 "연봉" 
SELECT ENAME||'님의 연봉은 '||SAL||'만원입니다' 연봉 FROM EMP;

--SAL 연봉이 2000~3000 사이 조회(컬럼은 ENAME,SAL)
SELECT ENAME,SAL FROM EMP WHERE SAL>=2000 AND SAL<=3000;
--BETWEEN 을 이용해서 조회할수도 있다
SELECT ENAME,SAL FROM EMP WHERE SAL BETWEEN 1000 AND 2000;

-- 직업이 SALESMAN ,MANAGER,CLERK 조회
SELECT ENAME,JOB FROM EMP WHERE JOB='SALESMAN' OR JOB='MANAGER' OR JOB='CLERK';
--IN 을 이용해서 여러직업으르 출력해보자
SELECT ENAME,JOB FROM EMP WHERE JOB IN ('SALESMAN','MANAGER','CLERK');

-- MGR 이 7902,7839,7788 조회(컬럼은 ENAME,JOB,MGR)-IN 사용해서
SELECT ENAME,JOB,MGR FROM EMP WHERE MGR IN (7902,7839,7788);

--MGR 이 NULL 인경우만 출력
SELECT * FROM EMP WHERE MGR IS NULL;

--COMM 이 NULL 인경우
SELECT * FROM EMP WHERE COMM IS NULL;

--COMM 이 NULL 이 아닌경우
SELECT * FROM EMP WHERE COMM IS NOT NULL;

--문자조회시 %, _ 를 이용해서 조회
-- ENAME 이 A로 시작하는 사람 조회
SELECT ENAME,SAL FROM EMP WHERE ENAME LIKE 'A%';

--ENAME 이 N 으로 끝나는 사람
SELECT ENAME,SAL FROM EMP WHERE ENAME LIKE '%N';

--이름의 두번째 글자가 A 인사람 조회
SELECT ENAME,SAL FROM EMP WHERE ENAME LIKE '_A%';
--이름의 세번째 글자가 A 인사람 조회
SELECT ENAME,SAL FROM EMP WHERE ENAME LIKE '__A%';

-- 이름이 S나M으로 시작하는 사람
SELECT ENAME,SAL FROM EMP WHERE ENAME LIKE 'S%' OR ENAME LIKE 'M%';
--이름이 S로 시작하는 사람중에 연봉이 2000 이상인 사람 조회
SELECT ENAME,SAL FROM EMP WHERE ENAME LIKE 'S%' AND SAL>=2000;
-- MGR 이 7902,7788,7566 이 아닌(NOT)사람을 조회(IN)
SELECT ENAME,JOB,MGR FROM EMP WHERE MGR NOT IN (7902,7566,7788);

--COMM, COMM+1000 의 결과 출력
SELECT COMM,COMM+1000 FROM EMP; -- NULL 인 경우는 더해도 결과는 NULL

-- 함수중 NVL(컬럼명,널일경우초기값),MYSQL 은 ifnull 이다
SELECT NVL(COMM,0),NVL(COMM,0)+1000 FROM EMP; --NULL 일경우 0으로 출력 또는 계산

--위의 SQL문을 컬럼제목을 추가하여 완성하시오 "COMM" ,"COMM2"
SELECT NVL(COMM,0) COMM,NVL(COMM,0)+1000 COMM2 FROM EMP; --NULL 일경우 0으로 출력 또는 계산

--SAL 이 2000~3000 이 아닌경우 조회, 두가지 방법으로 해보기
SELECT ENAME,SAL FROM EMP WHERE SAL<2000 OR SAL>3000;
SELECT ENAME,SAL FROM EMP WHERE  SAL NOT BETWEEN 2000 AND 3000;

-- GROUP 함수(COUNT,MAX,MIN,AVG,SUM)
SELECT COUNT(*) FROM EMP; --전체 인원수
SELECT MAX(SAL) FROM EMP; --최고연봉
SELECT MIN(SAL) FROM EMP; --최소연봉
SELECT SUM(SAL) FROM EMP; --SAL의 합계
SELECT AVG(SAL) FROM EMP; --SAL의 평균  2073.2142...

--AVG는 소숫점이하가 많이 나오는경우가 많아서 ROUND,CEIL,FLOOR 등과 같이 많이 사용
--평균은 소숫점이하 2자리까지만 나오게 하기
SELECT ROUND(AVG(SAL),2) FROM EMP; --2073.21,ROUND 는 반올림
SELECT CEIL(AVG(SAL)) FROM EMP; --무조건 올림 2074
SELECT FLOOR(AVG(SAL)) FROM EMP; --무조건내림 2073

-- PPT 57 페이지 GROUP BY 절
--JOB 을 그룹으로 직업별 각 인원수를 구해보자
SELECT JOB 직업,COUNT(*) 인원수 FROM EMP GROUP BY JOB;

--위의 결과에 직업의 오름차순으로 출력
SELECT JOB 직업,COUNT(*) 인원수 FROM EMP GROUP BY JOB ORDER BY 직업;--컬럼명으로 순서정하기
SELECT JOB 직업,COUNT(*) 인원수 FROM EMP GROUP BY JOB ORDER BY 1;--컬럼번호로 순서정하기

--위의 결과에 인원수의 오름차순으로 출력
SELECT JOB 직업,COUNT(*) 인원수 FROM EMP GROUP BY JOB ORDER BY 인원수;--컬럼명으로 순서정하기
SELECT JOB 직업,COUNT(*) 인원수 FROM EMP GROUP BY JOB ORDER BY 2;--컬럼번호로 순서정하기

--직업별 인원수와 최고연봉,최저연봉,평균연봉(무조건올림) 을 구해보시오
SELECT JOB,COUNT(*),MAX(SAL),MIN(SAL),CEIL(AVG(SAL)) FROM EMP GROUP BY JOB;
SELECT JOB 직업,COUNT(*) 인원수,MAX(SAL) 최고연봉,MIN(SAL) 최저연봉,CEIL(AVG(SAL)) 평균연봉 FROM EMP GROUP BY JOB;


--HAVING 은 GROUP 절에서 그룹함수를 이용해서 조건을 나타낼경우
--직업별 인원수와 최고연봉을 구하는데 직업별 인원수가 3명 이상인경우만 조회를 하시오
SELECT JOB,COUNT(*),MAX(SAL) FROM EMP GROUP BY JOB HAVING COUNT(JOB)>=3;

--오라클에서는 현재날짜를 구할때 SYSDATE,MYSQL 은 NOW()
SELECT SYSDATE FROM DUAL;--현재 콘솔에 현재 날짜 출력
--3일뒤
SELECT SYSDATE+3 FROM DUAL;
--30일뒤
SELECT SYSDATE+30 FROM DUAL;

--날짜타입을 문자열로 변환 TO_CHAR : 기호 Y,M,D,HH,HH24,MI,SS,AM/PM등
SELECT TO_CHAR(SYSDATE,'YYYY') FROM DUAL; --2024
SELECT TO_CHAR(SYSDATE,'YEAR') FROM DUAL;--영문자로 나옴,잘안씀

SELECT TO_CHAR(SYSDATE,'MM') FROM DUAL; --04
SELECT TO_CHAR(SYSDATE,'MONTH') FROM DUAL; --영어/한글로 월이 나옴

--기호를 많이 쓰는데 다양한 기호로 날짜를 출력해보자
SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD') FROM DUAL;
SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD AM HH:MI') FROM DUAL; --AM 또는 PM시간상관없이 사용
SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI') FROM DUAL;

--EMP 에서 HIREDATE(날짜타입)를이용해서 출력해보자
SELECT ENAME,TO_CHAR(HIREDATE,'YYYY-MM-DD') HIREDATE FROM EMP;

--고용년도가 1987 인 사람 출력
SELECT * FROM EMP WHERE TO_CHAR(HIREDATE,'YYYY')='1987';
SELECT * FROM EMP WHERE TO_CHAR(HIREDATE,'YYYY')=1987;

--고용월이 5월인 사람 출력
SELECT * FROM EMP WHERE TO_CHAR(HIREDATE,'MM')='05';
SELECT * FROM EMP WHERE TO_CHAR(HIREDATE,'MM')=5;

--SUBQUERY(SEELCT 문 안에 또 SELECT 문이 있는경우)
-- SCOTT 의 연봉과 같은 연봉을 가진 사람을 구하시오
SELECT * FROM EMP WHERE SAL=(SELECT SAL FROM EMP WHERE ENAME='SCOTT');
--평균연봉보다 많이 받는 사람의 정보를 출력하시오
SELECT * FROM EMP WHERE SAL>(SELECT AVG(SAL) FROM EMP);






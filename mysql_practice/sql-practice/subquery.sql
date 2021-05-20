-- subquery
-- 1) from절의 서브쿼리
SELECT NOW() AS n, SYSDATE() AS b, 3 + 1 AS c;

SELECT s.n, s.b, s.c
FROM (SELECT NOW() AS n, SYSDATE() AS b, 3 + 1 AS c) s;

-- 예제: 현재 Fai Bale이 근무하는 부서에서 근무하는 직원의 사번, 전체 이름을 출력해보세요.
SELECT b.dept_no
FROM employees a, dept_emp b
WHERE a.emp_no = b.emp_no
	AND b.to_date = '9999-01-01'
	AND CONCAT(a.first_name, ' ', a.last_name) = 'Fai Bale';
    
SELECT  a.emp_no, a.first_name
FROM employees a, dept_emp b
WHERE a.emp_no = b.emp_no
AND b.dept_no = 'd004';

-- 위에 두개의 쿼리를 서브쿼리로 만들면 밑의 쿼리가 됨 
SELECT a.emp_no, a.first_name
FROM employees a, dept_emp b
WHERE a.emp_no = b.emp_no
AND b.dept_no = (SELECT b.dept_no
        FROM employees a, dept_emp b
        WHERE a.emp_no = b.emp_no
		AND b.to_date = '9999-01-01'
		AND CONCAT(a.first_name, ' ', a.last_name) = 'Fai Bale');

-- 예제 2-1) where절의 서브쿼리 : 단일행
-- 단일행 연산자: =, >, <. <=, >=, <>, !=

-- 실습문제 1: 현재 전체사원의 평균 연봉보다 적은 급여를 받는 사원의  이름, 급여를 나타내세요.
select avg(salary) from salaries where to_date='9999-01-01';

select e.first_name, s.salary from employees e, salaries s
where s.emp_no = e.emp_no
and s.to_date = '9999-01-01'
and s.salary < (select avg(salary) from salaries where to_date ='9999-01-01')
order by s.salary desc;

-- 실습문제 2: 현재 가장적은 평균 급여를 받고 있는 직책의  평균 급여를 구하세요
-- 1) 직책별 평균 급여
SELECT  t.title, AVG(s.salary) AS avg_salary
FROM titles t, salaries s
WHERE t.emp_no = s.emp_no
	AND s.to_date = '9999-01-01'
	AND t.to_date = '9999-01-01'
GROUP BY t.title
ORDER BY avg_salary ASC;

-- sol1
-- 2) 가장적은 평균 급여
select min(a.avg_salary)
  from ( select a.title, avg(b.salary) as avg_salary
	       from titles a, salaries b
		  where a.emp_no = b.emp_no
            and a.to_date = '9999-01-01'
            and b.to_date = '9999-01-01'
	   group by a.title) a;

select a.title, avg(b.salary) as avg_salary
	from titles a, salaries b
   where a.emp_no = b.emp_no
     and a.to_date = '9999-01-01'
     and b.to_date = '9999-01-01'
group by a.title
having round(avg_salary) = (select min(a.avg_salary)
                                from (  select round(avg(b.salary)) as avg_salary
	                                      from titles a, salaries b
		                                 where a.emp_no = b.emp_no
                                           and a.to_date = '9999-01-01'
                                           and b.to_date = '9999-01-01'
	                                  group by a.title) a);  

-- sol2 : top-k
SELECT t.title, AVG(s.salary) AS avg_salary
FROM titles t, salaries s
WHERE t.emp_no = s.emp_no
	AND s.to_date = '9999-01-01'
	AND t.to_date = '9999-01-01'
GROUP BY t.title
ORDER BY avg_salary ASC
LIMIT 0 , 1;

-- 2-2) where 절의 서브쿼리 : 복수행
-- 복수행 연산자 : in, not in, any, all

-- any 사용법
-- 1. = any : in과 동일
-- 2. >any, >=any : 최소값
-- 3. <any, <=any : 최대값
-- 4. <>any : not in과 동일

-- all 사용법
-- 1. =all (x)
-- 2. >all, >=all : 최대값
-- 3. <all, <= all : 최소값

-- 예제 : 현재 급여가 50000 이상인 직원 이름 출력
-- sol1
select a.first_name, b.salary
from employees a, salaries b
where a.emp_no = b.emp_no
	and b.to_date ='9999-01-01'
    and (a.emp_no, b.salary) in (select emp_no, salary from salaries where salary >= 50000 and to_date = '9999-01-01')
order by b.salary asc;

-- sol2
select a.first_name, b.salary
from employees a, salaries b
where a.emp_no = b.emp_no
and b.to_date='9999-01-01'
and b.salary >= 50000
order by b.salary asc;

-- sol3
select a.first_name, b.salary
from employees a, salaries b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
and (a.emp_no, b.salary) = any(select emp_no, salary from salaries where to_date='9999-01-01' and salary >= 50000)
order by b.salary asc;

-- 실습문제4 : 각 부서별로 최고 월급을 받는 직원의 이름과 월급을 출력
select a.dept_no, max(b.salary) from dept_emp a, salaries b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by a.dept_no;

-- sol1 : where절 subquery = any
select a.dept_no, c.first_name, b.salary
from dept_emp a, salaries b, employees c
where a.emp_no = b.emp_no
and b.emp_no = c.emp_no
and a.to_date='9999-01-01'
and b.to_date ='9999-01-01'
and (a.dept_no, b.salary) =any ( select a.dept_no, max(b.salary)
from dept_emp a, salaries b
where a.emp_no = b.emp_no
and a.to_date='9999-01-01'
and b.to_date='9999-01-01'
group by a.dept_no);

-- sol2 : from절 subquery
select a.dept_no, c.first_name, b.salary
	from dept_emp a, salaries b, employees c,
    (select a.dept_no, max(b.salary) as max_salary
		from dept_emp a, salaries b
		where a.emp_no = b.emp_no
		and a.to_date = '9999-01-01'
		and b.to_date = '9999-01-01'
        group by a.dept_no) d
where a.emp_no = b.emp_no
and b.emp_no = c.emp_no
and a.dept_no = d.dept_no
and a.to_date='9999-01-01'
and b.to_date ='9999-01-01'
and b.salary = d.max_salary;
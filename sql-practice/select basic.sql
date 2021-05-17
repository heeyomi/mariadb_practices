-- select 연습
select * from departments;
select dept_no, dept_name from departments;

-- alias(as)
-- 예제 : employees 테이블에서 직원의 이름, 성별, 입사일을 출력
select first_name as name, gender as 성별, hire_date as 입사일 from employees;

-- concat
-- 예제 : employees 테이블에서 직원의 전체이름, 성별, 입사일을 출력
select concat(first_name, ' ',last_name) as '전체 이름', gender as 성별, hire_date as 입사일 from employees;

-- distinct
-- 예제 1: titles 테이블에서 모든 직급의 이름 출력
select title from titles;

-- 예제 2 titles 테이블에서 직급은 어떤 것들이 있는지 직급이름을 한번씩만 출력
select distinct(title) from titles;

-- where 절
-- 얘재 1: employees 테이블에서 1991년 이전에 입사한 직원의 이름, 성별, 입사일을 출력
select first_name, gender, hire_date from employees where '1991-01-01' > hire_date;

-- 예제 2: employees 테이블에서 1989년 이전에 입사한 여직원의 이름, 입사일을 출력
select first_name, hire_date from employees where '1989-01-01'>hire_date and gender = 'F';

-- where절 #3 : in 연산자
-- 예제 : dept_emp 테이블에서 부서 번호가 d005나 d009에 속한 사원의 사번, 부서 번호 출력
select emp_no, dept_no from dept_emp where dept_no in('d005', 'd009');

-- where절 #4 : Like
-- 예제 : employees 테이블에서 1989년에 입사한 직원의 이름, 입사일을 출력
select first_name, hire_date from employees where hire_date like '1989%';
select first_name, hire_date from employees where hire_date >= '1989-01-01' and hire_date <= '1989-12-31';
select first_name, hire_date from employees where hire_date between '1989-01-01' and '1989-12-31';

-- order by절 #1
-- 예제 : employees 테이블에서 직원의 전체이름,  성별, 입사일을  입사일 순으로 출력
select concat(first_name, ' ', last_name), gender, hire_date from employees order by hire_date asc;

-- 예제 : salaries 테이블에서 2001년 월급을 가장 높은순으로 사번, 월급순으로 출력
select * from salaries;
select emp_no, salary from salaries where from_date >='2001-01-01' order by salary desc;

-- 예제2 : 직원들의 사번,월급을 월급 순으로 사번, 월급만 출력
select emp_no, salary from salaries order by emp_no asc, salary desc;
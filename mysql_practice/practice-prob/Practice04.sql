-- SQL Practice04
-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*) from salaries where salary > (select avg(salary) from salaries);

-- 문제2. 
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 
select a.emp_no as '사번', concat(d.first_name, ' ' , d.last_name) as '이름', c.dept_name as '부서',max(a.salary) as '연봉'
	from salaries a, employees d, dept_emp b left join departments c on b.dept_no = c.dept_no
where a.emp_no = b.emp_no
	and b.emp_no = d.emp_no
	and a.to_date = '9999-01-01'
	and b.to_date = '9999-01-01'
group by b.dept_no
order by 연봉 desc;

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select e.emp_no as '사번', concat(e.first_name, ' ', e.last_name) as '이름', s.salary as '연봉'
	from employees e, salaries s, dept_emp d, (select a.dept_no, avg(b.salary) as avg_salary
		from dept_emp a, salaries b
	where a.emp_no = b.emp_no
		and a.to_date ='9999-01-01'
		and b.to_date = '9999-01-01'
	group by a.dept_no) k
where e.emp_no = s.emp_no
	and s.emp_no = d.emp_no
	and d.dept_no = k.dept_no
	and s.to_date = '9999-01-01'
	and d.to_date = '9999-01-01'
	and s.salary > k.avg_salary;

-- 부서별 평균 급여
select a.dept_no, avg(b.salary) from dept_emp a, salaries b
where a.emp_no = b.emp_no
and a.to_date ='9999-01-01'
and b.to_date = '9999-01-01'
group by a.dept_no;

-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.

-- 현재 매니저 
select * from dept_manager where to_date = '9999-01-01';


-- 매니저 이름 부서 매칭
select dept_name as '부서', d.manager_name from departments c right join (select  a.dept_no, concat(b.first_name, ' ', b.last_name) as 'manager_name' from dept_manager a right join employees b on a.emp_no = b.emp_no where to_date = '9999-01-01') d on c.dept_no = d.dept_no;
select * from departments c right join (select  a.dept_no, concat(b.first_name, ' ', b.last_name) as 'manager_name' from dept_manager a right join employees b on a.emp_no = b.emp_no where to_date = '9999-01-01') d on c.dept_no = d.dept_no;
select * from employees e left join (select * from departments c right join (select a.dept_no, concat(b.first_name, ' ', b.last_name) as 'manager_name' from dept_manager a right join employees b on a.emp_no = b.emp_no where to_date = '9999-01-01') d on c.dept_no = d.dept_no) k on e.emp_no = k.emp_no;

-- 매니저와 사원 매칭
select * from dept_emp a left join (select concat(b.first_name, ' ', b.last_name) as '매니저 이름', a.dept_no from dept_manager a right join employees b on a.emp_no = b.emp_no where to_date = '9999-01-01') c on a.dept_no = c.dept_no;


-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.

-- 문제6.
-- 평균 연봉이 가장 높은 부서는?


-- 문제7.
-- 평균 연봉이 가장 높은 직책?

select max(k.avg_salary) from titles a, (
select a.title , a.emp_no, avg(b.salary) as avg_salary
		from titles a, salaries b
	where a.emp_no = b.emp_no
		and a.to_date ='9999-01-01'
		and b.to_date = '9999-01-01'
	group by a.title order by avg(b.salary) desc) k where a.emp_no = k.emp_no;
    

-- 평균 연봉이 가장 높은 직책과 연봉
select a.title, max(k.avg_salary) as max_salary from titles a, (
select a.title , a.emp_no, avg(b.salary) as avg_salary
		from titles a, salaries b
	where a.emp_no = b.emp_no
		and a.to_date ='9999-01-01'
		and b.to_date = '9999-01-01'
	group by a.title order by avg(b.salary) desc) k where a.emp_no = k.emp_no;



select a.title from titles a, salaries b where a.emp_no = b.emp_no
and avg(b.salary) = any(
select max(k.avg_salary) as max_salary from titles a, (
select a.title , a.emp_no, avg(b.salary) as avg_salary
		from titles a, salaries b
	where a.emp_no = b.emp_no
		and a.to_date ='9999-01-01'
		and b.to_date = '9999-01-01'
	group by a.title order by avg(b.salary) desc) k where a.emp_no = k.emp_no);


select max(k.avg_salary) from salaries a, titles b ,(select a.titles, avg(b.salary) as avg_salary
		from titles a, salaries b
	where a.emp_no = b.emp_no
		and a.to_date ='9999-01-01'
		and b.to_date = '9999-01-01'
	group by a.titles) k where a.emp_no = b.emp_no;

select a.emp_no, max(avg_salary), b.title from salaries a, titles b where a.emp_no = b.emp_no and a.to_date ='9999-01-01' and b.to_date='9999-01-01' group by b.title;

select a.emp_no, avg(a.salary), b.title from salaries a, titles b where a.emp_no = b.emp_no and a.to_date='9999-01-01' and b.to_date='9999-01-01' group by b.title;

-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
select * from dept_manager;
select * from employees;

-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다

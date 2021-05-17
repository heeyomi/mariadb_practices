-- 함수 : 날짜 함수

-- curdate(), current_date
select curdate(), current_date();

-- curtime(), current_time
select curtime(), current_time();

-- now() vs sysdate()
select now(), sysdate();
select now(), sleep(2), now();
select sysdate(), sleep(2), sysdate();

-- date_format(date, format)
select date_format(now(), '%y년 %m월 %d일 %h시 %i분 %s초');
select date_format(now(), '%y년 %c월 %d일 %h시 %i분 %s초');

-- period_diff
-- YYMM, YYYYMM
-- 예) 근무 개월 수를 출력
select concat(first_name, ' ' , last_name) as name, period_diff(date_format(curdate(), '%y%m'), date_format(hire_date,'%y%m')) from employees;

-- date_add(=adddate), date_sub(subdate),
-- 날짜 date에 type(day, month, year) 형식으로 expr 값을 더하거나 뺀다.
-- 예) 각 사원들의 근무 년수가 5년이 되는 날은 언제인가?

select first_name, hire_date, date_add(hire_date, interval 5 year) from employees;

-- cast
select cast('2021-05-07' as date);
select cast('12345' as int);
select now(), cast(now() as date);
select cast(1-2 as unsigned);
select cast(cast(1-2 as unsigned) as signed);

-- mysql type
-- VARCHAR, CHAR, text, CLOB
-- signed(unsigned), int(integer), medium int, big int, int(11)
-- fload, double
-- time, date, datetime
-- LOB(Large OBject) : CLOB, BLOB


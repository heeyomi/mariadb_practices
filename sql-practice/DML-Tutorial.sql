-- Delete table
drop table pet;

-- CREATE table
create table pet (
	name varchar(20),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
);

-- schema 확인
desc pet;


-- 조회
select name, owner, species, gender from pet;

-- 데이터 넣기(생성)
insert into pet values ('댕댕이','윤희경','dog','m','2021-01-05',null);

-- 데이터 삭제
delete from pet where name='댕댕이';

-- 조회 연습1
-- 1990년 이후에 태어난 아이들은?
select * from pet where birth > '1990-12-31';

-- 주인이 GWen인 아이들은?
select * from pet where owner = 'Gwen';

-- 조회 연습2 : order by

-- 어린순으로 정렬
select * from pet order by birth desc, name asc;

-- null 다루기 1 : 살아 있는 애들은?
select * from pet where death is null;

-- null 다루기 2 : 죽은 애들은?
select * from pet where death is not null;

-- like 검색(패턴 매칭)

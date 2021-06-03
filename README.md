# mariadb practices
📖 Study mariadb and JDBC

## 목차
* [bookmall](#bookmall)
* [jdbc-practices](#jdbc-practices)
* [mysql_practice](#mysql_practice) 

## 개발환경
- Linux CentOS 7
- mariaDB 10.1.48
- Java JDK 1.8

## 1. bookmall
- ER Diagram
![image](https://user-images.githubusercontent.com/46592018/120678773-6bc2bc80-c4d3-11eb-8c11-508d339fdcc1.png)
- 디렉토리 구조
```
 com.douzone.bookmall.dao
    | -- BookDao
    | -- CartDao
    | -- CategoryDao
    | -- MemberDao
    | -- OrderDao
 com.douzone.bookmall.main
    | -- BookMall
 com.douzone.bookmall.test
    | -- BookDaoTest
    | -- CartDaoTest
    | -- CategoryDaoTest
    | -- MemberDaoTest
    | -- OrderDaoTest
 com.douzone.bookmall.vo
    | -- BookVo
    | -- CartVo
    | -- CategoryVo
    | -- MemberVo
    | -- OrderVo
```

#### bookmall 기능
1. Create
- 서적과 카트, 카테고리, 회원, 주문 및 주문 서적을 추가하는 기능

2. Read
- 서적과 카트, 카테고리, 회원, 주문 및 주문 서적 전체를 출력하는 기능
- 서적 번호와 회원번호, 주문 번호를 입력하면 해당하는 정보를 출력하는 기능

3. Delete
- 카트와 회원을 삭제하는 기능

#### 실행 화면
![image](https://user-images.githubusercontent.com/46592018/120681218-1dfb8380-c4d6-11eb-9072-fec0b0eda49a.png)
![image](https://user-images.githubusercontent.com/46592018/120681280-2bb10900-c4d6-11eb-9a30-cca33e070e45.png)
![image](https://user-images.githubusercontent.com/46592018/120681365-41bec980-c4d6-11eb-8bdf-a0d676aa5f4a.png)


## 2. jdbc-practices
1. bookshop
- DAO 패턴 연습 - bookshop
2. driver
- JDBC Driver 직접 구현
3. hr
- DAO 패턴 연습 - employees
4. test
- SQL CRUD Query 및 PreparedStatment 연습

## 3. mysql_practice
- DML과 Math, String, date, group, join, select, subquery 연습 및 Practice 과제

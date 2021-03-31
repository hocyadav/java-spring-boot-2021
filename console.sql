select *
from movies;
-- create table  + insert data + drop delete table
create table emp
(
    id     int primary key auto_increment,
    depid  int   default 1,
    salary float default 0,
    name   varchar(5) not null
);

insert into emp(depid, salary, name)
values (10, 100.12, 'hari'),
       (10, 200.12, 'hari'),
       (20, 300, 'hari'),
       (20, 300, 'hari'),
       (20, 400, 'hari'),
       (30, 500, 'hari'),
       (30, 500, 'hari');

drop table emp;

-- row number function
select row_number() over (order by depid) as rn
from emp;
-- same output as above
select row_number() over (order by name) as rn
from emp;

select row_number() over (order by id) as rn, id, lock_status, seat_id, show_id
from seat_lock;

-- max min function : find 2nd highest
-- approach : 1st find 1st max -- A, and then find 1st max but not in A
select max(salary)
from emp; -- 5000

select max(salary) -- 4000
from emp
where salary not in
      (select max(salary) from emp);
-- 5000

-- order by class
select *
from emp; -- its based on PK order if present

select *
from emp
order by salary; -- increasing order

select *
from emp
order by salary desc;
-- decreasing order

-- display odd / even row, required order by + mod function
-- select * from (result set table) where conditions
select *
from (select depid, salary, name, row_number() over (order by depid) as rn
      from emp
      order by rn) as t1
where mod(rn, 2) != 0;
-- = 0 for even row

-- find duplicated values and its frequency of a column
select count(*), depid
from emp
group by depid;

select count(*), name
from emp
group by name; -- add contion on groub by : dont use where, use having

select count(*), name
from emp
group by name
having name = 'om';
-- condition on group by

-- sort group by using orde by
select count(*), name -- 3
from emp -- 1
group by name -- 2
order by name; -- 4 here we have 2 column only not all emp column IMP point

select count(*), name
from emp
group by name
order by count(*);
-- same as above
select count(*) as count, name
from emp
group by name
order by count;
-- same as above but decreasing order
select count(*) as count, name
from emp
group by name
order by count desc;

-- group by + condition + sort
select count(*), name -- 3
from emp -- 1
group by name -- 2
having name = 'hari' -- 4 only we can add 2 column one is group by col name and 2nd one is function
order by name;
-- 4 same as above we can add 2 type filter

-- pattern matching 4 que using LIKE :
-- start with H, ends with V, contains H, does not contain H
select *
from emp
where name like 'H%';

select *
from emp
where name like '%V'; -- final result contains small or capital letter

select *
from emp
where name like '%A%'; -- contains A

select *
from emp
where name not like '%A%';
-- not contains A

-- length que LIKE, 4 length , L at 2nd position , join in DEC month (solve using date fun or pattern matching),
-- exactly 2 L

select *
from emp
where name like '____'; -- 4 length

select *
from emp
where name like '__D%';
-- 3rd char is D

-- display nth row : show 4th row with few column, show 4th row with all column,
# select * from (result set with row num)

-- show all records with row number
select *
from (select row_number() over (order by depid) as rn, emp.* from emp) as t1;

select *
from (select row_number() over (order by depid) as rn, name, depid, salary from emp) as t1
where rn <= 4;

select *
from (select row_number() over (order by depid) as rn, name, depid, salary from emp) as t1
where rn <= 3;

-- m1 : now subtract above 2
select *
from (select row_number() over (order by depid) as rn, name, depid from emp) as t1
where rn
          not in -- we can use minus in oracle, since mysql does not support minus
      (select rn
       from (select row_number() over (order by depid) as rn from emp) as t2
       where rn <= 3);

-- m2 : using equal and in operator : show few column
select name
from (select row_number() over (order by depid) as rn, name from emp) as t1
where rn = 4;

-- m2 : same as above but show all column
select *
from (select row_number() over (order by depid) as rn, emp.* from emp) as t1
where rn = 4;

-- m3 : show 4th , 5th 7th row
select *
from (select row_number() over (order by depid) as rn, emp.* from emp) as t1
where rn in (4, 5, 7);

-- create new emp2 table
create table emp2
(
    id     int primary key auto_increment,
    name   varchar(5) default '',
    salary float      default 0
);
drop table emp2;

insert into emp2(name, salary)
values ('hariy', 123.12),
       ('neha', 6000),
       ('cndn', 5000),
       ('omp', 3000);

-- union(remove duplicate) and union all(keep all elements)
select name
from emp
union
-- remove duplicate row (duplicate row when all columns values are same for 2 row)
select name
from emp2;

select name
from emp
union all
-- keep all row
select name
from emp2;

select name, salary
from emp
union
select name, salary
from emp2;

select name, salary
from emp
union all
select name, salary
from emp2;

select id, name, salary
from emp
union
select name, id, salary -- wrong add same order as above, and each column should have similar type
from emp2;

-- join concept -- create dept table
create table dept
(
    id            int primary key,
    dept_location varchar(40) default '',
    country       varchar(40) default ''
);


insert into dept (id, dept_location, country)
values (10, 'bangalore', 'india'),
       (20, 'delhi', 'india'),
       (30, 'singapore', 'out of india'),
       (50, 'ranchi', 'india');

select *
from dept; -- 3 column
select *
from emp; -- 4 column

select * -- 3 + 4 total 7 column
from emp,
     dept
where emp.depid = dept.id;

select * -- 1row of emp(coz of where cond) * all row of dep = 1 * 4 = 4
from emp,
     dept
where emp.id = 1; -- 1st emp row will combine with all rows of dept


select * -- all emp row(no where cond) * dep row = 9 * 4 = 36
from emp,
     dept;

select * -- all emp row * 1 dep row (where clause) = 9 * 1 = 9
from emp,
     dept
where dept.id = 10;

# emp that are in singapore - Approach : just add column that are present in another table
# just multiply table now we have single table with all t1 and t2 column and add extra condition
select *
from emp,
     dept
where emp.depid = dept.id
  and dept.dept_location = 'singapore';

-- display dept name/location and total salary from each dept,
-- salary present in emp table and dept name/location present in another table

select sum(emp.salary), dept.id, dept.dept_location
from emp,
     dept
where emp.depid = dept.id
group by dept.dept_location;

-- SEFL JOIN (same table 2 times when we compare same entity column):  create a emp dept combine one table
create table emp_dept
(
    id         int primary key,
    ename      varchar(10) default '',
    manager_id int,
    salary     float       default 0
);

insert into emp_dept(id, ename, manager_id, salary)
values (1, 'hariom', null, 4000),
       (2, 'chandan', 1, 3000), -- manager id 1 hariom
       (3, 'omp', 1, 5000),     -- manager id 1 hariom
       (4, 'neha', 2, 5000);
-- manager id 2 chandan

-- find emp with salary greater than manager
-- both are same entity, so self join required
select *
from emp_dept t1,
     emp_dept t2
where t1.manager_id = t2.id; -- we added manager details

select * -- all column of 2 table : 4 + 4 = 8
from emp_dept t1,
     emp_dept t2
where t1.manager_id = t2.id
  and t1.salary > t2.salary; -- manager details + t1 salary > t2 salary

select t1.ename as emp_name, t2.ename as manager_name -- select limited column
from emp_dept t1,
     emp_dept t2 -- join , here same table 2 times so self join
where t1.manager_id = t2.id;


-- practice union + union all + join + inner join + self join
select t1.name
from emp as t1
union
select t2.name
from emp2 as t2;

select t1.name
from emp t1
union all
select t2.name
from emp2 t2;
-- 1st union all between 2 result set and then group by
select min(t3.salary), t3.name
from (select t1.name, t1.salary from emp t1 union all select t2.name, t2.salary from emp2 t2) t3
group by t3.name;

select *
from emp,
     dept;

select *
from emp,
     dept
where emp.depid = dept.id;

select *
from emp,
     dept
where emp.depid = dept.id
  and dept.dept_location = 'delhi';

select count(*), dept.id -- each dept what is the num of emp
from emp,
     dept
where emp.depid = dept.id
group by dept.id;

select *
from emp_dept t1,
     emp_dept t2
where t1.manager_id = t2.id; -- self join , add managers details to table t1

select *
from emp_dept t1,
     emp_dept t2
where t1.manager_id = t2.id
  and t1.salary > t2.salary;
-- emp that have higher salary than there manager

-- left join : contains left all data , final result length is same number as left table
select *
from emp; -- left table 9 rows

select *
from emp
         left join dept on emp.depid = dept.id; -- final 9 rows (adding where condition in from)

select *
from emp,
     dept
where emp.depid = dept.id; -- same output as above

select *
from emp
         left join emp_dept on emp.salary = emp_dept.salary; -- salary matching in t2 table : contain matches + non matches data

select *
from emp,
     emp_dept
where emp.salary = emp_dept.salary;
-- same as above but clean result - remove null matches

-- right join
select *
from dept;

select *
from emp
         right join dept on emp.depid = dept.id; -- show all rows of right side table + matching condition from left side

select *
from emp
         right join dept on emp.depid = dept.id and dept.id = 10; -- only that matched condition that values are shown from left, but right side all values are shows

select *
from emp
         right join dept on emp.depid = dept.id and dept.dept_location = 'singapore';

select *
from emp,
     dept
where emp.depid = dept.id
  and dept_location = 'singapore';

select count(*), t2.id
from emp t1
         right join dept t2 on t1.depid = t2.id
group by t2.id;

-- full join = left join union right join, but no full join in MYSQL, we can using union
select *
from emp
         left join dept on emp.depid = dept.id; -- left join
select *
from emp
         right join dept on emp.depid = dept.id; -- right join

select *
from emp
         left join dept on emp.depid = dept.id -- left join
union
select *
from emp
         right join dept on emp.depid = dept.id; -- right join

select *
from emp
         cross join emp_dept;

select *
from emp,
     emp_dept;

-- first n rows, last n rows
select *
from emp_dept;

select *
from (select row_number() over (order by id) rn, emp_dept.* from emp_dept) t1
where t1.rn <= 2;

select *
from (select row_number() over (order by id desc) rn, emp_dept.* from emp_dept) t1
where t1.rn <= 2
order by t1.id;
-- optional sort

-- display 1st and last row, i.e rn = 1 OR rn = total number
select *
from (select row_number() over (order by id) rn, emp_dept.* from emp_dept) t1
where rn = 1
   OR rn = (select count(*) from emp_dept);

-- display last 2 row if total is 7 then: all - 1st five
-- 1st 2 rows
select *
from (select row_number() over (order by id) rn, emp_dept.* from emp_dept) t1
where t1.rn in (1, 2);
-- row num condition 1st 2 row

-- find last 2 row , same as above but only change in condition
select *
from (select row_number() over (order by id) rn, emp_dept.* from emp_dept) t1 -- create row num
where rn > (select count(*) - 2 from emp_dept);
-- condition on row num using count : rn > total - 2

-- 1st 2 row + last 2 row, take or of above 2 row num condition
select *
from (select row_number() over (order by id) rn, emp_dept.* from emp_dept) t1
where rn in (1, 2)
   OR rn > (select count(*) - 2 from emp_dept);

-- even odd records, only change rn condition, use mod
select *
from (select row_number() over (order by id) rn, emp_dept.* from emp_dept) t1
where mod(rn, 2) = 0;
-- even , for odd use != 0

-- first n row using order by n limit concept
select *
from emp_dept
order by id; -- sort ,+ take all n rows

select *
from emp_dept
order by id -- 1 sort
limit 2; -- 2 take top 2 element, default it will take n element

select *
from emp_dept
order by id desc -- 1. sort
limit 2; -- 2. take top 2 element


select *
from emp_dept
order by id
limit 3; -- take 1st 3 row, but where to start, default it will start from 0th index, i.e. offset = 0

select *
from emp_dept
order by id
limit 0, 3;
-- same as above one, we can use "limit 3" also

-- IMP : order of execution : FROM -> WHERE -> SELECT -> ORDER & LIMIT
select *
from emp_dept
order by id -- 1. sort
limit 1, 3;
-- 2. after sorting start from 1st array index , index start from 0
-- in above we are excluding 0th index, i.e. remove first element after sorting
-- if limit 2, 3 : then remove 0th n 1st index from final result, and after that show 3 values

-- https://www.mysqltutorial.org/mysql-limit.aspx
-- LIMIT : top 5 customer who have highest credit,
-- sort based on credit using ORDER BY, then take 1st 5 using LIMIT
-- .... order by cusLimit desc limit 5

-- nth highest or lowest using LIMIT
-- order by ... LIMIT n-1, 1; //skip n-1 element i.e. nothing but all element except one and 1 means show one element
-- order by ... desc LIMIT n-1, 1; //nth highest element

select distinct salary
from emp_dept
order by salary;
-- output
# 3000
# 3002
# 4000
# 4005
# 4008
# 4009
# 5000
# 5002
# 5004
select distinct salary
from emp_dept
order by salary
# limit 0, 1; -- 3000 : 0th index value in sorted array i.e. nothing but 1st min salary
# limit 1, 1; -- 3002 : 1st index value in sorted array i.e. nothing but 2nd min salary
limit 2, 1;
-- 4000 : 2nd index value in sorted array i.e. nothing but 3rd min salary..and so on..
-- we can find nth lowest by knowing where to start ,i.e. in array start from n-1 index then pick only one element after that i.e 1, so final SQL is limit n-1, 1;


-- group by above SQL
-- 1st : find nth lowest salary using LIMIT n-1, 1, 2nd apply group by to know how many row counts for that salary
select salary, count(*)
from emp_dept -- 1
group by salary -- 2
order by salary -- 3
limit 2, 1; -- 3rd lowest = 3-1 array index + pick only one element so 1, same as above but with count how many row have salary

select *
from emp_dept;

update emp_dept
set salary = 5555 -- 2nd this will run
where id = 1;
-- 1st this will run

# update
# set  =
# where;

-- no where clause so this will update all rows : https://www.w3schools.com/sql/sql_update.asp
# update emp_dept
# set salary = 4001;

select *
from emp_dept;

create index index1 on emp_dept (ename);

create index index2 on emp_dept (ename(2)); -- index on 1st 2 char

create index index3 on emp_dept (salary);

create table student
(
    id   int primary key auto_increment,
    name varchar(20)
);
create table backlog
(
    student_id int not null,
    subject_id int not null
);
select s.name
from student s,
     backlog b
where s.id = b.student_id
group by b.student_id
having count(*) >= 1;

select *
from (select s.name
      from student s,
           backlog b
      where s.id = b.student_id
      group by b.student_id
      having count(*) >= 1) t
order by t.name asc;

select t.name, c.name
from (select p.name, s.course_id
      from professor p,schedule s
      where p.id = s.professor_id) t,
     course c
where t.course_id = c.id
  and c.department_id not in (select p2.department_id from professor p2);
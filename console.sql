select * from movies;
-- create table  + insert data + drop delete table
create table emp(
    id int primary key auto_increment,
    depid int default 1,
    salary float default 0,
    name varchar(5) not null
);

insert into emp(depid, salary, name)
values
       (10, 100.12, 'hari'),
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
select max(salary) from emp; -- 5000

select max(salary) -- 4000
from emp
where salary not in
      (select max(salary) from emp); -- 5000

-- order by class
select * from emp; -- its based on PK order if present

select * from emp
order by salary; -- increasing order

select * from emp
order by salary desc; -- decreasing order

-- display odd / even row, required order by + mod function
-- select * from (result set table) where conditions
select *
from (select depid, salary, name, row_number() over (order by depid) as rn
    from emp
    order by rn) as t1
where mod(rn, 2) != 0; -- = 0 for even row

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
having name = 'om'; -- condition on group by

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
order by count desc ;

-- group by + condition + sort
select count(*), name -- 3
from emp             -- 1
group by name        -- 2
having name = 'hari' -- 4 only we can add 2 column one is group by col name and 2nd one is function
order by name;        -- 4 same as above we can add 2 type filter

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
where name not like '%A%'; -- not contains A

-- length que LIKE, 4 length , L at 2nd position , join in DEC month (solve using date fun or pattern matching),
-- exactly 2 L

select *
from emp
where name like '____'; -- 4 length

select *
from emp
where name like '__D%'; -- 3rd char is D

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
        where rn <=3) ;

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
create table emp2(
    id int primary key auto_increment,
    name varchar(5) default '',
    salary float default 0
);
drop table emp2;

insert into emp2(name, salary)
values ('hariy', 123.12),
         ('neha', 6000),
         ('cndn', 5000),
         ('omp', 3000);

-- union(remove duplicate) and union all(keep all elements)
select name from emp
union -- remove duplicate row (duplicate row when all columns values are same for 2 row)
select name from emp2;

select name from emp
union all -- keep all row
select name from emp2;

select name, salary from emp
union
select name, salary from emp2;

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
create table dept(
    id int primary key,
    dept_location varchar(40) default '',
    country varchar(40) default ''
);


insert into dept (id, dept_location, country)
values (10, 'bangalore', 'india'),
     (20, 'delhi', 'india'),
     (30, 'singapore', 'out of india'),
     (50, 'ranchi', 'india');

select *from dept; -- 3 column
select *from emp; -- 4 column

select * -- 3 + 4 total 7 column
from emp, dept
where emp.depid = dept.id;

select * -- 1row of emp(coz of where cond) * all row of dep = 1 * 4 = 4
from emp, dept
where emp.id = 1; -- 1st emp row will combine with all rows of dept


select * -- all emp row(no where cond) * dep row = 9 * 4 = 36
from emp, dept;

select * -- all emp row * 1 dep row (where clause) = 9 * 1 = 9
from emp, dept
where dept.id = 10; 

# emp that are in singapore - Approach : just add column that are present in another table
# just multiply table now we have single table with all t1 and t2 column and add extra condition
select *
from emp, dept
where emp.depid = dept.id and dept.dept_location = 'singapore';

-- display dept name/location and total salary from each dept,
-- salary present in emp table and dept name/location present in another table

select sum(emp.salary), dept.id, dept.dept_location
from emp, dept
where emp.depid = dept.id 
group by dept.dept_location;

-- SEFL JOIN (same table 2 times when we compare same entity column):  create a emp dept combine one table
create table emp_dept(
    id int primary key,
    ename varchar(10) default '',
    manager_id int,
    salary float default 0
);

insert into emp_dept(id, ename, manager_id, salary)
values (1, 'hariom', null, 4000),
 (2, 'chandan', 1, 3000), -- manager id 1 hariom
 (3, 'omp', 1, 5000), -- manager id 1 hariom
 (4, 'neha', 2, 5000); -- manager id 2 chandan

-- find emp with salary greater than manager
-- both are same entity, so self join required
select *
from emp_dept t1, emp_dept t2
where t1.manager_id = t2.id; -- we added manager details

select * -- all column of 2 table : 4 + 4 = 8
from emp_dept t1, emp_dept t2
where t1.manager_id = t2.id and t1.salary > t2.salary; -- manager details + t1 salary > t2 salary

select t1.ename as emp_name, t2.ename as manager_name -- select limited column
from emp_dept t1, emp_dept t2 -- join , here same table 2 times so self join
where t1.manager_id = t2.id;

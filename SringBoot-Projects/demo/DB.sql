 CREATE database mydb_java;
 
 use mydb_java;

create table employee(
id int NOT NULL AUTO_INCREMENT,
first_name varchar(20),
last_name varchar(20),
PRIMARY KEY (ID)
);

select * from employee;

-- drop table department;

create table department(
id int NOT NULL AUTO_INCREMENT,
dept_name varchar(20),
PRIMARY KEY (ID)
);


select * from department;
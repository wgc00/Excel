-- 创建一个员工表
create table employee(
  emp_number int primary key not null,
  emp_name   varchar(20) not null,
  emp_gender char(2) not null,
  emp_education varchar(10) not null,
  emp_monthly_pay decimal(18,2) not null
);

select * from employee;

insert into employee value
(1002, "张三", '男',	"博士", 14444.32),
(1003, "李四", '男',	"本科", 4445.32),
(1004, "赵六", '男',	"大专",	3331.03),
(1005,	"喜喜",	'女',	"本科", 4447.32);

select * from employee;
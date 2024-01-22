create table departments (
    id serial primary key,
    "name" varchar(255)
);

create table employees (
    id serial primary key,
    "name" varchar(255),
    department_id int references departments (id)
);

insert into departments(name) 
values ('Otdel kadrov'), ('Otdel proizvodstva'), ('Texnicheskii otdel');

insert into employees(name, department_id) 
values ('Ivanova', 1), ('Petrov', 2), ('Sidorov', 3), ('Knizev', null);

select * from employees e left join departments d on e.department_id = d.id
where e.department_id is null;

select * from departments d right join employees e on e.department_id = d.id;

select * from departments d full join employees e on e.department_id = d.id;

select * from departments d cross join employees e;

select * from departments d left join employees e on e.department_id = d.id;

select * from employees e right join departments d on e.department_id = d.id;

create table teens(
    id serial primary key,
    "name" varchar(50),
    gender varchar(10)
);

insert into teens (name, gender) values
    ('Olja', 'W'),
    ('Petya', 'M'),
    ('Sasha', 'M'),
    ('Lena', 'W');

select t1.name, t1.gender, t2.name, t2.gender
from teens t1
cross join teens t2
where t1.name <> t2.name
    and t1.gender <> t2.gender
    and t1.name < t2.name;
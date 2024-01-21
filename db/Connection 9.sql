create table type(
    id serial primary key,
    "name" varchar(255)
);

create table product(
    id serial primary key,
    "name" varchar(255),
    expired_date date,
    price numeric,
    type_id int references type(id)
);

insert into type(name) values ('����'), ('������'), ('���������'), ('����');

insert into product(name, expired_date, price, type_id) 
values ('��� �����', '2024-02-25', 690, 1);
insert into product(name, expired_date, price, type_id) 
values ('��� ����������', '2024-01-31', 850, 1);
insert into product(name, expired_date, price, type_id) 
values ('��� ��������', '2024-03-01', 1500, 1);


insert into product(name, expired_date, price, type_id) 
values ('Parmalat', '2024-04-25', 150, 2);
insert into product(name, expired_date, price, type_id) 
values ('�������� ����', '2024-01-01', 50, 2);
insert into product(name, expired_date, price, type_id) 
values ('������', '2024-03-01', 89, 2);


insert into product(name, expired_date, price, type_id) 
values ('������������ ������� �������', '2024-03-20', 280, 3);
insert into product(name, expired_date, price, type_id) 
values ('����� �����', '2025-10-12', 1500, 3);
insert into product(name, expired_date, price, type_id) 
values ('��������� ���� �������', '2025-01-01', 250, 3);

insert into product(name, expired_date, price, type_id) 
values ('��� RICH', '2025-10-20', 200, 4);
insert into product(name, expired_date, price, type_id) 
values ('������', '2025-12-08', 150, 4);

select * from product where type_id = 1;

select * from product where name like '%���������%';

select * from product where expired_date < '2024-01-21';

select name, price 
from product where price = (select max(price) from product);

select t.name, count(t.name) 
from product p join type t on p.type_id = t.id
group by t.name;

select p.name
from product p join type t on p.type_id = t.id
where t.name = '����';

select p.name
from product p join type t on p.type_id = t.id
where t.name = '������';

select t.name, count(t.name) 
from product p join type t on p.type_id = t.id
group by t.name
having count(t.name) < 10; 

select p.name, p.expired_date, p.price, t.name
from product p join type t on p.type_id = t.id;
create table price(
    id serial primary key,
    suma int
);

create table products(
    id serial primary key,
    "name" varchar(100),
    amount int,
    prices_id int references price(id)
);

insert into price(suma) values (50), (100), (150), (200), (250);

insert into products(name, amount, prices_id) 
values ('cheese', 10, 4), ('milk', 5, 2), ('bread', 7, 1), ('eggs', 15, 3), ('tea', 5, 5);

select p.name
from products as p
         join price pr on pr.id = p.prices_id
where p.name <> 'bread' AND amount < 15
group by p.name
having AVG(amount) < 10;

create view prod_name_with_amont 
as 
select p.name
from products as p
         join price pr on pr.id = p.prices_id
where p.name <> 'bread' AND amount < 15
group by p.name
having AVG(amount) < 10;

select * from prod_name_with_amont;
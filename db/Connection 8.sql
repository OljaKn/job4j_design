create table devices(
    id serial primary key,
    "name" varchar(255),
    price numeric
);

create table people(
    id serial primary key,
    "name" varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price) values ('Компьютер', 123000), ('Телефон', 68000), ('Планшет', 35000), ('Часы', 15000);

insert into people(name) values ('Вася'), ('Миша'), ('Саша'), ('Петя');

insert into devices_people(device_id, people_id) values (1, 1), (4, 1);
insert into devices_people(device_id, people_id) values (2, 2), (4, 2), (3, 2);
insert into devices_people(device_id, people_id) values (1, 3), (3, 3);
insert into devices_people(device_id, people_id) values (1, 4), (2, 4), (4, 4);

select avg(price) from devices;

select p.name, avg(d.price)
from people as p join devices_people as dp on dp.people_id = p.id
         join devices d
              on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from people as p join devices_people as dp on dp.people_id = p.id
         join devices d
              on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;


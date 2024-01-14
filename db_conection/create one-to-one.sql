create table inn(
    id serial primary key,
    number int
);

create table people(
    id serial primary key,
    name varchar(255),
    inn_id int references inn(id) unique
);

create table inn_people(
    id serial primary key,
    inn_id int references inn(id) unique,
    people_id int references people(id) unique
);

insert into inn(number) values ('775588963');
insert into inn(number) values ('589674258');
insert into inn(number) values ('748596165');

insert into people(name) values ('Петров И');
insert into people(name) values ('Иванов');
insert into people(name) values ('Сидоров');

insert into inn_people(inn_id, people_id) values (1, 1);
insert into inn_people(inn_id, people_id) values (2, 3);
insert into inn_people(inn_id, people_id) values (3, 2);
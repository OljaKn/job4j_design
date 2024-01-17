create table roles(
    id serial primary key,
    role text
);

create table users(
    id serial primary key,
    "name" varchar(255),
	roles_id int references roles(id)
);

create table rules(
    id serial primary key,
    function varchar(255)
);

create table roles_rules(
    id serial primary key,
    roles_id int references roles(id),
    rules_id int references rules(id)
);

create table categories(
    id serial primary key,
    "name" varchar(255)
);

create table states(
    id serial primary key,
    "name" text
);

create table items(
    id serial primary key,
    "name" varchar(255),
    users_id int references users(id),
    categories_id int references categories(id),
    states_id int references states(id)
);

create table commentes(
    id serial primary key,
    "name" varchar(255),
    items_id int references items(id)
);

create table attachs(
    id serial primary key,
    "name" varchar(255),
    items_id int references items(id)
);

insert into roles(role) VALUES ('исполнитель');
insert into roles(role) VALUES ('оператор');

insert into users(name, roles_id) values ('Петров', 1);
insert into users(name, roles_id) values ('Иванов', 2);

insert into rules(function) values ('Все права');
insert into rules(function) values ('Изменение заявок');

insert into roles_rules(roles_id, rules_id) values (1, 1);
insert into roles_rules(roles_id, rules_id) values (1, 2);
insert into roles_rules(roles_id, rules_id) values (2, 2);

insert into categories(name) VALUES ('срочные');
insert into categories(name) VALUES ('отгрузки');

insert into states(name) VALUES ('в работе');
insert into states(name) VALUES ('закрыта');

insert into items(name, users_id, categories_id, states_id) values ('Отгрузить товар', 1, 1, 1);
insert into items(name, users_id, categories_id, states_id) values ('Добавить накладную', 2, 2, 2);

insert into commentes(name, items_id) values ('Взять печать', 1);
insert into commentes(name, items_id) values ('Оплатить', 1);

insert into attachs(name, items_id) values ('Накладные', 1);
insert into attachs(name, items_id) values ('Инвентаризация', 1);


select * from users;

select * from roles where id in (select roles_id from users);
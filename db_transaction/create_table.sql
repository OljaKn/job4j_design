create table accaunts(
    id serial primary key,
    name varchar(50),
    age integer default 0
);

insert into accaunts (name, age)
VALUES ('Petrov', 20);
insert into accaunts (name, age)
VALUES ('Petrova', 17);
insert into accaunts (name, age)
VALUES ('Ivanov', 45);
insert into accaunts (name, age)
VALUES ('Vasiliev', 15);
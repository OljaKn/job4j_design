create table fauna(
    id serial primary key,
    "name" text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('Bird', 18000, '1234-04-10');
insert into fauna(name, avg_age, discovery_date) values ('Bird', 450, null);
insert into fauna(name, avg_age, discovery_date) values ('Mammal', 35000, '2000-02-07');
insert into fauna(name, avg_age, discovery_date) values ('Mammal', 5000, '1652-11-25');
insert into fauna(name, avg_age, discovery_date) values ('Fish', 11000, '1735-04-30');
insert into fauna(name, avg_age, discovery_date) values ('Fish', 500, '1300-08-15');

select * from fauna where name like '%Fish%';

select * from fauna where avg_age between 10000 AND 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '1950-01-01';
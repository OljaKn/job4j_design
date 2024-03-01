CREATE TABLE company(
    id integer NOT NULL,
    "name" character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person(
    id integer NOT NULL,
    "name" character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values (1, 'Partner'), (2, 'OOO'), (3, 'IP Sidorov'), (4, 'ROSTEX'), (5, 'Mac'), (6, 'Juk');

insert into person(id, name, company_id)
values (1, 'Vasya Sidorov', 1);
insert into person(id, name, company_id)
values (2, 'Ivan Petrov', 1);
insert into person(id, name, company_id)
values (3, 'Petr Arsenitiev', 1);
insert into person(id, name, company_id)
values (4, 'Olga Knyazeva', 1);

insert into person(id, name, company_id)
values (5, 'Daria Ivanova', 2);
insert into person(id, name, company_id)
values (6, 'Alexei Kniazev', 2);
insert into person(id, name, company_id)
values (7, 'Ivan Dorofeev', 2);

insert into person(id, name, company_id)
values (8, 'Olga Orlova', 3);
insert into person(id, name, company_id)
values (9, 'Denis Pavlov', 3);
insert into person(id, name, company_id)
values (10, 'Katya Bogacheva', 3);
insert into person(id, name, company_id)
values (11, 'Alina Strogonova', 3);

insert into person(id, name, company_id)
values (12, 'Yulia Kirillova', 4);
insert into person(id, name, company_id)
values (13, 'Maria Petrova', 4);
insert into person(id, name, company_id)
values (14, 'Misha Kniazev', 4);
insert into person(id, name, company_id)
values (15, 'Alexandr Orlov', 4);
insert into person(id, name, company_id)
values (16, 'Vladimir Putin', 4);
insert into person(id, name, company_id)
values (17, 'Dmitrii Medvedev', 4);

insert into person(id, name, company_id)
values (18, 'Alexei Navalinii', 5);
insert into person(id, name, company_id)
values (19, 'Yulia Navalinai', 5);
insert into person(id, name, company_id)
values (20, 'Ksenia Sobchak', 5);

insert into person(id, name, company_id)
values (21, 'Vasya Medvedev', 6);
insert into person(id, name, company_id)
values (22, 'Olga Knyazeva', 6);
insert into person(id, name, company_id)
values (23, 'Irina Melinikova', 6);
insert into person(id, name, company_id)
values (24, 'Elena Dorofeeva', 6);
insert into person(id, name, company_id)
values (25, 'Petr Griboedov', 6);
insert into person(id, name, company_id)
values (26, 'Pavel Pushkin', 6);

select p.name, c.name as Name_Company
from person as p join company as c on c.id = p.company_id
where p.company_id <> 5;

select c.name, count(p.name) as Count
from company c join person p on p.company_id = c.id
group by c.name
having count(p.name) = (
select max(count_p) 
from (select c.name, count(p.name) as count_p
from company c join person p on p.company_id = c.id
group by c.name));


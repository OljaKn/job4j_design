create table author(
    id serial primary key,
    "name" varchar(255)
);

create table books(
    id serial primary key,
    work varchar(255),
    author_id int references author(id)
);

insert into author(name) values ('Пушкин А.С.');
insert into author(name) values ('Достоевский Ф.М.');
insert into author(name) values ('Толстой Л.Н.');
insert into books(work, author_id) VALUES ('Руслан и Людмила', 1);
insert into books(work, author_id) VALUES ('Евгений Онегин', 1);
insert into books(work, author_id) VALUES ('Сборник стихов', 1);
insert into books(work, author_id) VALUES ('Преступление и наказание', 2);
insert into books(work, author_id) VALUES ('Идиот', 2);
insert into books(work, author_id) VALUES ('Бесы', 2);
insert into books(work, author_id) VALUES ('Война и мир', 3);
insert into books(work, author_id) VALUES ('Анна Каренина', 3);

select b.work, a.name
from books as b join author as a on b.author_id = a.id;

select b.work as Произведения, a.name as "Имя автора"
from books b join author as a on b.author_id = a.id;

select b.work as "Самые лучшие работы автора", a.name as "Автор"
from books as b join author a on b.author_id = a.id;
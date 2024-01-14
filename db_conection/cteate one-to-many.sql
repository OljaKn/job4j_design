create table author(
    id serial primary key,
    name varchar(255)
);

create table books(
    id serial primary key,
    work varchar(255),
    author_id int references author(id)
);

insert into author(name) values ('Пушкин');
insert into books(work, author_id) VALUES ('Руслан и Людмила', 1);

select * from books;

select * from author where id in (select author_id from books);
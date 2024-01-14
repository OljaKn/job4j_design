create table books(
    id serial primary key,
    authorsWork varchar(255),
    position_id int references Author(id)
);

insert into Author(name) values ('Достоевский');
insert into books(authorsWork, Author_id) VALUES ('Бесы', 1);

select * from books;

select * from Author where id in (select Author_id from books);
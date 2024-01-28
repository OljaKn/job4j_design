CREATE TABLE customers(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);
insert into customers (first_name, last_name, age, country) values ('Olja', 'Knyazeva', 32, 'Russia');
insert into customers (first_name, last_name, age, country) values ('Oleg', 'Ivanov', 40, 'USA');
insert into customers (first_name, last_name, age, country) values ('Vasya', 'Petrov', 30, 'China');
insert into customers (first_name, last_name, age, country) values ('Petya', 'Vasiliev', 18, 'Russia');
insert into customers (first_name, last_name, age, country) values ('Fedya', 'Zyev', 29, 'Italia');

select * from customers 
where age = (select min(age) from customers);

CREATE TABLE orders(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders (amount, customer_id) values (2, 1);
insert into orders (amount, customer_id) values (3, 2);
insert into orders (amount, customer_id) values (1, 3);
select * from orders;

select * from customers 
where customers.id not in (select customer_id from orders);
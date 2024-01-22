create table car_bodies(
     id serial primary key,
     "name" varchar(255)
 );
 
create table car_engines(
     id serial primary key,
     "name" varchar(255)
 );
 
 create table car_transmissions(
     id serial primary key,
     "name" varchar(255)
 );
 
 create table cars(
     id serial primary key,
	 "name" varchar(255),
     body_id int references car_bodies(id),
     engine_id int references car_engines(id),
	 transmission_id int references car_transmissions(id)
 );
 
insert into car_bodies(name) values ('седан');
insert into car_bodies(name) values ('хэтчбэк');
insert into car_bodies(name) values ('минивэн');
insert into car_bodies(name) values ('пикап');

insert into car_engines(name) values ('V4');
insert into car_engines(name) values ('V6');
insert into car_engines(name) values ('V8');
insert into car_engines(name) values ('V10');
insert into car_engines(name) values ('V12');

insert into car_transmissions(name) values ('автомат');
insert into car_transmissions(name) values ('механика');
insert into car_transmissions(name) values ('вариатор');
insert into car_transmissions(name) values ('робот');

insert into cars(name, body_id, engine_id, transmission_id) values ('Toyota', 1, 1, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('Kia', 3, null, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('Nissan', 2, 2, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('Amoda', 2, 3, null);


select * from cars;

select b.name from car_bodies b left join cars c on c.body_id = b.id
where c.body_id is null;

select e.name from cars c right join car_engines e on c.engine_id = e.id
where c.engine_id is null;

select t.name from car_transmissions t left join cars c on c.transmission_id = t.id
where c.transmission_id is null;



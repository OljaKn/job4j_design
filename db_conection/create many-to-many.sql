 create table doctors(
     id serial primary key,
     name varchar(255)
 );
 
 create table patients(
     id serial primary key,
     name varchar(255)
 );
 
 create table doctors_patients(
     id serial primary key,
     doctor_id int references doctors(id),
     patient_id int references patients(id)
 );
 
insert into doctors(name) values ('Невролог');
insert into doctors(name) values ('Офтальмолог');
insert into doctors(name) values ('Терапевт');

insert into patients(name) values ('Сидоров');
insert into patients(name) values ('Иванов');
insert into patients(name) values ('Петров');

insert into doctors_patients(doctor_id, patient_id) values (1, 1);
insert into doctors_patients(doctor_id, patient_id) values (1, 2);
insert into doctors_patients(doctor_id, patient_id) values (2, 1);
insert into doctors_patients(doctor_id, patient_id) values (2, 2);
insert into doctors_patients(doctor_id, patient_id) values (2, 3);
insert into doctors_patients(doctor_id, patient_id) values (3, 2);
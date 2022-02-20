create database if not exists vehicles; 

use vehicles;

drop tables if exists vehicle_table;

create table vehicle_table (
	id int(10) not null auto_increment PRIMARY KEY,
	type enum('Car', 'Boat', 'Plane') not null,
	year year not null,
	make varchar(50) not null,
	model varchar(50) not null
);

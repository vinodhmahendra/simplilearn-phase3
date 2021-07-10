drop table if exists customers;

create table customers(
	id int not null,
	firstname varchar(200) null,
	lastname varchar(200) null,
	email varchar(200) null,
	primary  key(id));
drop table transaction if exists;
drop table customer if exists;

create table customer(
	id bigint not null AUTO_INCREMENT,
	name varchar(100) not null,
	primary key (id)
);

create table transaction(
	id bigint not null AUTO_INCREMENT,
	tx_date date not null,
	amount numeric(8,2),
	customer_id bigint not null,
	primary key (id)
);
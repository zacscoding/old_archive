create table product(
	code number(5) primary key,
	name varchar2(100),
	price number(8),
	pictureurl varchar(50),
	description varchar(1000)
	);
	
create sequence product_seq start with 1 increment by 1;
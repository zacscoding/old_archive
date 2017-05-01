#schema 생성
CREATE SCHEMA `board_ex` DEFAULT CHARACTER SET utf8;
create user 'board'@'localhost' identified by 'board';
grant all privileges on board_ex.* to 'board'@'localhost';

#member table
create table tbl_member(
	user_no int auto_increment primary key,
	user_id varchar(50) unique,
	password varchar(150),
	email varchar(50),
	profile_pic varchar(150),
	reg_date timestamp default now(),
	enabled boolean default false,
	session_key varchar(50) not null default 'none',
	session_limit timestamp
);


#board table
create table tbl_borad(
	board_no int auto_increment, 
	title varchar(150),
	reply_count int default 0,
	like_count int default 0,
	view_count int default 0,
	reg_date timestamp default now(),
	mod_date timestamp,	
);			

#board detail table
create table tbl_board_detail(
	board_no primary key,
	content varchar(2000),
);
	

#reply table
create table tbl_reply (
	reply_no int auto_increment,
	board_no int not null,
	text varchar(1000) not null,
	user_no int ....
);
	


#init tbl_member
delete from tbl_member where user_no > 0;
alter table tbl_member auto_increment = 1;


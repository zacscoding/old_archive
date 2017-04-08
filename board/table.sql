-- schema »ý¼º
CREATE SCHEMA `board_ex` DEFAULT CHARACTER SET utf8;
create user 'board'@'localhost' identified by 'board';
grant all privileges on board_ex.* to 'board'@'localhost';

--member table
create table tbl_member(
	user_no int auto_increment,
	user_id varchar(50),
	password varchar(50),
	email varchar(50)
	primary user_no
);
	

--board table

--reply table





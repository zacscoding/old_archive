##########################
#create schema
##########################
CREATE SCHEMA `board_ex` DEFAULT CHARACTER SET utf8;
create user 'board'@'localhost' identified by 'board';
grant all privileges on board_ex.* to 'board'@'localhost';


##########################
#member table
##########################

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


##########################
#category
##########################

create table tbl_category (
	cate_no int auto_increment primary key,
	name varchar(150),
	parent_no int default 0	
);


##########################
#board table
##########################

create table tbl_board (
	board_no int auto_increment primary key, 
	title varchar(150),
	user_no int,
	cate_no int,	
	reply_count int default 0,
	like_count int default 0,
	view_count int default 0,
	reg_date timestamp default now(),
	mod_date timestamp,
	foreign key(user_no) references tbl_member(user_no),	
	foreign key(cate_no) references tbl_category(cate_no)	
);


##########################
#board detail table
##########################

create table tbl_board_detail (
	board_no int primary key,
	content varchar(2000),
	foreign key(board_no) references tbl_board(board_no)
);


##########################
#board attachement file table
##########################

create table tbl_board_attach (	
	full_name varchar(150) primary key,
	board_no int,
    regdate timestamp default now(),
	foreign key(board_no) references tbl_board(board_no)		
);


##########################
#board image file table
##########################
create tbl_board_image(
	full_name varchar(150) primary key,
	board_no int,
	regdate timestamp default now(),
	foreign key(board_no) references tbl_board(board_no)
);

	

##########################
#reply table
##########################
create table tbl_reply (
	reply_no int auto_increment,
	board_no int not null,
	text varchar(1000) not null,
	user_no int ....
);
	
	
##########################
#init tbl_member
##########################
delete from tbl_member where user_no > 0;
alter table tbl_member auto_increment = 1;


##########################
# sample category records 
# before implementing admin pages
##########################
insert into tbl_category (name) values ('공지사항');
insert into tbl_category (name) values ('사는애기');
insert into tbl_category (name) values ('포럼');
insert into tbl_category (name) values ('정기모임/스터디');
insert into tbl_category (name) values ('학원/홍보');
















create table board(
	num number(5) primary key,
	pass varchar2(30),
	name varchar2(30),
	email varchar2(30),
	title varchar2(50),
	content varchar2(1000),
	readcount number(4) default 0,
	writedate date default sysdate
	);
create sequence board_seq start with 1 increment by 1;
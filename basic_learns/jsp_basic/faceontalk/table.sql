create table member(
	user_id number primary key
	,user_name varchar2(20) not null
	,password varchar2(20) not null
	,email varchar2(50) not null unique
	,regdate date not null
	,phone varchar2(20) not null
	,gender char(1) not null
	,birth varchar2(20) not null
	,friend_count number not null
);

alter table member modify email unique; 	
alter table member modify gender check(gender='m' or gender='f');

create sequence member_seq;

create table relationship(
	user_one_id number
	,user_two_id number
	,status char(1) not null
	,action_user_id number not null
	,primary key(user_one_id,user_two_id)
	,foreign key (user_one_id) references member(user_id)
	,foreign key (user_two_id) references member(user_id)
);

create table feed(
	feed_no number not null
	,writer_id number not null
	,writer_email varchar2(50) not null unique
	,writer_name varchar2(20) not null
	,content varchar2(3000) not null
	,regdate date not null
	,moddate date not null
	,like_count number
	,pictureUrl varchar2(20)
	,primary key(feed_no,writer_id)
	,foreign key (writer_id) references member(user_id)
	,foreign key (writer_email) references member(email)
	,foreign key (writer_name) references member(user_name)
);

create sequence feed_seq;




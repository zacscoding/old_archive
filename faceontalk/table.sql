#유저 테이블
###############################
# - follower,following 숫자를 넣을지 말지 고민
# - 
################################
create table tbl_member(
	user_id int not null  auto_increment,
    user_name varchar(20) not null,
    user_email varchar(50) not null unique,
    password varchar(20) not null,    
    regdate timestamp not null default now(),
    phone varchar(20) not null, 
	profile varchar(50),
    primary key(user_id)
);



#피드 테이블
###############################
# - 파일 업로드 관련 추가
# - 
################################
create table tbl_feed(
	feed_no int not null auto_increment,
    user_id_fk int not null,
    user_name_fk varchar(20) not null,	
    content text not null,
    regdate timestamp default now(),
    moddate timestamp default now(),
    like_count int default 0,
    file_name varchar(150) not null,
    primary key(feed_no),
    foreign key (user_id_fk) references tbl_member(user_id),
    foreign key (user_name_fk) references tbl_member(user_name)
    ON UPDATE CASCADE ON DELETE CASCADE    
);

#태그 테이블
create table tbl_tag(
	tag_id int not null auto_increment,
    tag_name varchar(256) not null,
    primary key(tag_id)
);

#게시글 - 태그 관계
create table tbl_tagFeedRelations (
	feed_no int not null,
    tag_id int not null,
    primary key(feed_no,tag_id),
    foreign key (feed_no) references tbl_feed(feed_no),
    foreign key (tag_id) references tbl_tag(tag_id)
);

create table tbl_reply(
	rno int not null auto_increment,
	feed_no int not null,
	replytext varchar(1000) not null,
	replyer varchar(50) not null,
	regdate timestamp default now(),
	updatedate timestamp default now(),
	primary key(rno,feed_no),
	foreign key (feed_no) references tbl_feed(feed_no),
	
);
foreign key (replyer) references tbl_member(user_name)
	


#대화 1,2가 대화할 떄 고유한 conversation 
create table tbl_conversation(
	c_id int not null primary key auto_increment,
    user_one int not null,
    user_two int not null,
    ip varchar(30) default null,
    time timestamp default now(),
    foreign key (user_one) references tbl_member(user_id),
    foreign key (user_two) references tbl_member(user_id)
);

#대화 내역
create table tbl_conversation_reply (
	cr_id int not null primary key auto_increment,
    reply text,
    user_id_fk int not null,
    ip varchar(30) not null,
    time timestamp not null default now(),
    c_id_fk int not null,
    status enum('0','1') default '0',
    foreign key (user_id_fk) references tbl_member(user_id),
    foreign key (c_id_fk) references tbl_conversation(c_id)
);

#팔로잉
create table tbl_follow(
	follower int not null,
    following int not null,
    primary key(follower,following),
    foreign key (follower) references tbl_member(user_id),
    foreign key (following) references tbl_member(user_id)
);

#친구 관계
create table tbl_relationship(
	user_one_id int not null,
    user_two_id int not null,
	status enum('0','1','2') default '0',
    primary key(user_one_id,user_two_id),
    foreign key (user_one_id) references tbl_member(user_id),
    foreign key (user_two_id) references tbl_member(user_id)
);


    


###################
#예제
###################
#멤버 삽입
insert into tbl_member (user_name,user_email,password,phone) values('hiva1','hiva1@naver.com','1234','11');
insert into tbl_member (user_name,user_email,password,phone) values('hiva2','hiva2@naver.com','1234','11');
insert into tbl_member (user_name,user_email,password,phone) values('hiva3','hiva3@naver.com','1234','11');
insert into tbl_member (user_name,user_email,password,phone) values('hiva4','hiva4@naver.com','1234','11');

#게시글 삽입
insert into tbl_feed (user_id,content) values(1,'hiva1의 글');
insert into tbl_feed (user_id,content) values(2,'hiva2의 글');
insert into tbl_feed (user_id,content) values(3,'hiva3의 글');
insert into tbl_feed (user_id,content) values(4,'hiva4의 글');

#팔로우 추가
insert into tbl_follow (person,follower) values(1,2);
insert into tbl_follow (person,follower) values(1,3);
insert into tbl_follow (person,follower) values(2,3);
insert into tbl_follow (person,follower) values(3,2);

#3이 팔로우 하는 사람의 게시글 
select * from tbl_feed where user_id = 3 or user_id in (select follower from follow where person=3);



    
	
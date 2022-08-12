select * from notice

create table reply (
 no 		number primary key,
 writer 	varchar2(20),
 wdate 		date, 
 content 	varchar2(200),
 notice_id 	number
);

drop table reply;

insert into reply values(1, 'kim', sysdate, '비밀댓글입니다.', 27);
insert into reply values(2, 'kang', sysdate, '비밀댓글입니다.', 27);
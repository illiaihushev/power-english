drop table book_excerpts;
drop table books;
drop table users;

create table users(
    user_id number(9),
    login varchar(20),
    passphrase varchar(20),
    registration_date date
);

alter table users add constraint pk_user_id primary key(user_id);

create table books(
    book_id number(9),
    user_id number(9),
    book_name varchar(20),
    author varchar(20),
    release_date date
);

alter table books add constraint pk_book_id primary key(book_id);

create table book_excerpts(
    book_excerpt_id number(9),
    book_id number(9),
    page number(9),
    chapter number(9),
    original varchar2(4000),
    translation varchar2(4000)
);

alter table book_excerpts add constraint pk_book_excerpt_id primary key(book_excerpt_id);

alter table users drop constraint fk_books_users;

alter table book_excerpts drop constraint fk_book_excerpts_books;

alter table users add constraint fk_books_users foreign key(user_id) references users(user_id) on delete cascade;

alter table book_excerpts add constraint fk_book_excerpts_books foreign key(book_id) references books(book_id) on delete cascade;

drop sequence book_excerpt_seq;
drop sequence book_seq;
drop sequence user_seq;

create sequence book_excerpt_seq
minvalue 1
start with 1
increment by 1;


create sequence user_seq
minvalue 1
start with 1
increment by 1;

create sequence book_seq
minvalue 1
start with 1
increment by 1;
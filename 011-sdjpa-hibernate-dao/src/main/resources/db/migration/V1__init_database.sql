drop table if exists book cascade;
drop table if exists author;

create table book
(
    id        bigint not null auto_increment primary key,
    isbn      varchar(255),
    publisher varchar(255),
    title     varchar(255),
    author_id BIGINT
) engine = InnoDB;

create table author
(
    id         bigint not null auto_increment primary key,
    first_name varchar(255),
    last_name  varchar(255)
) engine = InnoDB;

alter table book
    add constraint book_author_fk foreign key (author_id) references author (id);

insert into author (first_name, last_name) values ('Craig', 'Walls');

insert into book (isbn, publisher, title, author_id) values ('978-1617294945', 'Simon & Schuster',
       'Spring in Action, 5th Edition',(select id from author where first_name = 'Craig' and last_name = 'Walls') );

insert into book (isbn, publisher, title, author_id) values ('978-1617292545', 'Simon & Schuster',
    'Spring Boot in Action, 1st Edition',(select id from author where first_name = 'Craig' and last_name = 'Walls') );

insert into book (isbn, publisher, title, author_id) values ('978-1617297571', 'Simon & Schuster',
    'Spring in Action, 6th Edition',(select id from author where first_name = 'Craig' and last_name = 'Walls') );

insert into author (first_name, last_name) values ('Eric', 'Evans');

insert into book (isbn, publisher, title, author_id) values ('978-0321125217', 'Addison Wesley',
    'Domain-Driven Design',(select id from author where first_name = 'Eric' and last_name = 'Evans') );

insert into author (first_name, last_name) values ('Robert', 'Martin');

insert into book (isbn, publisher, title, author_id) values ('978-0134494166', 'Addison Wesley',
    'Clean Code',(select id from author where first_name = 'Robert' and last_name = 'Martin') );


insert into author (first_name, last_name) values ('Alan', 'Martin');

insert into book (isbn, publisher, title, author_id) values ('978-0134494167', 'Addison Wesley',
                                                             'Clean Code 1',(select id from author where first_name = 'Alan' and last_name = 'Martin') );

insert into author (first_name, last_name) values ('Bob', 'Martin');

insert into book (isbn, publisher, title, author_id) values ('978-0134494165', 'Addison Wesley',
                                                             'Clean Code 2',(select id from author where first_name = 'Bob' and last_name = 'Martin') );

insert into author (first_name, last_name) values ('Clark', 'Martin');

insert into book (isbn, publisher, title, author_id) values ('978-0134494132', 'Addison Wesley',
                                                             'Clean Code 3',(select id from author where first_name = 'Clark' and last_name = 'Martin') );

insert into author (first_name, last_name) values ('Dave', 'Martin');

insert into book (isbn, publisher, title, author_id) values ('978-0134494132', 'Addison Wesley',
                                                             'Clean Code 3',(select id from author where first_name = 'Clark' and last_name = 'Martin') );

insert into book (isbn, publisher, title, author_id) values ('978-0134494134', 'Addison Wesley',
                                                             'Clean Code 4',(select id from author where first_name = 'Clark' and last_name = 'Martin') );

insert into book (isbn, publisher, title, author_id) values ('978-0134494128', 'Addison Wesley',
                                                             'Clean Code 5',(select id from author where first_name = 'Clark' and last_name = 'Martin') );

insert into book (isbn, publisher, title, author_id) values ('978-0134494116', 'Addison Wesley',
                                                             'Clean Code 6',(select id from author where first_name = 'Clark' and last_name = 'Martin') );

insert into book (isbn, publisher, title, author_id) values ('978-0134494192', 'Addison Wesley',
                                                             'Clean Code 7',(select id from author where first_name = 'Clark' and last_name = 'Martin') );

insert into book (isbn, publisher, title, author_id) values ('978-0134494175', 'Addison Wesley',
                                                             'Clean Code 8',(select id from author where first_name = 'Clark' and last_name = 'Martin') );

insert into book (isbn, publisher, title, author_id) values ('978-01344941343', 'Addison Wesley',
                                                             'Clean Code 9',(select id from author where first_name = 'Clark' and last_name = 'Martin') );

insert into author (first_name, last_name) values ('Robert', 'Smith');
insert into author (first_name, last_name) values ('Alexander', 'Smith');
insert into author (first_name, last_name) values ('Krausz', 'Smith');
insert into author (first_name, last_name) values ('Valentin', 'Smith');
insert into author (first_name, last_name) values ('Ahmed', 'Smith');
insert into author (first_name, last_name) values ('Tishara', 'Smith');
insert into author (first_name, last_name) values ('Yakovlev', 'Smith');
insert into author (first_name, last_name) values ('Claudiu', 'Smith');
insert into author (first_name, last_name) values ('Dinesh', 'Smith');
insert into author (first_name, last_name) values ('Arimardan', 'Smith');
insert into author (first_name, last_name) values ('Prasath', 'Smith');
insert into author (first_name, last_name) values ('Mekandor', 'Smith');
insert into author (first_name, last_name) values ('Valentin', 'Smith');
insert into author (first_name, last_name) values ('Ahmed', 'Smith');
insert into author (first_name, last_name) values ('Tishara', 'Smith');
insert into author (first_name, last_name) values ('Alberto', 'Smith');
insert into author (first_name, last_name) values ('Roberto', 'Smith');
insert into author (first_name, last_name) values ('Barbara', 'Smith');
insert into author (first_name, last_name) values ('Stepan', 'Smith');
insert into author (first_name, last_name) values ('Dhanunjaya', 'Smith');
insert into author (first_name, last_name) values ('Christian', 'Smith');
insert into author (first_name, last_name) values ('Marcel', 'Smith');
insert into author (first_name, last_name) values ('Silvio', 'Smith');
insert into author (first_name, last_name) values ('Mitesh', 'Smith');
insert into author (first_name, last_name) values ('Jayesh', 'Smith');
insert into author (first_name, last_name) values ('Pooja', 'Smith');
insert into author (first_name, last_name) values ('Woraphat', 'Smith');
insert into author (first_name, last_name) values ('Yugal', 'Smith');
insert into author (first_name, last_name) values ('Jasonk', 'Smith');
insert into author (first_name, last_name) values ('Serdar', 'Smith');
insert into author (first_name, last_name) values ('Ugur', 'Smith');
insert into author (first_name, last_name) values ('Vera', 'Smith');
insert into author (first_name, last_name) values ('Surendra', 'Smith');
insert into author (first_name, last_name) values ('Serhad', 'Smith');
insert into author (first_name, last_name) values ('Patrick', 'Smith');
insert into author (first_name, last_name) values ('Yeum', 'Smith');
insert into author (first_name, last_name) values ('Benjamin', 'Smith');
insert into author (first_name, last_name) values ('Jasmine', 'Smith');
insert into author (first_name, last_name) values ('Ganesh', 'Smith');
insert into author (first_name, last_name) values ('Manuel', 'Smith');
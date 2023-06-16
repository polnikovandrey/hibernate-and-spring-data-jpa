drop database if exists order_service;
drop user if exists `orderadmin`@`%`;
drop user if exists `orderuser`@`%`;
create database if not exists order_service character set utf8mb4 collate utf8mb4_unicode_ci;
create user if not exists `orderadmin`@`%` identified with mysql_native_password by 'password';
grant select, insert, update, delete, create, drop, references, index, alter, execute, create view, show view,
    create routine, alter routine, event, trigger on `order_service`.* to `orderadmin`@`%`;
create user if not exists `orderuser`@`%` identified with mysql_native_password by 'password';
grant select, insert, update, delete, show view on `order_service`.* to `orderuser`@`%`;
flush privileges;
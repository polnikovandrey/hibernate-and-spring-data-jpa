-- cardholder db
drop database if exists cardholder;
drop user if exists `cardholderadmin`@`%`;
drop user if exists `cardholderuser`@`%`;
create database if not exists cardholder character set utf8mb4 collate utf8mb4_unicode_ci;
create user if not exists `cardholderadmin`@`%` identified with mysql_native_password by 'password';
grant select, insert, update, delete, create, drop, references, index, alter, execute, create view, show view, create routine, alter routine, event, trigger on `cardholder`.* to `cardholderadmin`@`%`;
create user if not exists `cardholderuser`@`%` identified with mysql_native_password by 'password';
grant select, insert, update, delete, show view on `cardholder`.* to `cardholderuser`@`%`;
flush privileges;

-- card db
drop database if exists card;
drop user if exists `cardadmin`@`%`;
drop user if exists `carduser`@`%`;
create database if not exists card character set utf8mb4 collate utf8mb4_unicode_ci;
create user if not exists `cardadmin`@`%` identified with mysql_native_password by 'password';
grant select, insert, update, delete, create, drop, references, insert, alter, execute, create view, show view, create routine, alter routine, event, trigger on `card`.* to `cardadmin`@`%`;
create user if not exists `carduser`@`%` identified with mysql_native_password by 'password';
grant select, insert, update, delete on `card`.* to `carduser`@`%`;
flush privileges;
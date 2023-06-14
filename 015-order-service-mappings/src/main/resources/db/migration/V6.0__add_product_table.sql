drop table if exists product;
create table product
(
    id BIGINT not null auto_increment primary key,
    description varchar(255),
    product_status varchar(255),
    created_date timestamp,
    last_modified_date timestamp
) engine = InnoDB;
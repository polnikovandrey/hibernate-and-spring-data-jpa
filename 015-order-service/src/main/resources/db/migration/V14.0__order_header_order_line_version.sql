alter table order_header add column version integer default 0;
alter table order_line add column version integer default 0;
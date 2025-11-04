create table products (
id bigserial primary key,
product_type varchar(255) not null,
product_name varchar(255) not null,
supplier_price int not null,
sale_price int not null,
sale_date date,
is_sold boolean default false,
created_at timestamp default current_timestamp
);
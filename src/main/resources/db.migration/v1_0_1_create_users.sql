create type role_type as enum ('Admin', 'Supplier', 'User');

create table users(
id bigserial primary key,
chat_id bigint not null unique,
username varchar(255),
role role_type not null,
created_at timestamp default current_timestamp
);
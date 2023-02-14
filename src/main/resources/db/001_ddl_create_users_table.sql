create table users (
    id serial primary key not null,
    name varchar not null,
    password varchar not null,
    email varchar unique not null,
    created timestamp without time zone default now()
);
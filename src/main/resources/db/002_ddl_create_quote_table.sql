create table quote (
    id serial primary key not null,
    content varchar not null,
    date_of_update timestamp without time zone default now(),
    user_posted_id int not null references users(id),
    score int not null default 0
);
create table vote(
    id serial primary key not null,
    user_id int not null references users(id),
    quote_id int not null references quote(id),
    positive boolean not null
);
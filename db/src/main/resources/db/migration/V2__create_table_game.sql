create sequence if not exists game_id_seq;

create table if not exists game
(
    id          bigint  not null primary key,
    name        text    not null unique,
    description text    not null,
    removed     boolean not null
);
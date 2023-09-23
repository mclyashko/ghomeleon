create sequence if not exists game_release_id_seq;

create table if not exists game_release
(
    id          bigint not null primary key,
    date        date   not null,
    game_id     bigint not null,
    platform_id bigint not null,
    foreign key (game_id) references game (id),
    foreign key (platform_id) references platform (id)
);
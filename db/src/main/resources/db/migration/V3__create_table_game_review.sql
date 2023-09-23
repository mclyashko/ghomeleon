create sequence if not exists game_review_id_seq;

create table if not exists game_review
(
    id      bigint   not null primary key,
    mark    smallint not null,
    text    text     not null,
    game_id bigint   not null,
    foreign key (game_id) references game (id)
);
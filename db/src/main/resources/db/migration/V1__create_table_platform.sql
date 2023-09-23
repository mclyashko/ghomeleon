create sequence if not exists platform_id_seq;

create table if not exists platform
(
    id           bigint  not null primary key,
    name         text    not null unique,
    release_date date    not null,
    manufacturer text    not null,
    removed      boolean not null
);
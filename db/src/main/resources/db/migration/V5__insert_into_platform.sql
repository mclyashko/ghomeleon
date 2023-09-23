insert into platform
    (id, name, release_date, manufacturer, removed)
values (nextval('platform_id_seq'), 'Nintendo DS', '2004-11-21', 'Nintendo', false),
       (nextval('platform_id_seq'), 'Nintendo Switch', '2017-03-03', 'Nintendo', false),
       (nextval('platform_id_seq'), 'PlayStation 5', '2020-11-12', 'Sony', false),
       (nextval('platform_id_seq'), 'Dreamcast', '1998-11-27', 'Sega', false),
       (nextval('platform_id_seq'), 'Xbox Series X', '2020-11-10', 'Microsoft', false),
       (nextval('platform_id_seq'), 'Atari Jaguar', '1993-11-18', 'Atari', false);
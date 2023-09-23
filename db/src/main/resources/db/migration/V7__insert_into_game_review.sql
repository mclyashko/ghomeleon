insert into game_review
    (id, mark, text, game_id)
values (nextval('game_review_id_seq'), 5,
        'Before New Super Mario Bros. entered my life, Yoshi''s Island was, to me, the greatest platformer ever created. That title may now have to go to New Super Mario Bros.',
        (select id from game where game.name = 'New Super Mario Bros.')),
       (nextval('game_review_id_seq'), 5,
        'Мощная, поэтичная, запоминающаяся и очень остроумная игра.',
        (select id from game where game.name = 'Disco Elysium')),
       (nextval('game_review_id_seq'), 5,
        'Невероятно проработанная РПГ, в которой вы можете стать таким детективом, каким захотите. Даже плохим',
        (select id from game where game.name = 'Disco Elysium'));
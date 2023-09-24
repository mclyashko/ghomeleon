package ru.mirea.ghomeleon.domain.game.query

import ru.mirea.ghomeleon.domain.game.Game

fun Game.addNewReview(newReview: Game.Review): Game {
    return Game.restoreGame(
        id = id,
        name = name,
        description = description,
        reviews = reviews + newReview,
        releases = releases,
        removed = removed,
    )
}

fun Game.addNewRelease(newRelease: Game.Release): Game {
    return Game.restoreGame(
        id = id,
        name = name,
        description = description,
        reviews = reviews,
        releases = releases + newRelease,
        removed = removed,
    )
}

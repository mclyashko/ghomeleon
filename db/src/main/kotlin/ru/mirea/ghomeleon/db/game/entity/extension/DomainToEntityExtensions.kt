package ru.mirea.ghomeleon.db.game.entity.extension

import ru.mirea.ghomeleon.db.game.entity.GameEntity
import ru.mirea.ghomeleon.db.game.entity.ReleaseEntity
import ru.mirea.ghomeleon.db.game.entity.ReviewEntity
import ru.mirea.ghomeleon.domain.game.Game

fun Game.toGameEntity(): GameEntity = GameEntity(
    id = id.toLongValue(),
    name = name.toStringValue(),
    description = description.toStringValue(),
    removed = removed,
)

fun Game.Review.toReviewEntity(gameId: Game.Id): ReviewEntity = ReviewEntity(
    id = id.toLongValue(),
    mark = mark.toByteValue(),
    text = text.toStringValue(),
    gameId = gameId.toLongValue(),
)

fun Game.Release.toReleaseEntity(gameId: Game.Id): ReleaseEntity = ReleaseEntity(
    id = id.toLongValue(),
    date = date.toLocalDateValue(),
    gameId = gameId.toLongValue(),
    platformId = platformId.toLongValue(),
)

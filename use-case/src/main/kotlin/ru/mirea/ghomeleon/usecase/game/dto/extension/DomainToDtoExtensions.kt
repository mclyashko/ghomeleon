package ru.mirea.ghomeleon.usecase.game.dto.extension

import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo

internal fun Game.toGameInfoDto(): GameInfo = GameInfo(
    id = id,
    name = name,
    description = description,
    reviews = reviews.map { review ->
        review.toReviewInfoDto()
    },
    releases = releases.map { release ->
        release.toReleaseInfoDto()
    },
    removed = removed,
)

internal fun Game.Review.toReviewInfoDto(): GameInfo.Review = GameInfo.Review(
    id = id,
    mark = mark,
    text = text,
)

internal fun Game.Release.toReleaseInfoDto(): GameInfo.Release = GameInfo.Release(
    id = id,
    date = date,
    platformId = platformId,
)

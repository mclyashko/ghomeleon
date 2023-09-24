package ru.mirea.ghomeleon.rest.game.dto.response.extension

import ru.mirea.ghomeleon.rest.game.dto.response.GameResponse
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo

fun GameInfo.toResponseDto(): GameResponse = GameResponse(
    id = id.toLongValue(),
    name = name.toStringValue(),
    description = description.toStringValue(),
    reviews = reviews.map { review ->
        review.toResponseDto()
    },
    releases = releases.map { release ->
        release.toResponseDto()
    },
    removed = removed,
)

fun GameInfo.Review.toResponseDto(): GameResponse.Review = GameResponse.Review(
    id = id.toLongValue(),
    mark = mark.toByteValue(),
    text = text.toStringValue(),
)

fun GameInfo.Release.toResponseDto(): GameResponse.Release = GameResponse.Release(
    id = id.toLongValue(),
    date = date.toLocalDateValue(),
    platformId = platformId.toLongValue(),
)

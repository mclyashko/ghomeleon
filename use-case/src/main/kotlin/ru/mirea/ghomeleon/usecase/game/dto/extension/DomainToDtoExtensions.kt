package ru.mirea.ghomeleon.usecase.game.dto.extension

import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo

fun Game.toGameInfoDto(): GameInfo = GameInfo(
    id = id,
    name = name,
    description = description,
    reviews = reviews,
    releaseInfos = releases.map { release ->
        release.toReleaseInfoDto()
    }
)

fun Game.Release.toReleaseInfoDto(): GameInfo.ReleaseInfo = GameInfo.ReleaseInfo(
    id = id,
    releaseDate = releaseDate,
    platformId = platformId,
)
package ru.mirea.ghomeleon.usecase.game.dto.extension

import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.usecase.game.dto.UpdateGameInfo

fun Game.updateFromDto(dto: UpdateGameInfo): Game = Game.restoreGame(
    id = id,
    name = dto.name,
    description = dto.description,
    reviews = reviews,
    releases = releases,
    removed = removed,
)

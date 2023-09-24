package ru.mirea.ghomeleon.usecase.game.dto

import ru.mirea.ghomeleon.domain.game.Game

data class UpdateGameInfo(
    val name: Game.Name,
    val description: Game.Description,
)

package ru.mirea.ghomeleon.usecase.game.dto

import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.domain.platform.Platform

data class AddGameReleaseInfo(
    val date: Game.Release.Date,
    val platformId: Platform.Id,
)

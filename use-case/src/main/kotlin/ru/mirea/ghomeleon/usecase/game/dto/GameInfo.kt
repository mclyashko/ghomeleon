package ru.mirea.ghomeleon.usecase.game.dto

import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.domain.platform.Platform

data class GameInfo(
    val id: Game.Id,
    val name: Game.Name,
    val description: Game.Description,
    val reviews: List<Game.Review>,
    val releaseInfos: List<ReleaseInfo>,
) {
    data class ReleaseInfo(
        val id: Game.Release.Id,
        val releaseDate: Game.Release.ReleaseDate,
        val platformId: Platform.Id,
    )
}
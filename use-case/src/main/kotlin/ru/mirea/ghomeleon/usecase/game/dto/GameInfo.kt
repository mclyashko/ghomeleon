package ru.mirea.ghomeleon.usecase.game.dto

import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.domain.platform.Platform

data class GameInfo(
    val id: Game.Id,
    val name: Game.Name,
    val description: Game.Description,
    val reviews: List<Review>,
    val releases: List<Release>,
    val removed: Boolean,
) {
    data class Review(
        val id: Game.Review.Id,
        val mark: Game.Review.Mark,
        val text: Game.Review.Text,
    )

    data class Release(
        val id: Game.Release.Id,
        val date: Game.Release.Date,
        val platformId: Platform.Id,
    )
}

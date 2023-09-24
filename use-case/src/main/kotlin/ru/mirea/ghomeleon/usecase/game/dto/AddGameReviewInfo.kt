package ru.mirea.ghomeleon.usecase.game.dto

import ru.mirea.ghomeleon.domain.game.Game

data class AddGameReviewInfo(
    val mark: Game.Review.Mark,
    val text: Game.Review.Text,
)

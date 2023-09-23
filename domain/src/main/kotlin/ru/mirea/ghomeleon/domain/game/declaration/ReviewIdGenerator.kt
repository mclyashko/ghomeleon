package ru.mirea.ghomeleon.domain.game.declaration

import ru.mirea.ghomeleon.domain.game.Game

interface ReviewIdGenerator {
    fun generate(): Game.Review.Id
}

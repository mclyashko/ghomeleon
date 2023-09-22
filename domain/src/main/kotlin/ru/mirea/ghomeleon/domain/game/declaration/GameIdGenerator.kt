package ru.mirea.ghomeleon.domain.game.declaration

import ru.mirea.ghomeleon.domain.game.Game

interface GameIdGenerator{
    fun generate(): Game.Id
}
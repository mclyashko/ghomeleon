package ru.mirea.ghomeleon.domain.game.declaration

import ru.mirea.ghomeleon.domain.game.Game

fun interface GameAlreadyExists {
    operator fun invoke(name: Game.Name): Boolean
}
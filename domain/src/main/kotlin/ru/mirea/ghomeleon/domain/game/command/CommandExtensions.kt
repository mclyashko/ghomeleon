package ru.mirea.ghomeleon.domain.game.command

import ru.mirea.ghomeleon.domain.game.Game

fun Game.markAsRemoved() {
    removed = true
}

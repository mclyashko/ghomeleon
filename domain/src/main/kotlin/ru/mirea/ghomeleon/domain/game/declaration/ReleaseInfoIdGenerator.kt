package ru.mirea.ghomeleon.domain.game.declaration

import ru.mirea.ghomeleon.domain.game.Game

interface ReleaseInfoIdGenerator {
    fun generate(): Game.Release.Id
}
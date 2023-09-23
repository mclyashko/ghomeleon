package ru.mirea.ghomeleon.usecase.game.declaration

import ru.mirea.ghomeleon.domain.game.Game

interface GamePersister {
    fun save(game: Game)
}

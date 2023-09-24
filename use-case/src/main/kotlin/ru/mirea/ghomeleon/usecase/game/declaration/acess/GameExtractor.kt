package ru.mirea.ghomeleon.usecase.game.declaration.acess

import ru.mirea.ghomeleon.domain.game.Game

interface GameExtractor {
    fun getById(id: Game.Id): Game?

    fun getByName(name: Game.Name): Game?

    fun getAll(): List<Game>
}

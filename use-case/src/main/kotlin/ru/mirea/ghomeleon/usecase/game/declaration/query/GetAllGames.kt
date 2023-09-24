package ru.mirea.ghomeleon.usecase.game.declaration.query

import ru.mirea.ghomeleon.usecase.game.dto.GameInfo

interface GetAllGames {
    fun execute(): List<GameInfo>
}

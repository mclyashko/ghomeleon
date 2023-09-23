package ru.mirea.ghomeleon.usecase.game.declaration

import ru.mirea.ghomeleon.usecase.game.dto.GameInfo

interface GetAllGames {
    fun execute(): List<GameInfo>
}

package ru.mirea.ghomeleon.usecase.game.declaration.command

import ru.mirea.ghomeleon.usecase.game.dto.AddGameInfo
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo

interface AddNewGame {
    fun execute(addGameInfo: AddGameInfo): GameInfo
}

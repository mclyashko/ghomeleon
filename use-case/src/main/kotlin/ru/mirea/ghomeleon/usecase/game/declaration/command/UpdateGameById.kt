package ru.mirea.ghomeleon.usecase.game.declaration.command

import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo
import ru.mirea.ghomeleon.usecase.game.dto.UpdateGameInfo
import kotlin.jvm.Throws

interface UpdateGameById {
    @Throws(DomainException.UpdateGameByIdNotFoundException::class)
    fun execute(id: Game.Id, updateGameInfo: UpdateGameInfo): GameInfo
}

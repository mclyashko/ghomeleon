package ru.mirea.ghomeleon.usecase.game.declaration.command

import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo
import kotlin.jvm.Throws

interface RemoveGameById {
    @Throws(DomainException.RemoveGameByIdNotFoundException::class)
    fun execute(id: Game.Id): GameInfo
}

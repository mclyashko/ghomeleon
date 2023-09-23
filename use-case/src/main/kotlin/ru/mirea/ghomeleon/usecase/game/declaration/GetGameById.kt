package ru.mirea.ghomeleon.usecase.game.declaration

import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo
import kotlin.jvm.Throws

interface GetGameById {
    @Throws(DomainException.GetGameByIdNotFoundException::class)
    fun execute(id: Game.Id): GameInfo
}

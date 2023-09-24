package ru.mirea.ghomeleon.usecase.game.declaration.command

import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.usecase.game.dto.AddGameReviewInfo
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo
import kotlin.jvm.Throws

interface AddNewGameReviewById {
    @Throws(DomainException.AddNewGameReviewByIdNotFoundException::class)
    fun execute(id: Game.Id, addGameReviewInfo: AddGameReviewInfo): GameInfo
}

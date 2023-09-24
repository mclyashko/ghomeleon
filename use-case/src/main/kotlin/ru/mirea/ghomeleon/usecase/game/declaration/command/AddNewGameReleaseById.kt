package ru.mirea.ghomeleon.usecase.game.declaration.command

import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.usecase.game.dto.AddGameReleaseInfo
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo
import kotlin.jvm.Throws

interface AddNewGameReleaseById {
    @Throws(DomainException.AddNewGameReleaseByIdNotFoundException::class)
    fun execute(id: Game.Id, addGameReleaseInfo: AddGameReleaseInfo): GameInfo
}

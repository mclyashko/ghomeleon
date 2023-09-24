package ru.mirea.ghomeleon.usecase.game.scenario.command

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.domain.game.command.markAsRemoved
import ru.mirea.ghomeleon.usecase.game.declaration.acess.GameExtractor
import ru.mirea.ghomeleon.usecase.game.declaration.acess.GamePersister
import ru.mirea.ghomeleon.usecase.game.declaration.command.RemoveGameById
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo
import ru.mirea.ghomeleon.usecase.game.dto.extension.toGameInfoDto

@Component
class RemoveGameByIdUseCase(
    private val gameExtractor: GameExtractor,
    private val gamePersister: GamePersister,
) : RemoveGameById {
    @Transactional
    override fun execute(id: Game.Id): GameInfo {
        val game = gameExtractor
            .getById(id = id)
            ?: throw DomainException.RemoveGameByIdNotFoundException(
                "Game with id '${id.toLongValue()}' doesn't exist!"
            )

        game.markAsRemoved()

        return game.let {
            gamePersister.save(it)
            it.toGameInfoDto()
        }
    }
}

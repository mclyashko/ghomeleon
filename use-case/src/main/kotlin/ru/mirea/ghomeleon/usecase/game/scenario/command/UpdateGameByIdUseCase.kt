package ru.mirea.ghomeleon.usecase.game.scenario.command

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.usecase.game.declaration.acess.GameExtractor
import ru.mirea.ghomeleon.usecase.game.declaration.acess.GamePersister
import ru.mirea.ghomeleon.usecase.game.declaration.command.UpdateGameById
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo
import ru.mirea.ghomeleon.usecase.game.dto.UpdateGameInfo
import ru.mirea.ghomeleon.usecase.game.dto.extension.toGameInfoDto
import ru.mirea.ghomeleon.usecase.game.dto.extension.updateFromDto

@Component
class UpdateGameByIdUseCase(
    private val gameExtractor: GameExtractor,
    private val gamePersister: GamePersister,
) : UpdateGameById {
    @Transactional
    override fun execute(id: Game.Id, updateGameInfo: UpdateGameInfo): GameInfo {
        val game = gameExtractor
            .getById(id = id)
            ?: throw DomainException.UpdateGameByIdNotFoundException(
                "Game with id '${id.toLongValue()}' doesn't exist!"
            )

        return game.updateFromDto(updateGameInfo).let {
            gamePersister.save(it)
            it.toGameInfoDto()
        }
    }
}

package ru.mirea.ghomeleon.usecase.game.scenario

import org.springframework.stereotype.Component
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.usecase.game.declaration.GameExtractor
import ru.mirea.ghomeleon.usecase.game.declaration.GetGameById
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo
import ru.mirea.ghomeleon.usecase.game.dto.extension.toGameInfoDto

@Component
class GetGameByIdUseCase(
    private val gameExtractor: GameExtractor,
) : GetGameById {
    override fun execute(id: Game.Id): GameInfo {
        return gameExtractor
            .getById(id = id)
            ?.toGameInfoDto()
            ?: throw DomainException.GetGameByIdNotFoundException(
                "Game with id '${id.toLongValue()}' doesn't exist!"
            )
    }
}
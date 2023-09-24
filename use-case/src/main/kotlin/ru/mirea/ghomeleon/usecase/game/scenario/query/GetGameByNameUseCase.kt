package ru.mirea.ghomeleon.usecase.game.scenario.query

import org.springframework.stereotype.Component
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.usecase.game.declaration.acess.GameExtractor
import ru.mirea.ghomeleon.usecase.game.declaration.query.GetGameByName
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo
import ru.mirea.ghomeleon.usecase.game.dto.extension.toGameInfoDto

@Component
class GetGameByNameUseCase(
    private val gameExtractor: GameExtractor,
) : GetGameByName {
    override fun execute(name: Game.Name): GameInfo {
        return gameExtractor
            .getByName(name = name)
            ?.toGameInfoDto()
            ?: throw DomainException.GetGameByNameNotFoundException(
                "Game with name '${name.toStringValue()}' doesn't exist!"
            )
    }
}

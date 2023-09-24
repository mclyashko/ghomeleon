package ru.mirea.ghomeleon.usecase.game.scenario.query

import org.springframework.stereotype.Component
import ru.mirea.ghomeleon.usecase.game.declaration.acess.GameExtractor
import ru.mirea.ghomeleon.usecase.game.declaration.query.GetAllGames
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo
import ru.mirea.ghomeleon.usecase.game.dto.extension.toGameInfoDto

@Component
class GetAllGamesUseCase(
    private val gameExtractor: GameExtractor
) : GetAllGames {
    override fun execute(): List<GameInfo> {
        return gameExtractor
            .getAll()
            .map { game ->
                game.toGameInfoDto()
            }
    }
}

package ru.mirea.ghomeleon.usecase.game.scenario

import org.springframework.stereotype.Component
import ru.mirea.ghomeleon.usecase.game.declaration.GameExtractor
import ru.mirea.ghomeleon.usecase.game.declaration.GetAllGames
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

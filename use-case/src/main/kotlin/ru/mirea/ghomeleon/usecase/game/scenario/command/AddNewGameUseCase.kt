package ru.mirea.ghomeleon.usecase.game.scenario.command

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.domain.game.declaration.GameAlreadyExists
import ru.mirea.ghomeleon.domain.game.declaration.GameIdGenerator
import ru.mirea.ghomeleon.usecase.game.declaration.acess.GamePersister
import ru.mirea.ghomeleon.usecase.game.declaration.command.AddNewGame
import ru.mirea.ghomeleon.usecase.game.dto.AddGameInfo
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo
import ru.mirea.ghomeleon.usecase.game.dto.extension.toGameInfoDto

@Component
class AddNewGameUseCase(
    private val gameIdGenerator: GameIdGenerator,
    private val gameAlreadyExists: GameAlreadyExists,
    private val gamePersister: GamePersister,
) : AddNewGame {
    @Transactional
    override fun execute(addGameInfo: AddGameInfo): GameInfo {
        return Game.addGame(
            gameIdGenerator = gameIdGenerator,
            gameAlreadyExists = gameAlreadyExists,
            name = addGameInfo.name,
            description = addGameInfo.description
        ).let { game ->
            gamePersister.save(game)
            game.toGameInfoDto()
        }
    }
}

package ru.mirea.ghomeleon.usecase.game.scenario.command

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.domain.game.declaration.ReleaseIdGenerator
import ru.mirea.ghomeleon.domain.game.query.addNewRelease
import ru.mirea.ghomeleon.usecase.game.declaration.acess.GameExtractor
import ru.mirea.ghomeleon.usecase.game.declaration.acess.GamePersister
import ru.mirea.ghomeleon.usecase.game.declaration.command.AddNewGameReleaseById
import ru.mirea.ghomeleon.usecase.game.dto.AddGameReleaseInfo
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo
import ru.mirea.ghomeleon.usecase.game.dto.extension.toGameInfoDto

@Component
class AddNewGameReleaseByIdUseCase(
    private val gameExtractor: GameExtractor,
    private val releaseIdGenerator: ReleaseIdGenerator,
    private val gamePersister: GamePersister,
) : AddNewGameReleaseById {
    @Transactional
    override fun execute(id: Game.Id, addGameReleaseInfo: AddGameReleaseInfo): GameInfo {
        val game = gameExtractor
            .getById(id = id)
            ?: throw DomainException.AddNewGameReleaseByIdNotFoundException(
                "Game with id '${id.toLongValue()}' doesn't exist!"
            )

        val gameWithNewRelease = game.addNewRelease(
            Game.Release.addRelease(
                releaseIdGenerator = releaseIdGenerator,
                date = addGameReleaseInfo.date,
                platformId = addGameReleaseInfo.platformId,
            )
        )

        return gameWithNewRelease.let {
            gamePersister.save(it)
            it.toGameInfoDto()
        }
    }
}

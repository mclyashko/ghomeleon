package ru.mirea.ghomeleon.db.game.acess

import org.springframework.stereotype.Repository
import ru.mirea.ghomeleon.db.game.entity.GameEntity
import ru.mirea.ghomeleon.db.game.repository.GameRepository
import ru.mirea.ghomeleon.db.game.repository.ReleaseRepository
import ru.mirea.ghomeleon.db.game.repository.ReviewRepository
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.usecase.game.declaration.GameExtractor
import kotlin.jvm.optionals.getOrNull

@Repository
class GameExtractorImpl(
    private val gameRepository: GameRepository,
    private val reviewRepository: ReviewRepository,
    private val releaseRepository: ReleaseRepository,
) : GameExtractor {
    override fun getById(id: Game.Id): Game? {
        val gameEntity = gameRepository
            .findById(id.toLongValue())
            .getOrNull()

        return gameEntity?.let {
            constructDomain(it)
        }
    }

    override fun getByName(name: Game.Name): Game? {
        val gameEntity = gameRepository
            .findByName(name.toStringValue())
            .getOrNull()

        return gameEntity?.let {
            constructDomain(it)
        }
    }

    override fun getAll(): List<Game> {
        val gameEntities = gameRepository
            .findAll()

        return gameEntities.map {
            constructDomain(it)
        }
    }

    private fun constructDomain(gameEntity: GameEntity): Game {
        val reviewEntities = reviewRepository.findAllByGameId(gameEntity.id)
        val releaseEntities = releaseRepository.findAllByGameId(gameEntity.id)

        return Game.restoreGame(
            id = Game.Id(gameEntity.id),
            name = Game.Name(gameEntity.name),
            description = Game.Description(gameEntity.description),
            reviews = reviewEntities.map { reviewEntity ->
                Game.Review(
                    id = Game.Review.Id(reviewEntity.id),
                    mark = Game.Review.Mark(reviewEntity.mark),
                    text = Game.Review.Text(reviewEntity.text),
                )
            },
            releases = releaseEntities.map { releaseEntity ->
                Game.Release(
                    id = Game.Release.Id(releaseEntity.id),
                    date = Game.Release.Date(releaseEntity.date),
                    platformId = Platform.Id(releaseEntity.platformId),
                )
            },
            removed = gameEntity.removed,
        )
    }
}

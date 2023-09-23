package ru.mirea.ghomeleon.db.game.acess

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.mirea.ghomeleon.db.game.entity.extension.toGameEntity
import ru.mirea.ghomeleon.db.game.entity.extension.toReleaseEntity
import ru.mirea.ghomeleon.db.game.entity.extension.toReviewEntity
import ru.mirea.ghomeleon.db.game.repository.GameRepository
import ru.mirea.ghomeleon.db.game.repository.ReleaseRepository
import ru.mirea.ghomeleon.db.game.repository.ReviewRepository
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.usecase.game.declaration.GamePersister

@Repository
class GamePersisterImpl(
    private val gameRepository: GameRepository,
    private val reviewRepository: ReviewRepository,
    private val releaseRepository: ReleaseRepository,
) : GamePersister {

    @Transactional
    override fun save(game: Game) {
        val gameEntity = game.toGameEntity()

        val reviewEntities = game.reviews.map { review ->
            review.toReviewEntity(gameId = game.id)
        }

        val releaseEntities = game.releases.map { release ->
            release.toReleaseEntity(gameId = game.id)
        }

        gameRepository.save(gameEntity)
        reviewRepository.saveAll(reviewEntities)
        releaseRepository.saveAll(releaseEntities)
    }
}

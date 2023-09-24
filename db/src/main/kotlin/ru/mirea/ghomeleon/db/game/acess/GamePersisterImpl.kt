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
import ru.mirea.ghomeleon.usecase.game.declaration.acess.GamePersister

@Repository
class GamePersisterImpl(
    private val gameRepository: GameRepository,
    private val reviewRepository: ReviewRepository,
    private val releaseRepository: ReleaseRepository,
) : GamePersister {

    @Transactional
    override fun save(game: Game) {
        (game to game.toGameEntity())
            .let { (game, gameEntity) ->
                if (game.isNew()) {
                    gameEntity.apply { isNew = true }
                }
                gameRepository.save(gameEntity)
            }

        game.reviews.map { review -> review to review.toReviewEntity(game.id) }
            .map { (review, reviewEntity) ->
                if (review.isNew()) {
                    reviewEntity.apply { isNew = true }
                }
                reviewEntity
            }
            .let { reviewEntities ->
                reviewRepository.saveAll(reviewEntities)
            }

        game.releases.map { release -> release to release.toReleaseEntity(game.id) }
            .map { (release, releaseEntity) ->
                if (release.isNew()) {
                    releaseEntity.apply { isNew = true }
                }
                releaseEntity
            }
            .let { releaseEntities ->
                releaseRepository.saveAll(releaseEntities)
            }

        game.markPersistedCascade()
    }
}

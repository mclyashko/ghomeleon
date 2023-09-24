package ru.mirea.ghomeleon.usecase.game.scenario.command

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.domain.game.declaration.ReviewIdGenerator
import ru.mirea.ghomeleon.domain.game.query.addNewReview
import ru.mirea.ghomeleon.usecase.game.declaration.acess.GameExtractor
import ru.mirea.ghomeleon.usecase.game.declaration.acess.GamePersister
import ru.mirea.ghomeleon.usecase.game.declaration.command.AddNewGameReviewById
import ru.mirea.ghomeleon.usecase.game.dto.AddGameReviewInfo
import ru.mirea.ghomeleon.usecase.game.dto.GameInfo
import ru.mirea.ghomeleon.usecase.game.dto.extension.toGameInfoDto

@Component
class AddNewGameReviewByIdUseCase(
    private val gameExtractor: GameExtractor,
    private val reviewIdGenerator: ReviewIdGenerator,
    private val gamePersister: GamePersister,
) : AddNewGameReviewById {
    @Transactional
    override fun execute(id: Game.Id, addGameReviewInfo: AddGameReviewInfo): GameInfo {
        val game = gameExtractor
            .getById(id = id)
            ?: throw DomainException.AddNewGameReviewByIdNotFoundException(
                "Game with id '${id.toLongValue()}' doesn't exist!"
            )

        val gameWithNewReview = game.addNewReview(
            Game.Review.addReview(
                reviewIdGenerator = reviewIdGenerator,
                mark = addGameReviewInfo.mark,
                text = addGameReviewInfo.text,
            )
        )

        return gameWithNewReview.let {
            gamePersister.save(it)
            it.toGameInfoDto()
        }
    }
}

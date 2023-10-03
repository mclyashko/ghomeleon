package ru.mirea.ghomeleon.usecase.game.invariant

import org.springframework.stereotype.Component
import ru.mirea.ghomeleon.domain.common.util.metric.MetricLogger
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.domain.game.declaration.GameAlreadyExists
import ru.mirea.ghomeleon.usecase.game.declaration.acess.GameExtractor

@Component
class GameAlreadyExistsByExtractor(
    private val gameExtractor: GameExtractor,
    private val metricLogger: MetricLogger,
) : GameAlreadyExists {
    override fun invoke(name: Game.Name): Boolean {
        val game = gameExtractor.getByName(name)
        game?.let {
            metricLogger.registerCounter(
                name = MetricLogger.GAME_ALREADY_EXISTS_VALIDATION_FAILED
            )
            return true
        }
        return false
    }
}

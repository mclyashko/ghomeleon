package ru.mirea.ghomeleon.usecase.game.invariant

import org.springframework.stereotype.Component
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.domain.game.declaration.GameAlreadyExists
import ru.mirea.ghomeleon.usecase.game.declaration.acess.GameExtractor

@Component
class GameAlreadyExistsByExtractor(
    private val gameExtractor: GameExtractor,
) : GameAlreadyExists {
    override fun invoke(name: Game.Name): Boolean {
        val game = gameExtractor.getByName(name)
        return game != null
    }
}

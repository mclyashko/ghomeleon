package ru.mirea.ghomeleon.db.game.repository

import org.springframework.data.repository.CrudRepository
import ru.mirea.ghomeleon.db.game.entity.GameEntity
import java.util.Optional

interface GameRepository : CrudRepository<GameEntity, Long> {
    fun findByName(name: String): Optional<GameEntity>
}

package ru.mirea.ghomeleon.db.game.repository

import org.springframework.data.repository.CrudRepository
import ru.mirea.ghomeleon.db.game.entity.ReleaseEntity

interface ReleaseRepository : CrudRepository<ReleaseEntity, Long> {
    fun findAllByGameId(gameId: Long): List<ReleaseEntity>
}

package ru.mirea.ghomeleon.db.game.repository

import org.springframework.data.repository.CrudRepository
import ru.mirea.ghomeleon.db.game.entity.ReviewEntity

interface ReviewRepository : CrudRepository<ReviewEntity, Long> {
    fun findAllByGameId(gameId: Long): List<ReviewEntity>
}

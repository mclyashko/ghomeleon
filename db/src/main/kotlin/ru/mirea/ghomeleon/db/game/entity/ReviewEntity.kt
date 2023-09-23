package ru.mirea.ghomeleon.db.game.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("game_review")
data class ReviewEntity(
    @Id
    val id: Long,
    val mark: Byte,
    val text: String,
    val gameId: Long,
)

package ru.mirea.ghomeleon.db.game.entity

import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("game_release")
data class ReleaseEntity(
    val id: Long,
    val date: LocalDate,
    val gameId: Long,
    val platformId: Long,
)

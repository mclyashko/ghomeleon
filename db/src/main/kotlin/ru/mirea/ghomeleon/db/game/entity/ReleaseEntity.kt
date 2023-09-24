package ru.mirea.ghomeleon.db.game.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("game_release")
data class ReleaseEntity(
    @Id
    val id: Long,
    val date: LocalDate,
    val gameId: Long,
    val platformId: Long,
) : Persistable<Long> {

    @field:Transient
    internal var isNew = false

    override fun getId(): Long {
        return id
    }

    override fun isNew(): Boolean {
        return isNew
    }
}

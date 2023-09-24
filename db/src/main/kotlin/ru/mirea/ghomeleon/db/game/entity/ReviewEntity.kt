package ru.mirea.ghomeleon.db.game.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table

@Table("game_review")
data class ReviewEntity(
    @Id
    val id: Long,
    val mark: Byte,
    val text: String,
    val gameId: Long,
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

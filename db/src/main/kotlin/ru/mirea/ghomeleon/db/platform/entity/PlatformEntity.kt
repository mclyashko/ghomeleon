package ru.mirea.ghomeleon.db.platform.entity

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("platform")
data class PlatformEntity(
    @Id
    val id: Long,
    val name: String,
    val releaseDate: LocalDate,
    val manufacturer: String,
    val removed: Boolean,
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

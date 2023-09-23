package ru.mirea.ghomeleon.db.platform.entity

import org.springframework.data.annotation.Id
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
)

package ru.mirea.ghomeleon.rest.platform.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "Model for the platform.")
data class PlatformResponse(
    @field:Schema(
        description = "Platform ID",
        example = "1",
        type = "long",
    )
    val id: Long,
    @field:Schema(
        description = "Platform name",
        example = "Nintendo DS",
        type = "string",
    )
    val name: String,
    @field:Schema(
        description = "Release date",
        example = "2003-05-02",
        type = "local date",
    )
    val releaseDate: LocalDate,
    @field:Schema(
        description = "Platform manufacturer",
        example = "Nintendo",
        type = "enum",
    )
    val manufacturer: String,
    @field:Schema(
        description = "Is platform removed",
        type = "boolean"
    )
    val removed: Boolean,
)

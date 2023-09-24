package ru.mirea.ghomeleon.rest.platform.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "Model for updating information about the platform.")
data class UpdatePlatformRequest(
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
)

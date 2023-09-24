package ru.mirea.ghomeleon.rest.game.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "Model for adding a new release to the game.")
data class AddGameReleaseRequest(
    @field:Schema(
        description = "Release date",
        example = "2003-05-02",
        type = "local date",
    )
    val date: LocalDate,
    @field:Schema(
        description = "Platform Id",
        example = "3",
        type = "byte",
    )
    val platformId: Long,
)

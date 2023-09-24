package ru.mirea.ghomeleon.rest.game.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Model for updating information about the game.")
data class UpdateGameRequest(
    @field:Schema(
        description = "Game name",
        example = "New Super Mario Bros.",
        type = "string",
    )
    val name: String,
    @field:Schema(
        description = "Game description",
        example = "Platformer",
        type = "string",
    )
    val description: String,
)

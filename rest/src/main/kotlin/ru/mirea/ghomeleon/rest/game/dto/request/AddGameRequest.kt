package ru.mirea.ghomeleon.rest.game.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Model for adding a new game.")
data class AddGameRequest(
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

package ru.mirea.ghomeleon.rest.game.dto.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Model for adding a new review to the game.")
data class AddGameReviewRequest(
    @field:Schema(
        description = "Review mark",
        example = "5",
        type = "byte",
    )
    val mark: Byte,
    @field:Schema(
        description = "Review text",
        example = "5",
        type = "Great game",
    )
    val text: String,
)

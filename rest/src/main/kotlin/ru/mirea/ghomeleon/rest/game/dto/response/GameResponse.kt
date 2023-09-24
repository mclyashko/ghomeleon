package ru.mirea.ghomeleon.rest.game.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Schema(description = "Model for the game.")
data class GameResponse(
    @field:Schema(
        description = "Game ID",
        example = "3",
        type = "long",
    )
    val id: Long,
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
    @field:Schema(
        description = "List of game reviews",
        type = "review[]",
    )
    val reviews: List<Review>,
    @field:Schema(
        description = "List of game releases",
        type = "release[]",
    )
    val releases: List<Release>,
    @field:Schema(
        description = "Is game removed",
        type = "boolean"
    )
    val removed: Boolean,
) {
    @Schema(description = "Model for the game review.")
    data class Review(
        @field:Schema(
            description = "Review ID",
            example = "3",
            type = "long",
        )
        val id: Long,
        @field:Schema(
            description = "Review mark",
            example = "4",
            type = "byte",
        )
        val mark: Byte,
        @field:Schema(
            description = "Review text",
            example = "Good game",
            type = "string",
        )
        val text: String,
    )

    @Schema(description = "Model for the game release.")
    data class Release(
        @field:Schema(
            description = "Release ID",
            example = "3",
            type = "long",
        )
        val id: Long,
        @field:Schema(
            description = "Release date",
            example = "2003-05-02",
            type = "local date",
        )
        val date: LocalDate,
        @field:Schema(
            description = "Platform ID",
            example = "3",
            type = "long",
        )
        val platformId: Long,
    )
}

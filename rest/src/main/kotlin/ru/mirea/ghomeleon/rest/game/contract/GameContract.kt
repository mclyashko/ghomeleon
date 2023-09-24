package ru.mirea.ghomeleon.rest.game.contract

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import ru.mirea.ghomeleon.rest.game.dto.request.AddGameReleaseRequest
import ru.mirea.ghomeleon.rest.game.dto.request.AddGameRequest
import ru.mirea.ghomeleon.rest.game.dto.request.AddGameReviewRequest
import ru.mirea.ghomeleon.rest.game.dto.request.UpdateGameRequest
import ru.mirea.ghomeleon.rest.game.dto.response.GameResponse

@OpenAPIDefinition(
    info = Info(
        title = "ghomeleon",
        version = "1.0.0"
    )
)
interface GameContract {
    @Operation(
        summary = "Get all games"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun getAllGames(): ResponseEntity<List<GameResponse>>

    @Operation(
        summary = "Get game by id"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Game not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun getGameById(id: Long): ResponseEntity<GameResponse>

    @Operation(
        summary = "Get game by name"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Game not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun getGameByName(name: String): ResponseEntity<GameResponse>

    @Operation(
        summary = "Add new game"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun addNewGame(addGameRequest: AddGameRequest): ResponseEntity<GameResponse>

    @Operation(
        summary = "Add new review"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Game not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun addNewGameReview(
        id: Long,
        addGameReviewRequest: AddGameReviewRequest,
    ): ResponseEntity<GameResponse>

    @Operation(
        summary = "Add new release"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Game not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun addNewGameRelease(
        id: Long,
        addGameReleaseRequest: AddGameReleaseRequest,
    ): ResponseEntity<GameResponse>

    @Operation(
        summary = "Update information about the game by id"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Game not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun updateGame(
        id: Long,
        updateGameRequest: UpdateGameRequest
    ): ResponseEntity<GameResponse>

    @Operation(
        summary = "Mark game as removed"
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Game not found"),
            ApiResponse(responseCode = "500", description = "Something went wrong"),
        ]
    )
    fun removeGame(
        id: Long,
    ): ResponseEntity<GameResponse>
}

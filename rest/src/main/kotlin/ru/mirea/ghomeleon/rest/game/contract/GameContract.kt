package ru.mirea.ghomeleon.rest.game.contract

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
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
}

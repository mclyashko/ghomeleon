package ru.mirea.ghomeleon.rest.game.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.rest.game.contract.GameContract
import ru.mirea.ghomeleon.rest.game.dto.response.GameResponse
import ru.mirea.ghomeleon.rest.game.dto.response.extension.toResponseDto
import ru.mirea.ghomeleon.usecase.game.declaration.GetAllGames
import ru.mirea.ghomeleon.usecase.game.declaration.GetGameById

@RestController
@RequestMapping("/api")
class GameController(
    private val getAllGames: GetAllGames,
    private val getGameById: GetGameById,
) : GameContract {
    @GetMapping("/game")
    override fun getAllGames(): ResponseEntity<List<GameResponse>> {
        return ResponseEntity.ok(
            getAllGames
                .execute()
                .map { gameInfo ->
                    gameInfo.toResponseDto()
                }
        )
    }

    @GetMapping("/game/id")
    override fun getGameById(
        @RequestParam id: Long
    ): ResponseEntity<GameResponse> {
        return ResponseEntity.ok(
            getGameById
                .execute(Game.Id(id))
                .toResponseDto()
        )
    }
}

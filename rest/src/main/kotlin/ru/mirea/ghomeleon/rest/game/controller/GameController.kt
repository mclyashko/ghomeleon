package ru.mirea.ghomeleon.rest.game.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.rest.game.contract.GameContract
import ru.mirea.ghomeleon.rest.game.dto.request.AddGameReleaseRequest
import ru.mirea.ghomeleon.rest.game.dto.request.AddGameRequest
import ru.mirea.ghomeleon.rest.game.dto.request.AddGameReviewRequest
import ru.mirea.ghomeleon.rest.game.dto.request.UpdateGameRequest
import ru.mirea.ghomeleon.rest.game.dto.request.extension.toAddGameInfo
import ru.mirea.ghomeleon.rest.game.dto.request.extension.toAddGameReleaseInfo
import ru.mirea.ghomeleon.rest.game.dto.request.extension.toAddGameReviewInfo
import ru.mirea.ghomeleon.rest.game.dto.request.extension.toUpdateGameInfo
import ru.mirea.ghomeleon.rest.game.dto.response.GameResponse
import ru.mirea.ghomeleon.rest.game.dto.response.extension.toResponseDto
import ru.mirea.ghomeleon.usecase.game.declaration.command.AddNewGame
import ru.mirea.ghomeleon.usecase.game.declaration.command.AddNewGameReleaseById
import ru.mirea.ghomeleon.usecase.game.declaration.command.AddNewGameReviewById
import ru.mirea.ghomeleon.usecase.game.declaration.command.RemoveGameById
import ru.mirea.ghomeleon.usecase.game.declaration.command.UpdateGameById
import ru.mirea.ghomeleon.usecase.game.declaration.query.GetAllGames
import ru.mirea.ghomeleon.usecase.game.declaration.query.GetGameById
import ru.mirea.ghomeleon.usecase.game.declaration.query.GetGameByName

@RestController
@RequestMapping("/api")
@Suppress("LongParameterList")
class GameController(
    private val getAllGames: GetAllGames,
    private val getGameById: GetGameById,
    private val getGameByName: GetGameByName,
    private val addNewGame: AddNewGame,
    private val addNewGameReviewById: AddNewGameReviewById,
    private val addNewGameReleaseById: AddNewGameReleaseById,
    private val updateGameById: UpdateGameById,
    private val removeGameById: RemoveGameById,
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
                .execute(id = Game.Id(id))
                .toResponseDto()
        )
    }

    @GetMapping("/game/name")
    override fun getGameByName(
        @RequestParam name: String
    ): ResponseEntity<GameResponse> {
        return ResponseEntity.ok(
            getGameByName
                .execute(name = Game.Name(name))
                .toResponseDto()
        )
    }

    @PostMapping("/game")
    override fun addNewGame(
        @RequestBody addGameRequest: AddGameRequest
    ): ResponseEntity<GameResponse> {
        return ResponseEntity.ok(
            addNewGame
                .execute(addGameInfo = addGameRequest.toAddGameInfo())
                .toResponseDto()
        )
    }

    @PostMapping("/game/id/review")
    override fun addNewGameReview(
        @RequestParam id: Long,
        @RequestBody addGameReviewRequest: AddGameReviewRequest
    ): ResponseEntity<GameResponse> {
        return ResponseEntity.ok(
            addNewGameReviewById
                .execute(
                    id = Game.Id(id),
                    addGameReviewInfo = addGameReviewRequest.toAddGameReviewInfo()
                )
                .toResponseDto()
        )
    }

    @PostMapping("/game/id/release")
    override fun addNewGameRelease(
        @RequestParam id: Long,
        @RequestBody addGameReleaseRequest: AddGameReleaseRequest
    ): ResponseEntity<GameResponse> {
        return ResponseEntity.ok(
            addNewGameReleaseById
                .execute(
                    id = Game.Id(id),
                    addGameReleaseInfo = addGameReleaseRequest.toAddGameReleaseInfo(),
                )
                .toResponseDto()
        )
    }

    @PutMapping("/game/id")
    override fun updateGame(
        @RequestParam id: Long,
        @RequestBody updateGameRequest: UpdateGameRequest
    ): ResponseEntity<GameResponse> {
        return ResponseEntity.ok(
            updateGameById
                .execute(
                    id = Game.Id(id),
                    updateGameInfo = updateGameRequest.toUpdateGameInfo()
                )
                .toResponseDto()
        )
    }

    @DeleteMapping("/game/id")
    override fun removeGame(
        @RequestParam id: Long
    ): ResponseEntity<GameResponse> {
        return ResponseEntity.ok(
            removeGameById
                .execute(id = Game.Id(id))
                .toResponseDto()
        )
    }
}

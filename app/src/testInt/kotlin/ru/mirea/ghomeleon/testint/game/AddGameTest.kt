package ru.mirea.ghomeleon.testint.game

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import ru.mirea.ghomeleon.rest.game.dto.request.AddGameReleaseRequest
import ru.mirea.ghomeleon.rest.game.dto.request.AddGameRequest
import ru.mirea.ghomeleon.rest.game.dto.request.AddGameReviewRequest
import ru.mirea.ghomeleon.rest.game.dto.response.GameResponse
import ru.mirea.ghomeleon.testint.BaseIntTest
import java.time.LocalDate

class AddGameTest : BaseIntTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jacksonObjectMapper: ObjectMapper

    @Test
    fun shouldAddNewGame() {
        val addGameRequest = AddGameRequest(
            name = "Animal Crossing: New Horizons",
            description = """Animal Crossing: New Horizons is a social simulation game 
                |developed and published by Nintendo in 2020 for the Nintendo Switch; 
                |it is the fifth main entry in the Animal Crossing series. 
                |In New Horizons, the player controls a character who moves to a deserted island 
                |after purchasing a getaway package from Tom Nook, accomplishes assigned tasks, 
                |and develops the island as they choose. They can gather and craft items, 
                |customize the island, and develop it into a community 
                |of anthropomorphic animals.
            """.trimMargin()
        )

        val createdGameAsString = mockMvc
            .post("/api/game") {
                contentType = MediaType.APPLICATION_JSON
                content = jacksonObjectMapper.writeValueAsString(addGameRequest)
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val createdGame = jacksonObjectMapper
            .readValue<GameResponse>(createdGameAsString)

        val createdGameId = createdGame.id

        val gameAsString = mockMvc
            .get("/api/game/id") {
                param("id", createdGameId.toString())
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val game = jacksonObjectMapper
            .readValue<GameResponse>(gameAsString)

        Assertions.assertAll(
            { Assertions.assertEquals(addGameRequest.name, createdGame.name) },
            { Assertions.assertEquals(addGameRequest.description, createdGame.description) },
            { Assertions.assertTrue(createdGame.reviews.isEmpty()) },
            { Assertions.assertTrue(createdGame.releases.isEmpty()) },
            { Assertions.assertEquals(createdGame.id, game.id) },
            { Assertions.assertEquals(createdGame.name, game.name) },
            { Assertions.assertEquals(createdGame.description, game.description) },
            { Assertions.assertTrue(game.reviews.isEmpty()) },
            { Assertions.assertTrue(game.releases.isEmpty()) },
        )
    }

    @Test
    fun shouldAddReviewForFirstGameFromMigrationById() {
        val addReviewRequest = AddGameReviewRequest(
            mark = 4,
            text = "Good game!"
        )

        val gameWithoutNewReviewAsString = mockMvc
            .get("/api/game/id") {
                param("id", "1")
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val gameWithoutNewReview = jacksonObjectMapper
            .readValue<GameResponse>(gameWithoutNewReviewAsString)

        val gameWithNewReviewAsString = mockMvc
            .post("/api/game/id/review") {
                param("id", "1")
                contentType = MediaType.APPLICATION_JSON
                content = jacksonObjectMapper.writeValueAsString(addReviewRequest)
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val gameWithNewReview = jacksonObjectMapper
            .readValue<GameResponse>(gameWithNewReviewAsString)

        Assertions.assertAll(
            { Assertions.assertEquals(gameWithoutNewReview.id, gameWithNewReview.id) },
            { Assertions.assertEquals(gameWithoutNewReview.name, gameWithNewReview.name) },
            { Assertions.assertEquals(gameWithoutNewReview.reviews.size + 1, gameWithNewReview.reviews.size) },
            { Assertions.assertEquals(addReviewRequest.mark, gameWithNewReview.reviews.last().mark) },
            { Assertions.assertEquals(addReviewRequest.text, gameWithNewReview.reviews.last().text) },
        )
    }

    @Test
    fun shouldAddReleaseForFirstGameFromMigrationById() {
        val addReleaseRequest = AddGameReleaseRequest(
            date = LocalDate.now(),
            platformId = 2
        )

        val gameWithoutNewReleaseAsString = mockMvc
            .get("/api/game/id") {
                param("id", "1")
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val gameWithoutNewRelease = jacksonObjectMapper
            .readValue<GameResponse>(gameWithoutNewReleaseAsString)

        val gameWithNewReleaseAsString = mockMvc
            .post("/api/game/id/release") {
                param("id", "1")
                contentType = MediaType.APPLICATION_JSON
                content = jacksonObjectMapper.writeValueAsString(addReleaseRequest)
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val gameWithNewRelease = jacksonObjectMapper
            .readValue<GameResponse>(gameWithNewReleaseAsString)

        Assertions.assertAll(
            { Assertions.assertEquals(gameWithoutNewRelease.id, gameWithNewRelease.id) },
            { Assertions.assertEquals(gameWithoutNewRelease.name, gameWithNewRelease.name) },
            { Assertions.assertEquals(gameWithoutNewRelease.releases.size + 1, gameWithNewRelease.releases.size) },
            { Assertions.assertEquals(addReleaseRequest.date, gameWithNewRelease.releases.last().date) },
            { Assertions.assertEquals(addReleaseRequest.platformId, gameWithNewRelease.releases.last().platformId) },
        )
    }
}
